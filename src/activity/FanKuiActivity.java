package activity;

import util.ToastUtils;

import com.licai.R;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import base.BaseActivity;

public class FanKuiActivity extends BaseActivity {

	private EditText fankui;
 	private Button send_fankui;
	
	
 	
 	
 	
 	
 	
            @Override
            public void onCreate(Bundle arg0) {
             
            	super.onCreate(arg0);
            	setContentView(R.layout.activity_fankui);
            	
            	initview(context, "反馈");
            	
            	fankui=(EditText) findViewById(R.id.fankui);
            	send_fankui=(Button) findViewById(R.id.send_fankui);
                 
            	
              
            	send_fankui.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
                    String content=fankui.getText().toString().trim();

                    if(TextUtils.isEmpty(content)){
                    	ToastUtils.Toast(context, "请填写反馈后再发送");
                    	
                    }else{
                    	fankui.setText("");
                    	ToastUtils.Toast(context, "谢谢您的反馈");
                    }
                    
                    
           		
						
					}
				});
            	
            	
            	
            	
            	
            	
            
            
            
            
            
            }
            
            
            
            








}
