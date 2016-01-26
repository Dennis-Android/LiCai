package fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.fg1;
import model.fg1.Array;

import util.AllUrl;
import view.MyMyViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
 
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.licai.R;
import com.viewpagerindicator.TabPageIndicator;

import base.BaseFragment;
import adapter.fg1_adapter;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
 
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.ExpandableListView.OnGroupClickListener;
import application.MySingle;

public class Fragment2 extends BaseFragment {
	  
	
	public View view;
	public TextView fragment_title;
    public RequestQueue queue;
    public Context context;
	 
    public  ViewPager  pager;
    
    public static int flag;
    public ArrayList<Fragment> flist=new ArrayList<Fragment>();
    public ArrayList<View> views=new ArrayList<View>();
    public View view1;
    
    public TabPageIndicator indicator;
    public Childfragment1 fragment1;
    public Childfragment2 fragment2;
    public Childfragment3 fragment3; 
    public Childfragment4 fragment4; 
   
  
    public ArrayList<String> parentlist=new ArrayList<String>();
	public ArrayList<String> childlist=new ArrayList<String>();
    
	
	public StringRequest  request;
    private Gson gson=new Gson(); 
    public fg1_adapter adapter1;
    
    public static final String[] titles = new String[] { "定期", "活期", "升活宝", "等额本息"}; 
    public Myadapter adapter;
    public PagerTabStrip  tabstrip;
    
    
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	 	super.onCreate(savedInstanceState);
	 	context=getActivity();
	   }
	
	   @Override
 
	  public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		   checkNet();  //检查网络
		   view=View.inflate(context, R.layout.fragment2,null);
	       findview();
	       queue=MySingle.getInstance(context).getRequestQueue(context);
		    fragment_title.setText("产品列表");  
		    
	        
		      fragment1=new Childfragment1();
			  fragment2=new Childfragment2();
			  fragment3=new Childfragment3();
			  fragment4=new Childfragment4();
			  
			  flist.clear();
			  flist.add(fragment1);
			  flist.add(fragment2); 
			  flist.add(fragment3);
			  flist.add(fragment4);
		     
			
	 	  
			  
			  //注意，这里一定要用getChildFragmentManager,因为fragment里嵌套了viewpager，viewpager里嵌套了fragment
		        adapter=new Myadapter(getChildFragmentManager());
			    pager.setAdapter(adapter);
			    indicator.setViewPager(pager);
			  
		     indicator.setOnPageChangeListener(new OnPageChangeListener() {
		 		@Override
				public void onPageSelected(int arg0) {
	 	 			 
		 	 }
	   		@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
	  		
				}
		  		
		 		@Override
				public void onPageScrollStateChanged(int arg0) {
				    
					
				}
			}); 
	 	     return view;
        }
    
	   private void findview() {
		     fragment_title= (TextView) view.findViewById(R.id.fragment_title);
		     indicator=(TabPageIndicator) view.findViewById(R.id.indicator);
	 	     pager=(ViewPager ) view.findViewById(R.id.pager);
 	   }
 	   
	   
	   
	
	   
	   //pager适配器 
	    class Myadapter extends FragmentStatePagerAdapter {

			public Myadapter(FragmentManager fm) {
				super(fm);
     		  }
 		   @Override
			public Fragment getItem(int arg0) {
	 	     
		 		return flist.get(arg0);
			}
     		@Override
			public int getCount() {
			 
				return flist.size();
			}
            
     	
     		@Override
	        public CharSequence getPageTitle(int position) {
	        	 
	        	return titles[position];
	        }
	    
	     /*  @Override
	       public int getItemPosition(Object object) {
            return POSITION_NONE;
	    }*/
	    
	     }
	   
	   
}
