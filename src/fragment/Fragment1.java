package fragment;

 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Home;
import model.Home.Map1;
import model.LunBo;
import model.LunBo.Array;

import util.ACache;
import util.AllUrl;
import view.CircleProgress;
import view.ImageCycleView;

import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
 
import com.google.gson.Gson;
import com.licai.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import base.BaseFragment;
 
import activity.GouMaiActivity;
import activity.LoginActivity;
import activity.LunBoActivity;
import activity.PreLoginActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
 
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import application.MyApplication;
import application.MySingle;
 
 




public class Fragment1 extends  BaseFragment{
	public Map1 map1; 
	public ACache cache;   //设置缓存
	public RequestQueue queue;
	
	
	private CircleProgress   circleProgress;
	private Handler handler=new Handler();
	
	private StringRequest  request;
	private StringRequest request1;
	public ArrayList<Array> lunbolist=new ArrayList<LunBo.Array>();
	private Gson gson=new Gson();
	
	public ArrayList<String>  imagelist=new ArrayList<String>();
	public String lunbo_cache;    //缓存的轮播json数据
	public String home_cache;      //缓存的中间数据
	
	
	public Context context;
	public View view;
	public TextView fragment_title;
	public ImageCycleView	CycleView;
	  
	public double raise;
	public TextView product_name;
	public TextView annual_rate;
    public TextView experience_amount;
    public TextView activity_tag;
	public TextView operation_tag;
    public ImageView standard_tag_images1;
 	public TextView standard_tag1;
    public ImageView standard_tag_images2;
  	public TextView standard_tag2;
 	public ImageView standard_tag_images3;
    public TextView standard_tag3;
    private Button button_title;
    private boolean login; 
	
    private SharedPreferences sp;
    private int progress;   
	
    @Override
	    public void onCreate(Bundle savedInstanceState) {
	 	super.onCreate(savedInstanceState);
	 	context=getActivity();
	    }
	
	   @Override
 
	  public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
	     checkNet();  //检查网络 
	     cache=ACache.get(context);
	     
	     
	    if(view==null){ 
		 view=View.inflate(context, R.layout.fragment1,null);
		 } 
		 
		 ViewGroup parent=(ViewGroup) view.getParent();
		 if(parent!=null){
			 parent.removeView(view);
		     return view;
		  } 
	     
	     findviewbyid();
     	 fragment_title.setText("首页"); 
	  	 
     	  sp=context.getSharedPreferences("account", context.MODE_PRIVATE);
     	 login=sp.getBoolean("login", false);
     	  
     	  
     	  
	  	queue=MySingle.getInstance(context).getRequestQueue(context);
         	 	 
	 	 
	    lunbo_cache=cache.getAsString("AllUrl.url_lunbo");
	    home_cache=cache.getAsString("AllUrl.url_fragment");
	    
	    //如果缓存了就直接解析，否则请求网络
	    if(lunbo_cache==null){
	  		getLunboData();//请求网络	
	  	}else{
	  		GsonWith(lunbo_cache);
	  	}
	  	
	    
	    if(home_cache==null){
	    	 getHomeData();
	    }else{
	    	GsonHomeWith(home_cache);
	    }
	  	  
	    
	    next();
	    
	    
	    
	    
	    
          button_title.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				if(login==false){
					Intent intent=new Intent(context,PreLoginActivity.class);
				     context.startActivity(intent);
					
					
				}else{
					Intent intent=new Intent(context,GouMaiActivity.class);
				    intent.putExtra("product_name", map1.product_name);
				    intent.putExtra("raise", raise);  
				    intent.putExtra("experience_amount", map1.experience_amount);
				    intent.putExtra("financial_period", map1.financial_period);  
				    intent.putExtra("value_date", map1.value_date);       
				    intent.putExtra("product_id", map1.product_id);
				    intent.putExtra("starting_amount", map1.starting_amount);
				     
				    context.startActivity(intent);
			
				}
				
				
				    
			
			
			
			
			
			}
		});
	    
          
			  
	     	  
			  
	    
	     return view;
	       
	   }
	    
	   @Override
	public void onPause() {
		 
		super.onPause();
		CycleView.pushImageCycle();
	  }
	    @Override
	  public void onResume() {
	 
		super.onResume();
		CycleView.startImageCycle();
	   }
	   
	   @Override
	    public void onDestroyView() {
	    	super.onDestroyView();
	    	CycleView.pushImageCycle(); 
	     }  
	    
	   private void getHomeData() {
 	   request1=new StringRequest(Method.POST, AllUrl.url_fragment, new Listener<String>() {

			@Override
			public void onResponse(String response) {
		 	    cache.put("AllUrl.url_fragment",response, 3600*3);
		 	    GsonHomeWith(response);
	 	}
 		
     	}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				 System.out.println("网络不通");
	 		}
		}){@Override
		   protected Map<String, String> getParams() throws AuthFailureError {
			
			Map<String, String> map=new HashMap<String, String>();
		 	map.put("app_version_id", "1700");
		 	map.put("user_id", "");
	 		return map;
		}};
		   
	 queue.add(request1);   
     }

	private void getLunboData() {
		   request=new StringRequest(Method.POST, AllUrl.url_lunbo, new Listener<String>() {
 		@Override
			public void onResponse(String response) {
	 		    cache.put("AllUrl.url_lunbo",response, 3600*3);
			 	GsonWith(response);
			}
 	}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				 System.out.println("网络不通");
	 		}
		}){@Override
		   protected Map<String, String> getParams() throws AuthFailureError {
			
			Map<String, String> map=new HashMap<String, String>();
		 	map.put("app_version_id", "1700");
		 	map.put("app_market_id", "3");
		 	map.put("app_platform_id", "1");
	 		return map;
		}};
		   
 	queue.add(request);   
		
	}

	private void findviewbyid() {
		 
		 fragment_title= (TextView) view.findViewById(R.id.fragment_title);
		 CycleView=(ImageCycleView) view.findViewById(R.id.CycleView);   
		
		 product_name=(TextView) view.findViewById(R.id.product_name); 
		 annual_rate=(TextView) view.findViewById(R.id.annual_rate); 
		 experience_amount=(TextView) view.findViewById(R.id.experience_amount); 
		 activity_tag=(TextView) view.findViewById(R.id.activity_tag); 
		 operation_tag=(TextView) view.findViewById(R.id.operation_tag); 
		 
		 standard_tag_images1=(ImageView) view.findViewById(R.id.standard_tag_images1); 
		 standard_tag1=(TextView) view.findViewById(R.id.standard_tag1);
		 standard_tag_images2=(ImageView) view.findViewById(R.id.standard_tag_images2);
		 standard_tag2=(TextView) view.findViewById(R.id.standard_tag2);
		 standard_tag_images3=(ImageView) view.findViewById(R.id.standard_tag_images3);
		 standard_tag3=(TextView) view.findViewById(R.id.standard_tag3);
		  
		 button_title=(Button) view.findViewById(R.id.button_title);
     
	     circleProgress=(CircleProgress) view.findViewById(R.id.circleProgress);    
		 circleProgress.setRoundWidth(10);
			
	 
	
	}
 
	     //解析 
			private void GsonWith(String response){
			lunbolist=gson.fromJson(response, LunBo.class).array;
			imagelist.clear();
			for(int i=0;i<lunbolist.size();i++){
				imagelist.add(lunbolist.get(i).picture_url);
			}
		    
			CycleView.setImageResources(imagelist, mAdCycleViewListener);//设置资源和监听事件  
	  		}
 	
			//解析
		  	private void GsonHomeWith(String response) {
				map1=(Map1) gson.fromJson(response, Home.class).map;
		    	
				product_name.setText(map1.product_name); 
			 	
				 raise=Double.parseDouble(map1.annual_rate);
				 
				 annual_rate.setText(String.valueOf(raise*100)+"%"); 
				 
	 			 experience_amount.setText(String.valueOf(map1.experience_amount)); 
				 activity_tag.setText(map1.activity_tag);  
				 operation_tag.setText(map1.operation_tag); 
				
			    String [] a=map1.standard_tag.split("&");
			    String []b=map1.standard_tag_images.split("&");
			    
			    standard_tag1 .setText(a[0]);
			    standard_tag2 .setText(a[1]);
			    standard_tag3 .setText(a[2]);
			 
			  ImageLoader.getInstance().displayImage(b[0], standard_tag_images1, MyApplication.option);
			  ImageLoader.getInstance().displayImage(b[1], standard_tag_images2, MyApplication.option);
			  ImageLoader.getInstance().displayImage(b[2], standard_tag_images3, MyApplication.option);
	 		  button_title.setText(map1.button_title);
	 		
		  	
		  	
		  	}
			
	 	
			
			

	//轮播监听
	   private view.ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new view.ImageCycleView.ImageCycleViewListener() {  
	      	  
	        @Override  
	        public void onImageClick(int position, View imageView) {  
	            Intent intent=new Intent(context,LunBoActivity.class); 
	        	intent.putExtra("browser_url",lunbolist.get(position).browser_url);
	        	intent.putExtra("banner_title", lunbolist.get(position).banner_title);
	        	intent.putExtra("picture_url", lunbolist.get(position).picture_url);
	        	context.startActivity(intent);
	      }  
	  
	        @Override  
	        public void displayImage(String imageURL, ImageView imageView) {  
	            ImageLoader.getInstance().displayImage(imageURL, imageView,MyApplication.option);// 此处本人使用了ImageLoader对图片进行加装！  
	        }  
	    };
	 
	   
	   
		private void next() {
			if(progress<90){
				 handler.postDelayed(new Runnable() {
						
						@Override
						public void run() {
							progress=progress+3; 
							circleProgress.setProgress(progress);
						 	System.out.println("progress"+progress);
							next();
						}

					}, 70);
		 		
			  } 
		 	
		}
	   
	   
	   
}
