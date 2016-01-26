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
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import application.MySingle;
import base.BaseActivity;

public class PreLoginActivity extends BaseActivity implements OnClickListener {

	private String number  ;      
	private EditText input_phone;
	private Button login_next;
	private SharedPreferences sp;
	private String user_id;
	private String phonenumber;
	 private RequestQueue queue;
	public StringRequest  request;
    private Gson gson=new Gson();
	@Override
	public void onCreate(Bundle arg0) {
		 
		super.onCreate(arg0);
	   setContentView(R.layout.activity_pre_login);  
	  
	   initview(context, "填写手机号码");
	  
	   findview();
	   sp=getSharedPreferences("account", MODE_PRIVATE);
	   queue=MySingle.getInstance(context).getRequestQueue(context); 
	  }
 
	private void findview() {
		input_phone=(EditText) findViewById(R.id.input_phone);
		login_next=(Button) findViewById(R.id.login_next);
		
		login_next.setOnClickListener(this);
   }
	
	@Override
	public void onClick(View v) {
		 switch (v.getId()) {
		case R.id.login_next:
		 number=input_phone.getText().toString().trim();
		 if(PhoneEmail.isMobileNO(number)==false){
			 ToastUtils.Toast(context, "请输入正确的手机号");
		 }else{
			 getcode();
	  	 }	
	 	break;
  	}
   }
     public void getcode() {
	 	   request=new StringRequest(Method.POST, AllUrl.url_Getcode, new Listener<String>() {
         	@Override
				public void onResponse(String response) {
         		         if(gson.fromJson(response, Registe.class).status_code==602){
         		        	 Intent intent=new Intent(context,LoginActivity.class);	
         		        	 intent.putExtra("number", number); 
        				 	 startActivity(intent); 
         		         }else{
         		        	 Intent intent=new Intent(context,RegisterActivity.class);	
         					 intent.putExtra("number", number);
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
			 	map.put("counter", "1");
			 	map.put("phone_number", number);
			 	map.put("password", "cb16534e7163859b81710056af492158");
			 
	  	 		return map;
			}};
	 	 queue.add(request);   
	 	}
	

}
