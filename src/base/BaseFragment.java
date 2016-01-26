package base;

import com.licai.R;

import util.ToastUtils;
import android.content.Context;
import android.os.Bundle;
 
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public   class  BaseFragment extends Fragment {
              
	public Context context;
	public View view;
	
	@Override
	public void onCreate( Bundle savedInstanceState) {
	 	super.onCreate(savedInstanceState);
	 	context=getActivity();
          	
	   }
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	 
		 view=View.inflate(context, R.layout.fragment_titlebar,null);
		 
		 return view;
        }
	
	 
	 
	
	
	 
	
	
	
	//检查网络
    public void checkNet(){
		if(util.NetworkUtils.isNetworkAvailable(context)==false){
			 ToastUtils.Toast(context, "请检查网络");
		}
	}
	
	
    
    
        
	
 
	
}
