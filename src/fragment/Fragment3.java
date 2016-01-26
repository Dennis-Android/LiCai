package fragment;

import com.android.volley.RequestQueue;
import com.licai.R;

import base.BaseFragment;
import activity.AccountActivity;
import activity.BanklistActivity;
import activity.BlankActivity;
import activity.PreLoginActivity;
import android.R.string;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
 
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Fragment3 extends BaseFragment implements OnClickListener {
public RequestQueue queue;
	
	
	
	public Context context;     
	public View view;
	public TextView fragment_title;

	private SharedPreferences sp;
	
	
	private ScrollView scroll;
	private boolean login; 

	private Button login_bt;
	private Intent intent;
	 
	private RelativeLayout fg3_shoyi_ll;            //收益
	private ImageView imag ;                  //头像
	private RelativeLayout yigouzichan_ll;         //已购资产
	 
	private LinearLayout goumai_ll;             //购买记录 
	private LinearLayout  huankuan_ll;             //还款记录
	private LinearLayout Mybank_ll;              //我的银行卡
	private String number;



	private TextView fg3_account;
	
	
	
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	 	super.onCreate(savedInstanceState);
	 	context=getActivity();
	   }
	
	   @Override
 
	  public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		   checkNet();  //检查网络
		   view=View.inflate(context, R.layout.fragment3,null);
		   
		   fragment_title= (TextView) view.findViewById(R.id.fragment_title);
		   fragment_title.setText("我的资产"); 
		   
		   sp=context.getSharedPreferences("account", context.MODE_PRIVATE);
		   
		   number=sp.getString("phonenumber", null);
		   
		   findview();
		   
		   
		   
		   
		   return view;
	  
	   
	   }

	
	   
	   
	   
	   
	   
	   
	   
		 
	 
		 
	 
	 
	   
	 private void findview() {
	 login_bt=(Button) view.findViewById(R.id.login);
	 scroll=(ScrollView) view.findViewById(R.id.scroll);
	 fg3_shoyi_ll=(RelativeLayout) view.findViewById(R.id.fg3_shoyi_ll);
	 imag=(ImageView) view.findViewById(R.id.imag);
	 yigouzichan_ll=(RelativeLayout) view.findViewById(R.id.yigouzichan_ll);
	 goumai_ll=(LinearLayout) view.findViewById(R.id.goumai_ll);
	 huankuan_ll=(LinearLayout) view.findViewById(R.id.huankuan_ll);
	 Mybank_ll=(LinearLayout) view.findViewById(R.id.Mybank_ll);
	 
	 
	 fg3_account=(TextView) view.findViewById(R.id.fg3_account);
	 
		if(number!=null){
	           
			String a1=(String) number.subSequence(0, 3);
			String a2=(String) number.subSequence(8, 11);
			fg3_account.setText(a1+"****"+a2);
	
		}
	 
	 login=sp.getBoolean("login", false);
	 
	 if(login==false){
		 login_bt.setVisibility(View.VISIBLE); 
		 scroll.setVisibility(View.GONE); 
	 }else{
		 scroll.setVisibility(View.VISIBLE); 
		 login_bt.setVisibility(View.GONE); 
	 }
	 
	 
	 
	 login_bt.setOnClickListener(this);
	 fg3_shoyi_ll.setOnClickListener(this);
	 imag.setOnClickListener(this);
	 yigouzichan_ll.setOnClickListener(this);
	 goumai_ll.setOnClickListener(this);
	 huankuan_ll.setOnClickListener(this);
	 Mybank_ll.setOnClickListener(this);
	 
	 
	 
	 
	 
	 
	 
	  
	}

	@Override
	public void onClick(View v) {
		 switch (v.getId()) {
		case R.id.login:
	    intent=new Intent(context,PreLoginActivity.class);	
	    context.startActivity(intent);
	   
		break;
		case R.id.fg3_shoyi_ll:
			intent=new Intent(context,BlankActivity.class);	
		    intent.putExtra("title", "收益明细");
			context.startActivity(intent);
			
			break;
	 
		case R.id.imag:
			 intent=new Intent(context,AccountActivity.class);	
			 context.startActivity(intent);
			
			break;
	 
		case R.id.yigouzichan_ll:
			intent=new Intent(context,BlankActivity.class);	
		    intent.putExtra("title", "已购资产");
			context.startActivity(intent);
		   
			break;
			
		case R.id.goumai_ll:
			intent=new Intent(context,BlankActivity.class);	
		    intent.putExtra("title", "购买记录");
			context.startActivity(intent); 
		   
			break;
			
		case R.id.huankuan_ll:
			intent=new Intent(context,BlankActivity.class);	
		    intent.putExtra("title", "还款记录");
			context.startActivity(intent); 
		   
			break;
			
		case R.id.Mybank_ll:
			 intent=new Intent(context,BanklistActivity.class);	
			 context.startActivity(intent);
			 
		   
			break;
			
			
			
			
			
			
			
			
		}
		
	}
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
}
