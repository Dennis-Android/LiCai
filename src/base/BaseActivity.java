package base;

import com.licai.R;

import android.content.Context;
import android.os.Bundle;
 
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
 
import android.widget.ImageView;
import android.widget.TextView;

public    class BaseActivity extends FragmentActivity {
          
 	public ImageView back;
    public TextView textview_title;
	public Context context;
	public View view;
	   @Override
	   public void onCreate( Bundle arg0) {
	 	super.onCreate(arg0);
	    setContentView(R.layout.titlebar);
	 	context=this;
	   
	     
	   }
  
	 
	   public  void  initview(Context context,String title) {
	     back=(ImageView)findViewById(R.id.goback);
		 textview_title=(TextView)findViewById(R.id.text_title);
		 textview_title.setText(title);
	 	 back.setOnClickListener(new OnClickListener() {
	 		@Override
			public void onClick(View v) {
			  onBackPressed();
	 	}
		});
	    }	   
    
	                                                                                        
	    
	    
	   
	   
	   
	   
	   
	   
}
