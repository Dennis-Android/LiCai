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

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import application.MySingle;
import base.BaseActivity;

import model.Card;


public class AddCardActivity extends BaseActivity implements OnClickListener {
        
	private EditText add_name;             //姓名
	private EditText add_shenfenzheng;         //省份证
	private EditText add_carnumber;         //银行卡号
	private TextView getbank;                //点击获取银行
	private EditText yuliu_number;            //预留号码
	private Button add_card_btn;               //添加按钮
	
	private String carnumber;
	
   private RequestQueue queue;
   private StringRequest  request;
   private Gson gson=new Gson();
   private Card card;
	
	
	 @Override
	    public void onCreate(Bundle arg0) {
	     	super.onCreate(arg0);
	    setContentView(R.layout.activity_addcard);
	    queue=MySingle.getInstance(context).getRequestQueue(context);	
	    initview(context, "添加银行卡");
	    findview();
	 
	    
	    
	    
	 
	 }












	 private void findview() {
		 add_name=(EditText) findViewById(R.id.add_name);
		 add_shenfenzheng=(EditText) findViewById(R.id.add_shenfenzheng);
		 
		 add_carnumber=(EditText) findViewById(R.id.add_carnumber);
		 getbank=(TextView) findViewById(R.id.getbank);
		 yuliu_number=(EditText) findViewById(R.id.yuliu_number);
		 add_card_btn=(Button) findViewById(R.id.add_card_btn);
			 
			
			
		 getbank.setOnClickListener(this);
		 add_card_btn.setOnClickListener(this);
		 add_carnumber.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				getbank.setText("点击获取银行");
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
		 	}
			
			@Override
			public void afterTextChanged(Editable s) {
		 	}
		});
			 
			 
			 
			 
			 
			
		}

 





	@Override
	public void onClick(View v) {
		switch (v.getId()) {
	  
		case R.id.getbank:
			
		 carnumber=add_carnumber.getText().toString().trim();
		if(TextUtils.isEmpty(carnumber)){
			ToastUtils.Toast(context, "请输入卡号");
		}else{
			
			getcard(carnumber);
	 		
		}	
		
		
 		break;

       case R.id.add_card_btn:
			
    	 add_name=(EditText) findViewById(R.id.add_name);
  		 add_shenfenzheng=(EditText) findViewById(R.id.add_shenfenzheng);
  		 
  		 add_carnumber=(EditText) findViewById(R.id.add_carnumber);
  		 getbank=(TextView) findViewById(R.id.getbank);
  		 yuliu_number=(EditText) findViewById(R.id.yuliu_number);
  		 add_card_btn=(Button) findViewById(R.id.add_card_btn);
     	 
  		 String name=add_name.getText().toString().trim();
  		 carnumber=add_carnumber.getText().toString().trim();
  		 String  shenfenzheng=add_shenfenzheng.getText().toString().trim();
  		 String yuliu=yuliu_number.getText().toString().trim(); 
  		 String bank=getbank.getText().toString().trim(); 
  		
  	  
  		 if(TextUtils.isEmpty(name)||TextUtils.isEmpty(carnumber)||TextUtils.isEmpty(carnumber)||TextUtils.isEmpty(yuliu)||TextUtils.isEmpty(bank)||bank.equals("点击获取银行")){
   		    ToastUtils.Toast(context, "请填完所有信息");
   	    }else{
   		   
   	   } 
    	   
      
			break;
			
	 
		}
		
	}

 

 
	private void getcard(final String carnumber) {

	    request=new StringRequest(Method.POST, AllUrl.url_ankQuery, new Listener<String>() {
	          	@Override
					public void onResponse(String response) {
	          		 
	               
	                 try {
	                	 getbank.setText(gson.fromJson(response, Card.class).bank_name);
	                	 
	                	 
					} catch (Exception e) {
						 ToastUtils.Toast(context, "银行卡错误");
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
				 	map.put("card_number", carnumber);
				   
		  	 		return map;
				}};
		 	 queue.add(request);   
			
		
		
	}















}