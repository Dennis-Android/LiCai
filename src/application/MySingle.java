package application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.content.Context;

public class MySingle {
          
	  private static MySingle mysingle;
	  private Context context;
	  private RequestQueue queue;
	  
	  
	  private MySingle(Context context){
	      this.context=context;
	      
	  }
    
	  
	  public static synchronized MySingle getInstance(Context context){
		 if(mysingle==null){
			 mysingle=new MySingle(context);
		  }
	 	  return mysingle;
	  }
        
	  
	  public    RequestQueue getRequestQueue(Context context){
		  if(queue==null){
			  queue=Volley.newRequestQueue(context);
		   }
		 
		  return queue;
		  
	  }
	  
	 
	  
}
