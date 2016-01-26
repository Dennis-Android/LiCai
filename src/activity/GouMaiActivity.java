package activity;

import java.text.DecimalFormat;

import util.AllUrl;
import util.ToastUtils;

import com.licai.R;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import base.BaseActivity;

public class GouMaiActivity extends BaseActivity implements OnClickListener {
                    
	
	 public DecimalFormat format=new DecimalFormat("######0.00");
   private  TextView	buy_name;       //名称
   private  TextView buy_zhekou;        //折扣
   private  TextView buy_qixi;            //气息日
   private  TextView buy_time;           //期限	
   private  TextView buy_shouyi;         //收益
   private  EditText	edit_buy;          //输入金额
   private  CheckBox yue_zhifu;            //余额支付
   private  CheckBox card_zhifu;             //银行卡支付
   private  CheckBox buy_checek;            //同意协议
   private  TextView	buy_xieyi;        //协议
   private  Button buy_btn;              //提交
   private TextView tiyan;
   private String product_name;
   private double raise;
   private int experience_amount;
   private int financial_period;
   private String value_date;
   private int product_id;
   private String input_money;
   private Intent intent;
private String starting_amount;          
private LinearLayout tiyan_ll;
   @Override
	        public void onCreate(Bundle arg0) {
	        	 
	        	super.onCreate(arg0);
	         setContentView(R.layout.activity_goumai);
	         initview(context, "购买");  
	         findview(); 
	         
	         
	            
			     
			    
			   
			    product_name= getIntent().getStringExtra("product_name");
			    raise= getIntent().getDoubleExtra("raise",0.0);
			    experience_amount= getIntent().getIntExtra("experience_amount", 0);
			    financial_period= getIntent().getIntExtra("financial_period", 0);
			    value_date= getIntent().getStringExtra("value_date");
			    product_id= getIntent().getIntExtra("product_id", 0);
			    starting_amount= getIntent().getStringExtra("starting_amount");
	           
			    System.out.println("experience_amount"+experience_amount);
			    
		 if(product_name!=null){
			 buy_name.setText(product_name);
		 }
		 
		 if(raise!=0){
			 buy_zhekou.setText(String.valueOf(raise*100)+"%");
	 	 
		 }	    
			    
		 if(value_date!=null){
			 buy_qixi.setText(value_date+"日起息");
		 }
		 
		 if(financial_period!=0){
			 buy_time.setText(String.valueOf("期限"+financial_period)+"天");
		 }
		 
		 if(experience_amount!=0){
			 buy_name.setText(product_name);
			 tiyan_ll.setVisibility(View.VISIBLE);
			 tiyan.setText("送"+String.valueOf(experience_amount)+"元体验金");
		 }
		 
		 
		 
	  edit_buy.setHint(starting_amount+"起购"); 
		 
	  
	  
	  
	   input_money=edit_buy.getText().toString().trim();
  	   

		 if(!TextUtils.isEmpty(input_money)){
			 buy_shouyi.setText(format.format(((Integer.parseInt(input_money))+experience_amount)*raise*financial_period/365));
	           }else{
	        	   buy_shouyi.setText("0");   
	           }
	   
		 
		 edit_buy.addTextChangedListener(new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			 input_money=edit_buy.getText().toString().trim();
		 
			 System.out.println("input_money"+input_money);
			
			 
			 
			 if(!TextUtils.isEmpty(input_money)){
				 buy_shouyi.setText(format.format(((Integer.parseInt(input_money))+experience_amount)*raise*financial_period/365));
		         
				 System.out.println("raise"+raise);
				 System.out.println("financial_period"+financial_period);
			 }else{
		        	   buy_shouyi.setText("0");   
		           }
		 
		
		
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

	           
	           
	           
	           
			private void findview() {
				 
		 	       buy_name=(TextView) findViewById(R.id.buy_name);   
				   buy_zhekou=(TextView) findViewById(R.id.buy_zhekou); 
				   buy_qixi=(TextView) findViewById(R.id.buy_qixi); 
				   buy_shouyi=(TextView) findViewById(R.id.buy_shouyi); 
				   edit_buy=(EditText) findViewById(R.id.edit_buy); 
				   yue_zhifu=(CheckBox) findViewById(R.id.yue_zhifu); 
				   card_zhifu=(CheckBox) findViewById(R.id.card_zhifu); 
				   buy_checek=(CheckBox) findViewById(R.id.buy_checek); 
				   buy_xieyi=(TextView) findViewById(R.id.buy_xieyi); 
				   buy_btn=(Button) findViewById(R.id.buy_btn); 
				   tiyan=(TextView) findViewById(R.id.tiyan); 
				   
				   buy_time=(TextView) findViewById(R.id.buy_time); 
				   tiyan_ll=(LinearLayout) findViewById(R.id.tiyan_ll); 
				   
				   yue_zhifu.setOnClickListener(this);
				   card_zhifu.setOnClickListener(this);
				   buy_checek.setOnClickListener(this);
				   buy_xieyi.setOnClickListener(this);
				   buy_btn.setOnClickListener(this);
				   
				   
				   
				   
				   
			}





			@Override
			public void onClick(View v) {
				 
				switch (v.getId()) {
				case R.id.yue_zhifu:
					
					yue_zhifu.setChecked(true);
					card_zhifu.setChecked(false);
					
					
					break;
                
				 case R.id.card_zhifu:
						yue_zhifu.setChecked(false);
						card_zhifu.setChecked(true);
					break;

                 case R.id.buy_checek:
	

                	 if(buy_checek.isChecked()){
                		 buy_btn.setEnabled(true); 
                	 }else{
                		 buy_btn.setEnabled(false);  
                	 }
                	 
	                break;
					
					
                  case R.id.buy_xieyi:
                    intent=new Intent(this,XieYiActivity.class); 
          		 	intent.putExtra("url", AllUrl.url_buy+String.valueOf(product_id));
          			intent.putExtra("title", "用户购买协议");
          			startActivity(intent);
                	  
                	  
	               break;	
					 
	               
	               
                 case R.id.buy_btn:
	
                	 if(!TextUtils.isEmpty(input_money)){
                		 intent=new Intent(context,AddCardActivity.class); 
                		 startActivity(intent);
                	  
                	 } else{
                		 ToastUtils.Toast(context, "请输入金额");
                	 }
          	 
                  	break;				  
					  
					 
					 
					   
					   
					
					
					
					
					
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
	
	
	
}
