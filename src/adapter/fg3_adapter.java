package adapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

import model.fg1;
import model.fg1.Array;
import model.fg2.Array2;
import model.fg3.Array3;

import com.android.volley.Cache;
import com.licai.R;

import adapter.fg1_adapter.ViewHolder;
import android.R.string;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class fg3_adapter extends BaseExpandableListAdapter {
	    public Context context; 
	    public ArrayList<String> groupList=new ArrayList<String>();
	 	public  ArrayList<ArrayList<Array3>> childlist=new ArrayList<ArrayList<Array3>>();  
	   	public TextView child_product_name;
	 	public DecimalFormat format=new DecimalFormat("######0.00");
		private TextView parent2_text;
	   
	 	
	 	public fg3_adapter(Context context, ArrayList<String> groupList,ArrayList<ArrayList<Array3>> childlist2 ){
		this.context=context;
		this.childlist=childlist2;
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
				convertView=View.inflate(context, R.layout.child3, null);
				holder.child3_product_name=(TextView) convertView.findViewById(R.id.child3_product_name);
				holder.child3_operation_tag=(TextView) convertView.findViewById(R.id.child3_operation_tag);
				holder.child3_annual_rate=(TextView) convertView.findViewById(R.id.child3_annual_rate);
				holder.child3_annual_rate_top=(TextView) convertView.findViewById(R.id.child3_annual_rate_top);
				holder.child3_starting_amount=(TextView) convertView.findViewById(R.id.child3_starting_amount);
 
			    
				convertView.setTag(holder);
		  		 
		 		
			}else{holder=(ViewHolder) convertView.getTag();
			//最关键的一行代码
			holder.child3_operation_tag.setBackgroundResource(R.drawable.product_red);
		  	}
			
			holder.child3_product_name.setText(childlist.get(groupPosition).get(childPosition).product_name);
			holder.child3_operation_tag.setText(childlist.get(groupPosition).get(childPosition).operation_tag);
		   
	 
			 //四个标签
		 	 if(childlist.get(groupPosition).get(childPosition).operation_tag.equals("正在热销")){
		 		holder.child3_operation_tag.setBackgroundResource(R.drawable.product_red);
		   	
		     }else
            if(childlist.get(groupPosition).get(childPosition).operation_tag.equals("售罄") ){
        	    
           	holder.child3_operation_tag.setBackgroundResource(R.drawable.product_gray);
		 	  
           	}else
           	if(childlist.get(groupPosition).get(childPosition).operation_tag.equals("已还款") ){ 
           		 
           	holder.child3_operation_tag.setBackgroundResource(R.drawable.product_green);
           	
           	}else
           
           	if(childlist.get(groupPosition).get(childPosition).operation_tag.equals("预告") ){
           		 
           	holder.child3_operation_tag.setBackgroundResource(R.drawable.product_yellow);
         	 } 
	 
	 
			String  annual_rate1=format.format((double) childlist.get(groupPosition).get(childPosition).annual_rate*100); 
			String  annual_rate2=format.format((double) (childlist.get(groupPosition).get(childPosition).rate_top*100)); 
			System.out.println(childlist.get(groupPosition).get(childPosition).rate_top);
			
			
			holder.child3_annual_rate.setText(String.valueOf(annual_rate1)+"%");
		  	holder.child3_annual_rate_top.setText("-"+String.valueOf(annual_rate2)+"%");
			
			int starting_amount=(int) Double.parseDouble(childlist.get(groupPosition).get(childPosition).starting_amount);
	 	
			holder.child3_starting_amount.setText(String.valueOf(starting_amount)+"元");
			
			 
		 
		 
		 
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
			 
		    public TextView child3_product_name;
			public TextView child3_operation_tag;
			public TextView child3_annual_rate; 
  	        public TextView child3_annual_rate_top;
			public TextView child3_starting_amount;
			 
			
			
			
			
			
			
			
			
		}
		 

}
