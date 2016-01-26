package activity;

import java.util.HashMap;
import java.util.Map;

import model.Registe;

import util.AllUrl;
import util.PhoneEmail;
import util.ToastUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.licai.R;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import application.MySingle;
import base.BaseActivity;

public class RegisterActivity extends BaseActivity implements OnClickListener {

    private	EditText  input_code;        //输入验证码
    private Button   get_code;          //获取验证码
	    
    private EditText   input_password;  //输入登录密码
    private CheckBox   check;           //同意的checkbox
    private Button   xieyi_btn;         //协议
    private Button   regist_btn ;       //注册按钮
    private TimeCount time;
    private String password;
    private String code;
    private RequestQueue queue;
	
	public StringRequest  request;
    private Gson gson=new Gson();
	private String number;
    
	private int count=0;
	private String UDID;
	private Registe registe;
	private SharedPreferences sp;
	
 
	@Override
	public void onCreate(Bundle arg0) {
		 
		super.onCreate(arg0);
	    setContentView(R.layout.activity_register);  
	    initview(context, "注册");
	  
	    findview();
	    queue=MySingle.getInstance(context).getRequestQueue(context);
	    time=new TimeCount(60000, 1000);
	    number=getIntent().getStringExtra("number");
	    UDID=Secure.getString(getContentResolver(), Secure.ANDROID_ID);
	    sp=getSharedPreferences("account", MODE_PRIVATE);
	    
	  }


	private void findview() {
		input_code=(EditText) findViewById(R.id.input_code);
		get_code=(Button) findViewById(R.id.get_code);
		input_password=(EditText) findViewById(R.id.input_password);
		check=(CheckBox) findViewById(R.id.check);
		xieyi_btn=(Button) findViewById(R.id.xieyi_btn);
		regist_btn=(Button) findViewById(R.id.regist_btn);
	 	get_code.setOnClickListener(this);
		xieyi_btn.setOnClickListener(this);
		regist_btn.setOnClickListener(this);
	    check.setOnClickListener(this);
		
	 	
	}
	
	@Override
	public void onClick(View v) {
		 switch (v.getId()) {
		case R.id.get_code:
			
			count=count+1;
			getcode();
		 	time.start();
			ToastUtils.Toast(context, "发送成功");
		 	
	    
		break;
		case R.id.check:
	 	 if(check.isChecked()){
		 		 regist_btn.setEnabled(true); 
			   }else{
				   regist_btn.setEnabled(false);   
			   }
	 		break;
		case R.id.xieyi_btn:
			Intent intent=new Intent(this,XieYiActivity.class); 
			intent.putExtra("url", AllUrl.url_Xieyi);
			intent.putExtra("title", "用户注册协议");
			
			startActivity(intent);
     		break;
	 
		case R.id.regist_btn:
			 code =input_code.getText().toString().trim(); 
			 password=input_password.getText().toString().trim(); 
		 
	 		if(TextUtils.isEmpty(code)){
				ToastUtils.Toast(context, "请输入验证码");
			}else{
				if(TextUtils.isEmpty(password)||password.length()<6){
					ToastUtils.Toast(context, "请输入正确的密码");
				}else{
					  registe(code,password);
				}
	  		}
	 	
	 	 	   
			break;
	 	
		}
		
	}

	 private void registe( final String code,final String password) {
    request=new StringRequest(Method.POST, AllUrl.url_registe, new Listener<String>() {
          	@Override
				public void onResponse(String response) {
          		 registe=gson.fromJson(response, Registe.class); 
          		 
          		 if(registe.status_code==-802){
          			 ToastUtils.Toast(context, "验证码错误或过期，请重新发送");
          		 } 
          		  
          		 if(registe.status_code==801){
          			 ToastUtils.Toast(context, "恭喜您注册成功");
          			 sp.edit().putString("user_id", String.valueOf(registe.user_id)).commit();
          			 sp.edit().putString("password", password ).commit();
          			 sp.edit().putString("phonenumber", number).commit();
          		     
          			  Intent intent=new Intent(RegisterActivity.this,PreLoginActivity.class);
          	 	      startActivity(intent);
          		 } 
          		 
          		 
          		 
		 	   }
           	}, new ErrorListener() {
		        @Override
				public void onErrorResponse(VolleyError error) {
					 System.out.println("网络不通");
	 			}
			}){@Override
			   protected Map<String, String> getParams() throws AuthFailureError {
	    		Map<String, String> map=new HashMap<String, String>();
			 	map.put("app_version_id", "1700");
			 	map.put("verification_code", code);
			 	map.put("host_id", "0");
			 	map.put("app_market_id", "3");
			  	map.put("phone_number", number);
			 	map.put("phone_id", UDID);
			 	map.put("app_platform_id", "1");
			    map.put("login", "YES");
			 	map.put("pwd", password);	
				map.put("was_invited", "NO");	
    		
	  	 		return map;
			}};
	 	 queue.add(request);   
		
	}

	//倒计时实现
	   class TimeCount extends CountDownTimer{

		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
	 		
		}

		@Override
		public void onTick(long millisUntilFinished) {
			get_code.setText(millisUntilFinished/1000+"秒");
			get_code.setEnabled(false);
			get_code.setBackgroundColor(getResources().getColor(R.color.grey));
	 	}

		@Override
		public void onFinish() {
			get_code.setText("获取验证码");
			get_code.setEnabled(true);
			get_code.setBackgroundColor(getResources().getColor(R.color.indianred));
	 	    
		}
     }
	
	
	   //获取数据
  	   public void getcode() {
  	 	   request=new StringRequest(Method.POST, AllUrl.url_Getcode, new Listener<String>() {
            	@Override
  				public void onResponse(String response) {
            		 
  		 	   }
             	}, new ErrorListener() {
 		        @Override
  				public void onErrorResponse(VolleyError error) {
  					 System.out.println("网络不通");
  	 			}
  			}){@Override
  			   protected Map<String, String> getParams() throws AuthFailureError {
  	    		Map<String, String> map=new HashMap<String, String>();
  			 	map.put("counter", String.valueOf(count));
  			 	map.put("phone_number", number);
  			 	map.put("password", "cb16534e7163859b81710056af492158");
  			 
  	  	 		return map;
  			}};
  	 	 queue.add(request);   
  	 	}
	
	
	
	
	
	
	

}
