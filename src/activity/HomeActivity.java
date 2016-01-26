package activity;

import base.BaseActivity;

import com.licai.R;

import fragment.Fragment1;
import fragment.Fragment2;
import fragment.Fragment3;
import fragment.Fragment4;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import application.MyApplication;
 
public class HomeActivity extends  BaseActivity{
     
	public FragmentTabHost tabhost;
	public Class [] fragments ={Fragment1.class,Fragment2.class,Fragment3.class,Fragment4.class};
	 public  long ExitTime=0;
	
	 public ImageView tab_item_image;  //tab的图片
     public TextView tab_item_text;    //tab的文字
     public TabSpec tabspec;
	
	
	public  int [] imagearray={R.drawable.tab_1_selector,R.drawable.tab_2_selector,R.drawable.tab_3_selector,R.drawable.tab_4_selector};
	
	public String []  title={"首页","理财产品","我的资产","更多"};
	public View view;
	
	public Context context;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
	 	setContentView(R.layout.activity_home);
		
		init();  //初始化四个界面
    	 
	   MyApplication.addActivity(this);
		
	    }

 
	public void init() {
 	        tabhost=(FragmentTabHost)findViewById(android.R.id.tabhost);
		    tabhost.setup(this, getSupportFragmentManager(), R.id.home_fl);    
		  
		   for(int i=0;i<fragments.length;i++){
			    tabspec=tabhost.newTabSpec(title[i]).setIndicator(Myview(i));
		    	tabhost.addTab(tabspec, fragments[i],null);
		    }
		   
		
	  }


	public View Myview(int i) {
		    view=View.inflate(HomeActivity.this, R.layout.tab_item, null);    
		    tab_item_text=(TextView) view.findViewById(R.id.tab_item_text); 
		    tab_item_image=(ImageView) view.findViewById(R.id.tab_item_image); 
	    	  
		    tab_item_image.setImageResource(imagearray[i]);
		    tab_item_text.setText(title[i]); 
	         return view;
     } 
 
	      
	           //按2下退出   
			   @Override
				public boolean onKeyDown(int keyCode, KeyEvent event) {
					   //如果按返回键
					   if(keyCode==KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){
						  if(System.currentTimeMillis()-ExitTime>2000){
							  Toast.makeText(getApplicationContext(),"再按一次退出程序",0).show(); 
						      ExitTime=System.currentTimeMillis();
						  }else{
							  finish();
				 		  System.exit(0);   //0表示程序正常退出，1表示非正常退出
		 				  }
					     return true;
					   }
	 			   return super.onKeyDown(keyCode, event);
				}
	      
	 @Override
	protected void onDestroy() {
		 
		super.onDestroy();
		 MyApplication.removeActivity(this);
	 }
    
	      
	      
}
       