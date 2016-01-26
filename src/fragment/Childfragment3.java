package fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.fg2;
import model.fg2.Array2;
import model.fg3;
import model.fg3.Array3;
import util.ACache;
import util.AllUrl;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.licai.R;

import activity.Fg1_detailActivity;
import activity.ShengHuoBaoActivity;
import adapter.fg1_adapter;
import adapter.fg2_adapter;
import adapter.fg3_adapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.LinearLayout;
import application.MySingle;

public class Childfragment3 extends Fragment {
	 
	
	private fg3_adapter adapter3;
    private PullToRefreshExpandableListView expandableListView3;       
	private Context context;
    private View view;
 	private Handler handler=new Handler();
 	private ACache cache;   //设置缓存
 	private RequestQueue queue;
    public StringRequest  request;
    private Gson gson=new Gson();
    private  ArrayList<Array3> fg1_list=new ArrayList<Array3>();
    private  ArrayList<String> groupList=new ArrayList<String>();
    private  ArrayList<Array3> itemList1=new ArrayList<Array3>();
    private  ArrayList<Array3> itemList2=new ArrayList<Array3>();
    private  ArrayList<Array3> itemList3=new ArrayList<Array3>();
    private  ArrayList<Array3> itemList4=new ArrayList<Array3>();
    private  ArrayList<ArrayList<Array3>> childlist=new ArrayList<ArrayList<Array3>>();  
   
    private fg3 f3;
    private LinearLayout activity_ll;  //喇叭
    
    private String page3Cache;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		 
		super.onCreate(savedInstanceState);
		context=getActivity();
	}
	
   
          @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	    view=View.inflate(context, R.layout.viewpager3, null);
	    
	       activity_ll=(LinearLayout) view.findViewById(R.id.activity_ll);
	       expandableListView3=(PullToRefreshExpandableListView) view.findViewById(R.id.expandableListView3);
	       expandableListView3.getRefreshableView().setGroupIndicator(null);
	       expandableListView3.setMode(Mode.PULL_FROM_START); //仅下拉刷新
		   queue=MySingle.getInstance(context).getRequestQueue(context);
	 	  
	       childlist.clear();
		   groupList.clear();
		   groupList.add("正在热销");
	       groupList.add("募集完成");
		    
	       cache =ACache.get(context);
	       
	      page3Cache=cache.getAsString("url_page3");
	       
	       
	       if(page3Cache!=null){
	    	   Gsonwit(page3Cache); 
	       }else{
	    	   getData();  
	       }
		  
		   
	     
		   activity_ll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		 	
				Intent intent=new Intent(context,ShengHuoBaoActivity.class);
				intent.putExtra("title", f3.title);
				intent.putExtra("url", f3.url);
				context.startActivity(intent);
				
			}
		});
		   
		   
		   
		   
		   
		  //下拉刷新
		   expandableListView3.setOnRefreshListener(new OnRefreshListener<ExpandableListView>() {

			@Override
			public void onRefresh(
					final PullToRefreshBase<ExpandableListView> refreshView) {
				      
				  handler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						 getData();    
						 refreshView.onRefreshComplete();
					}
				}, 2000);
				  
		 		  
			  }
		   });
		   
		   
		   expandableListView3.getRefreshableView().setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				 
				Intent intent=new Intent(context,Fg1_detailActivity.class);
				intent.putExtra("product_id", childlist.get(groupPosition).get(childPosition).product_id);
				context.startActivity(intent);
				 
				return false;
			}
		});

		   
		   
		   
		   
		   
	     
	 	    expandableListView3.getRefreshableView().setOnGroupClickListener(new OnGroupClickListener() {
	  	 	@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
		 		    return false;
			 }
		     });  
	     
	          return view;
   }
  
	   //获取数据
	   public void getData() {
	 	   request=new StringRequest(Method.POST, AllUrl.url_page3, new Listener<String>() {
      	@Override
				public void onResponse(String response) {
 	     		
      		    cache.put("url_page3", response, 3600*12);
      		    Gsonwit(response);
		 	   
      	
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
			 	map.put("user_id", "0");
			 	map.put("app_market_id", "3");
				map.put("is_current", "Y");
	  	 		return map;
			}};
	 	 queue.add(request);   
	 	}


 	private void Gsonwit(String response) {
	      
 		 f3=gson.fromJson(response, fg3.class);
 		
 		fg1_list=f3.array;
	      
	      
	       childlist.clear();
	       itemList1.clear();
		   itemList2.clear();
		   itemList3.clear();
		   itemList4.clear();
	      
	      
	      for(int i=0;i<fg1_list.size();i++){
	    	  
	    	   
	    	  if(fg1_list.get(i).series.equals("正在热销")){
	    		  itemList1.add(fg1_list.get(i));
	     	   }
	    	 
	    	  if(fg1_list.get(i).series.equals("募集完成")){
	    		  itemList2.add(fg1_list.get(i));
	     	   }
	    
	      }
	      
	      
	      childlist.add(itemList1);
	      childlist.add(itemList2);
	     
	    
	      
	      adapter3=new fg3_adapter(context, groupList, childlist);
	      expandableListView3.getRefreshableView().setAdapter(adapter3);  
	       
	        
	       //设置默认展开
	 	    for(int i=0;i<adapter3.getGroupCount();i++){
	 		   expandableListView3.getRefreshableView().expandGroup(i);
	 	   }
	         
         adapter3.notifyDataSetChanged(); 

	 
	 

   
          
          
          }
  
}
