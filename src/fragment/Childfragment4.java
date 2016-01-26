package fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.fg1;
import model.fg1.Array;
import model.fg4;
import model.fg4.Array4;
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
import activity.YueLiBaoActivity;
import adapter.fg1_adapter;
import adapter.fg4_adapter;
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
import android.widget.LinearLayout;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import application.MySingle;

public class Childfragment4 extends Fragment {
	private PullToRefreshExpandableListView expandableListView4;       
	private Context context;
    private View view;
 	private Handler handler=new Handler();
 	private ACache cache;   //设置缓存
 	private RequestQueue queue;
	
	public StringRequest  request;
    private Gson gson=new Gson();
    private  ArrayList<Array4> fg1_list=new ArrayList<Array4>();
    
    private ArrayList<String> groupList=new ArrayList<String>();
    private ArrayList<Array4> itemList1=new ArrayList<Array4>();
    private ArrayList<Array4> itemList2=new ArrayList<Array4>();
    private ArrayList<Array4> itemList3=new ArrayList<Array4>();
    private ArrayList<Array4> itemList4=new ArrayList<Array4>();
 
    private fg4 f4 ;
	public  ArrayList<ArrayList<Array4>> childlist=new ArrayList<ArrayList<Array4>>();  
    public fg4_adapter adapter4;
	
    private String page4Cache;
   
    private LinearLayout activity4_ll;
	
   
 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		 
		super.onCreate(savedInstanceState);
		context=getActivity();
	}
	
   
          @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	    view=View.inflate(context, R.layout.viewpager4, null);
	    
	       activity4_ll=(LinearLayout) view.findViewById(R.id.activity4_ll);
	    
	       expandableListView4=(PullToRefreshExpandableListView) view.findViewById(R.id.expandableListView4);
	  	   expandableListView4.getRefreshableView().setGroupIndicator(null);
		   expandableListView4.setMode(Mode.PULL_FROM_START); //仅下拉刷新
		   queue=MySingle.getInstance(context).getRequestQueue(context);
	 	   
		      
		  
	       childlist.clear();
		   groupList.clear();
		  
		   groupList.add("正在热销");
		   groupList.add("募集完成");
		   
		   
		   cache =ACache.get(context);
	       
		   page4Cache=cache.getAsString("url_page4");
		   
		   if(page4Cache!=null){
	    	   Gsonwit(page4Cache); 
	       }else{
	    	   getData();  
	       }
		   
		  
		   activity4_ll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent intent=new Intent(context,YueLiBaoActivity.class);
				 intent.putExtra("active_link", f4.active_link);
				 intent.putExtra("title", f4.title);
				 context.startActivity(intent);
				
				
				
			}
		});
	      
	      
		   
		   //下拉刷新
		   expandableListView4.setOnRefreshListener(new OnRefreshListener<ExpandableListView>() {

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
		   
		   
		   expandableListView4.getRefreshableView().setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				 
				Intent intent=new Intent(context,Fg1_detailActivity.class);
				intent.putExtra("product_id", childlist.get(groupPosition).get(childPosition).product_id);
				context.startActivity(intent);
				 
				return false;
			}
		});

		   
		   
		   
		   
		   
	     
	 	    expandableListView4.getRefreshableView().setOnGroupClickListener(new OnGroupClickListener() {
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
	 	   request=new StringRequest(Method.POST, AllUrl.url_page4, new Listener<String>() {
         	@Override
				public void onResponse(String response) {
         		cache.put("url_page4", response, 3600*12);
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
			 	return map;
			}};
	 	 queue.add(request);   
	 	}


    	private void Gsonwit(String response) {
	     
	       f4 = gson.fromJson(response, fg4.class);
	       fg1_list=f4.array;
	      
	       childlist.clear();
	       itemList1.clear();
		   itemList2.clear();
		 
	      
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
	       
	      
	      
	     adapter4=new fg4_adapter(context, groupList, childlist);
	     expandableListView4.getRefreshableView().setAdapter(adapter4);  
	       
	        
	       //设置默认展开
	 	    for(int i=0;i<adapter4.getGroupCount();i++){
	 		   expandableListView4.getRefreshableView().expandGroup(i);
	 	   }
	         
       adapter4.notifyDataSetChanged();

	  }
	  	
	  	
	 

	  
  
}
