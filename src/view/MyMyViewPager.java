package view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyMyViewPager extends ViewPager{

	public MyMyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		 
	}

	public MyMyViewPager(Context context) {
		super(context);
	 
	}
    
	  @Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		getParent().requestDisallowInterceptTouchEvent(true);
		return super.dispatchTouchEvent(ev);
	}
	
	      
 
	
	 
      
      
}

