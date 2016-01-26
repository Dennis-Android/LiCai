package activity;

import com.licai.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import application.MyApplication;
import base.BaseActivity;

public class LunBoActivity extends BaseActivity {
       
	
	
	public String browser_url;
	public String banner_title;
	
	public String picture_url;
	
	public ImageView  lunbo_image;
	public WebView  lunbo_web;   
	
	
	
	
	
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
            public void onCreate(Bundle arg0) {
        	super.onCreate(arg0);
        	setContentView(R.layout.activity_lunbo);
        	
        	 findViewById();
        	
        	  browser_url=getIntent().getStringExtra("browser_url");
              banner_title=getIntent().getStringExtra("banner_title");
        	  picture_url=getIntent().getStringExtra("picture_url");
        	
        	  initview(this,banner_title);   //设置头部标题
              
             
             lunbo_web.getSettings().setJavaScriptEnabled(true);
             lunbo_web.getSettings().setDomStorageEnabled(true);
           
             lunbo_web.setWebViewClient(new WebViewClient(){
            	 
            	 @Override
             public boolean shouldOverrideUrlLoading(WebView view, String url) {
            		 view.loadUrl(url);
            	return true;
            }});
           
       
             
             lunbo_web.loadUrl(browser_url);
             
           
           
           }






	private void findViewById() {
	 
		lunbo_web=(WebView) findViewById(R.id.lunbo_web);
	}
}
