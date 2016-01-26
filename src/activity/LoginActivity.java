package activity;

import java.util.HashMap;
import java.util.Map;

import model.Registe;
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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import application.MyApplication;
import application.MySingle;
import base.BaseActivity;

public class LoginActivity extends BaseActivity {
     
	
	
  private	EditText input_pass;
  private   Button login;
	
  private String pass;
  public StringRequest  request;
  private Gson gson=new Gson();
  private SharedPreferences sp;
  private RequestQueue queue;
private String number;
private String UDID;
  
  
  
  
	  @Override
	public void onCreate(Bundle arg0) {
		 
		super.onCreate(arg0);
	    setContentView(R.layout.activity_login);
	    initview(context, "登录");  
	  
	    input_pass=(EditText) findViewById(R.id.input_pass);
	    login=(Button) findViewById(R.id.login);
	    
	    number=getIntent().getStringExtra("number");
	    queue=MySingle.getInstance(context).getRequestQueue(context);
	    
	     
	    sp=getSharedPreferences("account", MODE_PRIVATE);
	    
	    UDID=Secure.getString(getContentResolver(), Secure.ANDROID_ID); 
	    
	    
	    login.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				pass=input_pass.getText().toString().trim();
				if(TextUtils.isEmpty(pass)){
					ToastUtils.Toast(context, "请输入密码");
				}else{
					Login(pass);
				}
	 		}
 	});
	    
	  }
 	  private void Login(final String pass2) {
		 
			    request=new StringRequest(Method.POST, AllUrl.url_login, new Listener<String>() {
			          	@Override
							public void onResponse(String response) {
			          		     
			          		
			          		 if(gson.fromJson(response, Registe.class).status_code==-901){
			          			 ToastUtils.Toast(context, "密码错误");
			          		 } 
			          		  
			          		 if(gson.fromJson(response, Registe.class).status_code==901){
			          			 ToastUtils.Toast(context, "登录成功");
			          			 sp.edit().putBoolean("login", true).commit();
			          			 sp.edit().putString("user_id", String.valueOf(gson.fromJson(response, Registe.class).user_id)).commit();
			          			 sp.edit().putString("phonenumber", number ).commit();
			          			 
			          			 
			          			 
			          			 
			          			 MyApplication.finishAll();
			          		 	 Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
			          	 	     startActivity(intent);
			          		     finish();
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
						 	map.put("app_market_id", "3");
						  	map.put("phone_number", number);
						  	map.put("phone_id", UDID);
						 	map.put("app_platform_id", "1");
						 	map.put("pwd", pass2);	
						 	map.put("user_phone_equipment", "AvmkKtstOPO6fW9-SK-c6owU8-FeJsXEPcgmf_J0qJWS");
						 	
							 
				  	 		return map;
						}};
				 	 queue.add(request);   
					
				 
		}
}
