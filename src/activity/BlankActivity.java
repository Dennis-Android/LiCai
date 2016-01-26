package activity;

import util.ToastUtils;

import com.licai.R;

import android.os.Bundle;
import base.BaseActivity;

public class BlankActivity extends BaseActivity {
             
	
	    private String title;

		@Override
	    public void onCreate(Bundle arg0) {
	    	 
	    	super.onCreate(arg0);
	        setContentView(R.layout.activity_blank);
	    
	        title=getIntent().getStringExtra("title");
	        
	        initview(context, title);
	       ToastUtils.Toast(context, "暂无数据");
	    
	    
	    
	    }
	    
	    
	    
	
	
}
