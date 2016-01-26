package activity;

import util.AllUrl;

import com.licai.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import base.BaseActivity;

public class YueLiBaoActivity extends BaseActivity {
	private String title;         
	
	private String active_link;
	
	 private WebView guarantee_web;

	@Override
    public void onCreate(Bundle arg0) {
    
     super.onCreate(arg0);
     setContentView(R.layout.activity_guarantee); 
    
     
     if(getIntent().getStringExtra("active_link")!=null){
    	 active_link =getIntent().getStringExtra("active_link");
     }
     if(getIntent().getStringExtra("title")!=null){
    	 title =getIntent().getStringExtra("title");
     }
     
     initview(this,title);
   
    
     
     
     guarantee_web=(WebView) findViewById(R.id.guarantee_web);
	 
     guarantee_web.getSettings().setJavaScriptEnabled(true);
     guarantee_web.getSettings().setDomStorageEnabled(true);
	 
	 WebViewClient client=new WebViewClient(){
		 public boolean shouldOverrideUrlLoading(WebView view, String url) {
			
			 view.loadUrl(url);
	 	 
			 return false;
	 	 };
	  };
	 
	  guarantee_web.loadUrl(active_link);
	 
	 
	 
	 
	 
	 
	 }
}
