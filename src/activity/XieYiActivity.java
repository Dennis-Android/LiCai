package activity;

import util.AllUrl;

import com.licai.R;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import base.BaseActivity;

public class XieYiActivity extends BaseActivity {
               
	
	 
	
	 private WebView guarantee_web;
	private String title;
	private String url;

	@Override
    public void onCreate(Bundle arg0) {
    
     super.onCreate(arg0);
     setContentView(R.layout.activity_guarantee); 
     
     title=getIntent().getStringExtra("title");
     url=getIntent().getStringExtra("url");
     
     
     
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
	 
	  guarantee_web.loadUrl(url);
	 
	 
	 
	 
	 
	 
	 }
}
