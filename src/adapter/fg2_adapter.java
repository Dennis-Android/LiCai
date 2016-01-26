package adapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

import model.fg1;
import model.fg1.Array;
import model.fg2.Array2;

import com.android.volley.Cache;
import com.licai.R;

import android.R.string;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class fg2_adapter extends BaseExpandableListAdapter {
	    public Context context; 
	    public ArrayList<String> groupList=new ArrayList<String>();
	 	public  ArrayList<ArrayList<Array2>> childlist=new ArrayList<ArrayList<Array2>>();  
	   	public TextView child_product_name;
	 	public DecimalFormat format=new DecimalFormat("######0.00");
		private TextView parent2_text;
	   
	 	
	 	public fg2_adapter(Context context, ArrayList<String> groupList,ArrayList<ArrayList<Array2>> childlist ){
		this.context=context;
		this.childlist=childlist;
	    this.groupList=groupList;
	   
	 
	}
	    
	    @Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			
			View view=View.inflate(context, R.layout.parent2, null);
			
			parent2_text=(TextView) view.findViewById(R.id.parent2_text);
		 	
			parent2_text.setText(groupList.get(groupPosition));
			return view;
		}
		
		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}
		
		@Override
		public int getGroupCount() {
			return groupList.size();
		}
		
		@Override
		public Object getGroup(int groupPosition) {
		 
			return groupList.get(groupPosition);
		}
	   
		@Override
		public int getChildrenCount(int groupPosition) {
		    try {
		    	return childlist.get(groupPosition).size();
		} catch (Exception e) {
			           
		}
		return groupPosition; 
	 
		}
		
		@Override
		public long getChildId(int groupPosition, int childPosition) {
			 
			return childPosition;
		}
		
		@Override
		public Object getChild(int groupPosition, int childPosition) {
			
			return childlist.get(groupPosition).get(childPosition);
		}
		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			
	 	
			ViewHolder holder=null; 
			if(convertView==null){
				holder=new ViewHolder();
			 		convertView=View.inflate(context, R.layout.child2_2, null);
					holder.child1_2_product_name=(TextView) convertView.findViewById(R.id.child1_2_product_name);
					holder.child1_2_annual_rate=(TextView) convertView.findViewById(R.id.child1_2_annual_rate);
					holder.child1_2_person_amount=(TextView) convertView.findViewById(R.id.child1_2_person_amount);
				
		 	  	  convertView.setTag(holder);
		  		
			}else{holder=(ViewHolder) convertView.getTag();
		 
		  	}
			
		 
		    String  annual_rate=format.format((double) childlist.get(groupPosition).get(childPosition).annual_rate*100); 
	      
		 
		 
	 	   
	 	      holder.child1_2_product_name.setText(childlist.get(groupPosition).get(childPosition).product_name+"期");
		      holder.child1_2_annual_rate.setText(String.valueOf(annual_rate)+"%");
		      holder.child1_2_person_amount.setText(String.valueOf(childlist.get(groupPosition).get(childPosition).person_amount)+"人"); 
	 	   
	 	   
	 	 
		 
			return convertView;
		}
		
	
	 	@Override
		public boolean areAllItemsEnabled() {
			 
			return true;
		}
		@Override
		public boolean hasStableIds() {
			 
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			 
			return true;
		}
        
		
	                                            	
		static class ViewHolder{
			 
		    public TextView child1_2_product_name;
			public TextView child1_2_annual_rate;
			public TextView child1_2_person_amount; 
  	
		}
		 

}
