package activity;

import com.licai.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import base.BaseActivity;

public class BanklistActivity extends BaseActivity {
          
	
	 private Button add_card_btn;
	
	
	
	     @Override
	    public void onCreate(Bundle arg0) {
	     	super.onCreate(arg0);
	    setContentView(R.layout.activity_banklist);
	     	
	    initview(context, "银行卡管理");
	    add_card_btn=(Button) findViewById(R.id.add_card_btn);
	     
	     
	    add_card_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(context,AddCardActivity.class); 
				startActivity(intent);
			 	
				
				
			}
		});
	     
	    
	    
	    
	    
	    
	     
	     
	     
	     
	     
	     }
	   
	
}
