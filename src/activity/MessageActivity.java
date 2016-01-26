package activity;

import java.util.HashMap;
import java.util.Map;

import model.Message;
import model.Registe;
import util.AllUrl;
import util.ToastUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.licai.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
 
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import application.MyApplication;
import application.MySingle;
import base.BaseActivity;

public class MessageActivity extends BaseActivity {
             
	private PullToRefreshListView pull_listview;
	private StringRequest request;
	 private Gson gson=new Gson();
	 
	  private RequestQueue queue;   
	  private SharedPreferences sp;
	private String user_id;
	
	private Message message;
	private MyAdapter adapter;
	private TextView content;
	private TextView time;

	private Handler handler=new Handler(); 
	@Override
	    public void onCreate(Bundle arg0) {
	    	 
	    	super.onCreate(arg0);
	        setContentView(R.layout.activity_message);
	       initview(context, "消息中心");
	       queue=MySingle.getInstance(context).getRequestQueue(context);
	       pull_listview=(PullToRefreshListView) findViewById(R.id.pull_listview);
	       
	       sp=getSharedPreferences("account", MODE_PRIVATE);
	       
	       
	       user_id= sp.getString("user_id", null);
	        
	       getmessage();
	       System.out.println("user_id"+user_id);
	 
      
	       
	       pull_listview.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(final PullToRefreshBase<ListView> refreshView) {
				 
				
				handler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						 
					    getmessage();
						refreshView.onRefreshComplete();
						
						
					}
				}, 2000);
				
				
				
				
			}
		});
	       
	}
	      
	
	
	
	      private void getmessage() {
          request=new StringRequest(Method.POST, AllUrl.url_message, new Listener<String>() {
		 		@Override
						public void onResponse(String response) {
		          		 message=gson.fromJson(response,Message.class);
		          		 adapter=new MyAdapter();
		          		 pull_listview.setAdapter(adapter);
					  
					 }
		           	}, new ErrorListener() {
				        @Override
						public void onErrorResponse(VolleyError error) {
							 System.out.println("网络不通");
			 			}
					}){@Override
					   protected Map<String, String> getParams() throws AuthFailureError {
			    		Map<String, String> map=new HashMap<String, String>();
					 	map.put("page_number", "1");
					  	map.put("operation", "message_list");
					 	map.put("user_id", user_id);
				 	 
			  	 		return map;
					}};
			 	 queue.add(request);   
	 	} 
	
	 class MyAdapter extends BaseAdapter{

		
		@Override
		public int getCount() {
			 
			return message.array.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return message.array.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			
			View view=View.inflate(context, R.layout.message_item,null);
			
			content=(TextView) view.findViewById(R.id.content);
			time=(TextView) view.findViewById(R.id.time);	
			
			content.setText(message.array.get(position).message_content);
			time.setText(message.array.get(position).message_time);
		 
			
			return view;
		}
       }

	   
}	 
		
		
		 
	     
		
		
		
		
		
 
