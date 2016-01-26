package application;
  
import java.util.ArrayList;
 
import com.licai.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
 
public class MyApplication extends Application {
	public static ArrayList<Activity>  activity_list=new ArrayList<Activity>();
	public static ImageLoaderConfiguration config; 
	public static DisplayImageOptions option;       
    public Context context;          
	
 
	    @Override
	    public void onCreate() {
	    	super.onCreate();
	        //配置
	     	config=ImageLoaderConfiguration.createDefault(this);
	        ImageLoader.getInstance().init(config);
	     	
	         //显示图片的参数设置
			 option=new DisplayImageOptions.Builder()
			 .showImageOnFail(R.drawable.loading_failure) //加载失败显示的图片	    	 
		     .showImageOnLoading(R.drawable.loading)  //加载中显示的图片
		     .cacheInMemory(true) //受否缓存在内存中	    	 
		     .cacheOnDisk(true)   //受否缓存在SK卡中	    	 
		     .bitmapConfig(Bitmap.Config.RGB_565)
		     .build();
	   
	    }
	
	
	
	   //添加activity
	   public static void addActivity(Activity activity){
		   activity_list.add(activity); 
	   }
	   
	    //移除activity
 	    public static void removeActivity(Activity activity){
	    	 activity_list.remove(activity);
	    }
	     
 	    //结束所有activity
	    public static void finishAll(){
	    	for(Activity activity:activity_list){
	    		if(!activity.isFinishing()){
	    			activity.finish();
	    		}
	    	}
	    }
	    
	    
	    
	    
	    
	    
	
	
}
