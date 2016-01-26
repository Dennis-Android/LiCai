package util;

import android.content.Context;

public   class ToastUtils {
          
	
	 public static void Toast(Context context,String text){
		  android.widget.Toast.makeText(context, text, 0).show();
          	
	
}
}