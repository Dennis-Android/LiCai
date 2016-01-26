package activity;

import java.util.ArrayList;

import com.licai.R;
 
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GuideActivity extends Activity {
	public ViewPager guide_viewpager;           
	public ArrayList<View> viewcontaniers;
    public Button guide_enter;
    public SharedPreferences sp;
    public View view1;
    public View view2;
    public View view3;
    

    @Override 
    protected void onCreate(Bundle savedInstanceState) {    	 
    	super.onCreate(savedInstanceState);       
	    setContentView(R.layout.activity_guide);
	  
	    guide_viewpager=(ViewPager) findViewById(R.id.guide_viewpager);	    
	    viewcontaniers=new ArrayList<View>();
	   view1=View.inflate(this, R.layout.guide_view1, null);
	   view2=View.inflate(this, R.layout.guide_view2, null);
	   view3=View.inflate(this, R.layout.guide_view3, null);
        
	    viewcontaniers.add(view1);
	    viewcontaniers.add(view2);
	    viewcontaniers.add(view3);
    
        guide_viewpager.setAdapter(new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				 
				return arg0==arg1;
			}
			
			@Override
			public int getCount() {			 
			 return viewcontaniers.size();
			}
		  
			@Override
		  public Object instantiateItem(ViewGroup container, int position) {
			 container.addView(viewcontaniers.get(position));
			return viewcontaniers.get(position);
		}
       
			@Override
        public void destroyItem(ViewGroup container, int position,
        		Object object) {
        	   container.removeView((View) object);       	 
        } 
        });
        //存储设置
    
        sp=getSharedPreferences("config", MODE_PRIVATE);
        //进入按钮
        guide_enter=(Button)view3.findViewById(R.id.guide_enter);
        guide_enter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Editor editor=sp.edit();
				 editor.putBoolean("splashed", true);
				 editor.commit();
				 Intent intent=new Intent(GuideActivity.this,HomeActivity.class);
				 startActivity(intent); 
				 finish();
			   
			
			}
		});
    
    
    
    }  




}
