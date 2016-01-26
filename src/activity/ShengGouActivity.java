package activity;

import util.AllUrl;

import com.licai.R;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import base.BaseActivity;

public class ShengGouActivity extends BaseActivity {
               
	
	private int  product_id;
	
	 private WebView shenggou_web;

	@Override
    public void onCreate(Bundle arg0) {
    
     super.onCreate(arg0);
     setContentView(R.layout.activity_jiangli); 
     initview(this,"已申购订单");
     
     product_id=getIntent().getIntExtra("product_id",0);
     
   
     shenggou_web=(WebView) findViewById(R.id.jiangli_web);
	 shenggou_web.getSettings().setJavaScriptEnabled(true);
     shenggou_web.getSettings().setDomStorageEnabled(true);
	 
	 WebViewClient client=new WebViewClient(){
		 public boolean shouldOverrideUrlLoading(WebView view, String url) {
			
			 view.loadUrl(url);
	 	 
			 return false;
	 	 };
	  };
	 
   shenggou_web.loadUrl(AllUrl.url_Purchase+"?product_id="+product_id+"&phone_number=");
	 
	 
	 
	 
	 
	 
	 }
}
