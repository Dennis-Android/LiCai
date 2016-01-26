package activity;

import util.AllUrl;

import com.licai.R;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import base.BaseActivity;

public class GuaranteeActivity extends BaseActivity {
               
	
	private int product_id;
	
	 private WebView guarantee_web;

	@Override
    public void onCreate(Bundle arg0) {
    
     super.onCreate(arg0);
     setContentView(R.layout.activity_guarantee); 
     initview(this,"资金保障");
     
     product_id=getIntent().getIntExtra("product_id",0);
     
     
     
     guarantee_web=(WebView) findViewById(R.id.guarantee_web);
	 
     guarantee_web.getSettings().setJavaScriptEnabled(true);
     guarantee_web.getSettings().setDomStorageEnabled(true);
	 
	 WebViewClient client=new WebViewClient(){
		 public boolean shouldOverrideUrlLoading(WebView view, String url) {
			
			 view.loadUrl(url);
	 	 
			 return false;
	 	 };
	  };
	 
	  guarantee_web.loadUrl(AllUrl.url_guarantee+product_id);
	 
	 
	 
	 
	 
	 
	 }
}
