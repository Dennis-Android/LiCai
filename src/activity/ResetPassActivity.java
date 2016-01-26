package activity;

import java.util.HashMap;
import java.util.Map;

import model.Registe;
import model.Reset;
import util.AllUrl;
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

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import application.MySingle;
import base.BaseActivity;

public class ResetPassActivity extends BaseActivity implements OnClickListener {
               
	private Button reget_code;               //获取验证码
	private EditText reinput_code;           //验证码输入框
	private EditText reinput_password;       //输入登录密码
	private Button reregist_btn;             //设置密码按钮
	public StringRequest  request;
    private Gson gson=new Gson();
 
	  private RequestQueue queue;
	
	private TextView default_number;
	
	
	private SharedPreferences sp;
	private String number;
	    @Override
	    public void onCreate(Bundle arg0) {
	    	 
	    	super.onCreate(arg0);
	        setContentView(R.layout.activity_resetpass);
	    	sp=getSharedPreferences("account", MODE_PRIVATE);
	    	
	    	
	    	initview(context, "设置登录密码");
	    	
	    	number=sp.getString("phonenumber", null);
	    	
	    	findview();
	    	
	      queue=MySingle.getInstance(context).getRequestQueue(context);
	    
	    
	    
	    
	    
	    }
		private void findview() {
			default_number=(TextView) findViewById(R.id.default_number);
			reget_code=(Button) findViewById(R.id.reget_code);
			reinput_code=(EditText) findViewById(R.id.reinput_code);
			reinput_password=(EditText) findViewById(R.id.reinput_password);
			reregist_btn=(Button) findViewById(R.id.reregist_btn);
			
		 
			
			reget_code.setOnClickListener(this);
			reregist_btn.setOnClickListener(this);
		 
			
			
			
			
			
			if(number!=null){
		     	String a1=(String) number.subSequence(0, 3);
				String a2=(String) number.subSequence(8, 11);
				default_number.setText(a1+"****"+a2);
			}
			
	 	
		}
		@Override
		public void onClick(View v) {
			 
			switch (v.getId()) {
			
			
			case R.id.reget_code:
				getcode();
		 		
				break;
	      
			
			
			case R.id.reregist_btn:
				String recode=reinput_code.getText().toString().trim();
				String repass=reinput_password.getText().toString().trim();
				
				if(TextUtils.isEmpty(recode)){
					ToastUtils.Toast(context, "请输入验证码");
				}else{
					
					
					if(TextUtils.isEmpty(repass)||repass.length()<6){
						
						ToastUtils.Toast(context, "请输入6位数密码");
					}else{
						
						registe(recode,repass,repass);
				  		
					}
					
		 		}
		 	break;
	       
		 	
				
				
				
			}
		}
		//设置登录密码
		private void registe(final String recode,final  String repass,final String repass2) {

      request=new StringRequest(Method.POST, AllUrl.url_re_setpass, new Listener<String>() {
		          	@Override
						public void onResponse(String response) {
		          	        if(gson.fromJson(response, Reset.class).status_code==1201){
		          	        	ToastUtils.Toast(context, "修改成功");
		          	       
		          	        	 sp.edit().putString("password", repass2 ).commit();
		          	        
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
					 	map.put("verification_code", recode);
					  	map.put("id_number_suffix", "000000");
					    map.put("phone_number", number);	
					    map.put("pwd", repass);	
					    
				  		return map;
					}};
			 	 queue.add(request);   
				
			
			
			
			
			
			
			
			
			
			
			
		 
			
			
			
			
		}
		//获取验证码
		private void getcode() {

		    request=new StringRequest(Method.POST, AllUrl.url_re_getcode, new Listener<String>() {
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
					 	map.put("counter", "1");
					  	map.put("phone_number", number);
					    map.put("password", "d41d8cd98f00b204e9800998ecf8427e");	
		    	 		
			  	 		return map;
					}};
			 	 queue.add(request);   
				
			
			
			
			
			
			
			
			
			
			
			
		}
	
}
