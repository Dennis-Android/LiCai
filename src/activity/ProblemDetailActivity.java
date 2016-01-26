package activity;

import util.AllUrl;

import com.licai.R;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import base.BaseActivity;

public class ProblemDetailActivity extends BaseActivity {
               
	
	 
	
	 private WebView guarantee_web;
	 private String url_problem_detail;

	@Override
    public void onCreate(Bundle arg0) {
    
     super.onCreate(arg0);
     setContentView(R.layout.activity_guarantee); 
     initview(this,"常见问题");
     
     url_problem_detail=getIntent().getStringExtra("url_problem_detail");
     
     
     
     
     guarantee_web=(WebView) findViewById(R.id.guarantee_web);
	 
     guarantee_web.getSettings().setJavaScriptEnabled(true);
     guarantee_web.getSettings().setDomStorageEnabled(true);
	 
	 WebViewClient client=new WebViewClient(){
		 public boolean shouldOverrideUrlLoading(WebView view, String url) {
			
			 view.loadUrl(url);
	 	 
			 return false;
	 	 };
	  };
	 
	  guarantee_web.loadUrl(url_problem_detail);
	 
	 
	 
	 
	 
	 
	 }
}
