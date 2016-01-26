package fragment;

import java.net.URI;

import util.ToastUtils;

import com.android.volley.RequestQueue;
import com.licai.R;

import base.BaseFragment;
import activity.AccountActivity;
import activity.FanKuiActivity;
import activity.MessageActivity;
import activity.PreLoginActivity;
import activity.ProblemActivity;
import activity.RegisterActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
 
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fragment4 extends BaseFragment implements OnClickListener {
   public RequestQueue queue;
	
	
	
	public Context context;   
	public View view;
	public TextView fragment_title;
 
	
	
	
	private SharedPreferences sp;
	
	private LinearLayout account_ll;
	private LinearLayout yaoqin_ll;
 
 	private LinearLayout Message_ll;
	private LinearLayout Problam_ll;
	private LinearLayout LianXi_ll;
	private LinearLayout FanKui_ll;
	private LinearLayout Setting_ll;



	private boolean login;



	private Intent intent;
	
	
	
	
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	 	super.onCreate(savedInstanceState);
	 	context=getActivity(); 
	   }
	
	   @Override
 
	  public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		   checkNet();  //检查网络
		   view=View.inflate(context, R.layout.fragment4,null);
		   
		   fragment_title= (TextView) view.findViewById(R.id.fragment_title);
		   fragment_title.setText("更多"); 
		   
		   findview();
		   sp=context.getSharedPreferences("account", context.MODE_PRIVATE);
		   
		  
		   
		   
		   
		   return view;
	  
	   
	   }

	private void findview() {
		account_ll=(LinearLayout) view.findViewById(R.id.account_ll);
	  	Message_ll=(LinearLayout) view.findViewById(R.id.Message_ll);
		Problam_ll=(LinearLayout) view.findViewById(R.id.Problam_ll);
		LianXi_ll=(LinearLayout) view.findViewById(R.id.LianXi_ll);
		FanKui_ll=(LinearLayout) view.findViewById(R.id.FanKui_ll);
		Setting_ll=(LinearLayout) view.findViewById(R.id.Setting_ll);
		
	 	account_ll.setOnClickListener(this);
        Message_ll.setOnClickListener(this);
		Problam_ll.setOnClickListener(this);
		LianXi_ll.setOnClickListener(this);
		FanKui_ll.setOnClickListener(this);
		Setting_ll.setOnClickListener(this);
		
		
		
		
		
		
	}

	@Override
	public void onClick(View v) {
		 
		switch (v.getId()) {
		 //账户中心	
		case R.id.account_ll:
			 login=sp.getBoolean("login", false);
			
			if(login==false){
	 		 
	 		intent=new Intent(context,PreLoginActivity.class);
	 		context.startActivity(intent);
	 		
	 	}else{
	 		 
	 		intent=new Intent(context,AccountActivity.class);
	 		context.startActivity(intent);
	 		
	 		
	 		
	 	}
			
			
			break;
		 
		 
			 //消息中心
		case R.id.Message_ll:
		 	
			if(login==false){
		 		intent=new Intent(context,PreLoginActivity.class);
		 		context.startActivity(intent);
		 		
		 	}else{
		 		intent=new Intent(context,MessageActivity.class);
		 		context.startActivity(intent);
		  		
		 	}
			
			
			
			
			break;
			 //常见问题
		case R.id.Problam_ll:
		 	
			Intent intent5=new Intent(context,ProblemActivity.class);
			context.startActivity(intent5);
			
			
			break;
			 //联系我们
		case R.id.LianXi_ll:
		 	
			Intent intent6=new Intent(Intent.ACTION_CALL, Uri.parse("tel:400-050-1889"));
			context.startActivity(intent6);
			
			
			break;
			 //意见反馈
		case R.id.FanKui_ll:
		 	
			if(login==false){
		 		intent=new Intent(context,PreLoginActivity.class);
		 		context.startActivity(intent);
		 		
		 	}else{
		 		
		 		intent=new Intent(context,FanKuiActivity.class);
		 		context.startActivity(intent);
		 		
		 		
		 	}
			
			
			
			
			break;
		 //检查更新	
		case R.id.Setting_ll:
		 	
			ToastUtils.Toast(context, "已经是最新版本了");
		 
			break;
	 
		 	
		 	
		}
 	
	}
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
}
