package activity;

import com.licai.R;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import base.BaseActivity;

public class JiangLiActivity extends BaseActivity {
               
	
	private String activity_link;
	
	 private WebView jiangli_web;

	@Override
    public void onCreate(Bundle arg0) {
    
     super.onCreate(arg0);
     setContentView(R.layout.activity_jiangli); 
     initview(this,"最新资讯");
     
     activity_link=getIntent().getStringExtra("activity_link");
     
     
     
     jiangli_web=(WebView) findViewById(R.id.jiangli_web);
	 
     jiangli_web.getSettings().setJavaScriptEnabled(true);
	 jiangli_web.getSettings().setDomStorageEnabled(true);
	 
	 WebViewClient client=new WebViewClient(){
		 public boolean shouldOverrideUrlLoading(WebView view, String url) {
			
			 view.loadUrl(url);
	 	 
			 return false;
	 	 };
	  };
	 
	  jiangli_web.loadUrl(activity_link);
	 
	 
	 
	 
	 
	 
	 }
}
