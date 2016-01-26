package activity;

 

import util.AllUrl;
import util.ToastUtils;

import com.licai.R;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
 
import base.BaseActivity;

public class ProblemActivity extends BaseActivity implements OnClickListener {
               
	
	
	
	
	private LinearLayout problem_login;
	private LinearLayout  problem_card;
	private LinearLayout problem_anquan;
	private LinearLayout problem_about;
	private LinearLayout problem_shouyi;
	private LinearLayout problem_yaoqing;
 
	@Override
    public void onCreate(Bundle arg0) {
    
     super.onCreate(arg0);
     setContentView(R.layout.activity_problem); 
     initview(this,"常见问题");
    
     findview(); 
     
     
	 
	 }

	private void findview() {
		 
		problem_login=(LinearLayout) findViewById(R.id.problem_login);
		problem_card=(LinearLayout) findViewById(R.id.problem_card);
		problem_anquan=(LinearLayout)findViewById(R.id.problem_anquan);
		problem_about=(LinearLayout) findViewById(R.id.problem_about);
		problem_shouyi=(LinearLayout) findViewById(R.id.problem_shouyi);
		problem_yaoqing=(LinearLayout) findViewById(R.id.problem_yaoqing);
		 
	 
		
		problem_login.setOnClickListener(this);
		problem_card.setOnClickListener(this);
		problem_anquan.setOnClickListener(this);
		problem_about.setOnClickListener(this);
		problem_shouyi.setOnClickListener(this);
		problem_yaoqing.setOnClickListener(this);
 	
	}


	@Override
	public void onClick(View v) {
		 
		switch (v.getId()) {
		   
		case R.id.problem_login:
	 	
			Intent intent1=new Intent(context,ProblemDetailActivity.class);
		    intent1.putExtra("url_problem_detail", AllUrl.url_problem_detail1);
			context.startActivity(intent1);
			
			break;
			                          
		case R.id.problem_card:
		 	

			Intent intent2=new Intent(context,ProblemDetailActivity.class);
		    intent2.putExtra("url_problem_detail", AllUrl.url_problem_detail2);
			context.startActivity(intent2);
			
			
			break;   
			 
		case R.id.problem_anquan:
		 	

			Intent intent3=new Intent(context,ProblemDetailActivity.class);
		    intent3.putExtra("url_problem_detail", AllUrl.url_problem_detail3);
			context.startActivity(intent3);
			
			
			break;
			 
		case R.id.problem_about:
		 	

			Intent intent4=new Intent(context,ProblemDetailActivity.class);
		    intent4.putExtra("url_problem_detail", AllUrl.url_problem_detail4);
			context.startActivity(intent4);
			
			break;
			 
		case R.id.problem_shouyi:
		 	

			Intent intent5=new Intent(context,ProblemDetailActivity.class);
		    intent5.putExtra("url_problem_detail", AllUrl.url_problem_detail5);
			context.startActivity(intent5);
	 	
			break;
			 
		case R.id.problem_yaoqing:
		 	

			Intent intent6=new Intent(context,ProblemDetailActivity.class);
		    intent6.putExtra("url_problem_detail", AllUrl.url_problem_detail6);
			context.startActivity(intent6);
	 	
			break;
		 
 	
	}
 












	}

}
