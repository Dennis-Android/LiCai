package activity;

import util.ToastUtils;

import com.licai.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import base.BaseActivity;

public class AccountActivity extends BaseActivity implements OnClickListener {

	private SharedPreferences sp;
	private String number;
	
	private  TextView account_number;   //账户手机
	private  LinearLayout bankcard_ll;      //我的银行卡
	private  LinearLayout edit_pass_ll;       //修改登录密码
	private  LinearLayout edit_jiaoyi_ll;     //修改交易密码
	private  LinearLayout shoushi_ll;         //手势密码
	   
	
	private Button exit_login;                 //退出登录
	
	private Intent intent;
	private boolean login;
	
	
	@Override
	        public void onCreate(Bundle arg0) {
	        	 
	        	super.onCreate(arg0);
	          setContentView(R.layout.activity_account);
	         
	         sp=getSharedPreferences("account", MODE_PRIVATE);
		     
	         login=sp.getBoolean("login", false);
	         
	         number=sp.getString("phonenumber", null);
		         
	         initview(context, "账户中心");
	         findview();
	         
	        
	         
		    
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
	         
	         }




	private void findview() {
		 
		account_number=(TextView) findViewById(R.id.account_number);
		bankcard_ll=(LinearLayout) findViewById(R.id.bankcard_ll);
		edit_pass_ll=(LinearLayout) findViewById(R.id.edit_pass_ll);
		edit_jiaoyi_ll=(LinearLayout) findViewById(R.id.edit_jiaoyi_ll);
		 
		exit_login=(Button) findViewById(R.id.exit_login);
		 
		 
		
		
		 
		bankcard_ll.setOnClickListener(this);
		edit_pass_ll.setOnClickListener(this);
		edit_jiaoyi_ll.setOnClickListener(this);
 
		exit_login.setOnClickListener(this);
		
		if(number!=null){
           
			String a1=(String) number.subSequence(0, 3);
			String a2=(String) number.subSequence(8, 11);
		 	account_number.setText(a1+"****"+a2);
		
		}
		
		
	}




	@Override
	public void onClick(View v) {
		 
		switch (v.getId()) {
		case R.id.bankcard_ll:
			
			
			 intent=new Intent(this,BanklistActivity.class);
	    	    startActivity(intent);
			
			
			
			
			break;
       case R.id.edit_pass_ll:
			
    	    intent=new Intent(this,ResetPassActivity.class);
    	    startActivity(intent);
    	   
    	   
    	   
			break;
       case R.id.edit_jiaoyi_ll:
	        
    	   ToastUtils.Toast(context, "请添加交易账户");
    	   
	        break;

      
	
       case R.id.exit_login:
	
    	    sp.edit().putBoolean("login", false).commit();
    	   exit_login.setVisibility(View.GONE);
    	   finish();
    	   
	        break;
	
	
	
 
			 
			 
			
			
			
			
			
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
}
