package activity;

import util.AllUrl;

import com.licai.R;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import base.BaseActivity;

public class ProjectActivity extends BaseActivity {
               
	
	private int product_id;
	
	 private WebView project_web;

	@Override
    public void onCreate(Bundle arg0) {
    
     super.onCreate(arg0);
     setContentView(R.layout.activity_project); 
     initview(this,"项目描述");
     
     product_id=getIntent().getIntExtra("product_id",0);
     
     
     
     project_web=(WebView) findViewById(R.id.project_web);
	 
     project_web.getSettings().setJavaScriptEnabled(true);
     project_web.getSettings().setDomStorageEnabled(true);
	 
	 WebViewClient client=new WebViewClient(){
		 public boolean shouldOverrideUrlLoading(WebView view, String url) {
			
			 view.loadUrl(url);
	 	 
			 return false;
	 	 };
	  };
	 
	  project_web.loadUrl(AllUrl.url_project+product_id);
	 
	 
	 
	 
	 
	 
	 }
}
