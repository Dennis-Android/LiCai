package view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class Myscrollview extends ScrollView {

	public Myscrollview(Context context, AttributeSet attrs) {
		super(context, attrs);
		 
	}

 
  @Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		getParent().requestDisallowInterceptTouchEvent(true);
		return super.dispatchTouchEvent(ev);
  }}