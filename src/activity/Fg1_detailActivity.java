package activity;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import model.Detail;
import model.Detail.Map2;
import model.Home;
import model.Home.Map1;

import util.AllUrl;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import com.licai.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import application.MyApplication;
import application.MySingle;
import base.BaseActivity;

public class Fg1_detailActivity extends BaseActivity implements OnClickListener {
	public RequestQueue queue;          
	 public StringRequest  request;
	 private Gson gson=new Gson();
	 public DecimalFormat format=new DecimalFormat("######0.00");
	 private Dialog dialog;
	 
	 public TextView  fg1_financial_period; //期限
	 public TextView  fg1_annual_rate;          //折扣
	 public TextView  fg1_starting_amount;      //起购金额
	 public TextView fg1_amount_raised;        //融资金融
     public TextView fg1_has_sell;           //已售出
	 public TextView fg1_selling_amount;         //可售出
     public TextView fg1_shouyi;             //收益
	  public Button fg1_experience_amount;   //送体验金
     public  TextView fg1_cur_server_time;             //起息日
     public TextView fg1_fake_repayment_time;         
	  public TextView fg1_repayment_desc;               //还款方式
	 public   TextView fg1_jiangli;                         //活动奖励
	 public  LinearLayout  fg1_activity_detail;                //点击活动奖励的linearlayout
	 public LinearLayout  ll_person_amount;           //申购情况的linearlayout
	 public TextView fg1_person_amount;                        //申购订单
	  public LinearLayout ll_project_summary;                    //项目描述的linearlayout
	 public TextView fg1_project_summary;                   //项目描述
	 public LinearLayout ll_borrower_summary;                    //资金保障的linearlayout
	 public TextView fg1_borrower_summary;                   //资金保障
	 public ImageButton fg1_calculator;                    //计算器
	 public Button fg1_bt_title;                             //抢购按钮
	 public TextView fg1_shouyi_test;
	 public LinearLayout ll_jiangli;
	 
	 public  String  annual_rate;
	 public int	product_id;
	 public Map2 map2;
	 public SimpleDateFormat formatr1;
	 public SimpleDateFormat formatr2;
	 public Date date1;
	 public Date date2;
	 public SimpleDateFormat formatr3; 
	 
	 public View dialogView;
	 
	 public ImageButton cancle;
	 
	 public EditText cacul_edit;
	private TextView cacul_shoyi1;
	private TextView dialog_date;
 	  private boolean login; 
     private SharedPreferences sp;
     private   Intent intent;
	 
	 @Override
	    public void onCreate(Bundle arg0) {
	    	 
	    	super.onCreate(arg0);
	        setContentView(R.layout.activity_fg1_detail);
	        findview();
	    	queue=MySingle.getInstance(context).getRequestQueue(context);
	        product_id=getIntent().getIntExtra("product_id", 0);
           
	        sp=context.getSharedPreferences("account", context.MODE_PRIVATE);
	     	 login=sp.getBoolean("login", false);
	     	  
	        getdata();
         
	}

  
	private void getdata() {
		 request=new StringRequest(Method.POST, AllUrl.url_detail, new Listener<String>() {
 		@Override
				public void onResponse(String response) {
		 	 	    GsonHomeWith(response);
	 		}
     	}, new ErrorListener() {
 		@Override
				public void onErrorResponse(VolleyError error) {
					 System.out.println("网络不通");
					
				}
			}){@Override
			   protected Map<String, String> getParams() throws AuthFailureError {
				
				Map<String, String> map=new HashMap<String, String>();
			 	map.put("product_id", String.valueOf(product_id));
			 	map.put("user_id","");
		  	
				return map;
			}};
			   
		 queue.add(request);   
	}

              
	        //解析
         	private void GsonHomeWith(String response) {
         	   
         		map2=gson.fromJson(response, Detail.class).map;
                initview(context, map2.product_name);   
		       
		       fg1_financial_period.setText(String.valueOf("期限"+map2.financial_period)+"天");
		       annual_rate=format.format(map2.annual_rate*100); 
		       fg1_annual_rate.setText(annual_rate+"%");  //预期年化
		      
		       fg1_starting_amount.setText("起购金额"+String.valueOf((int)Double.parseDouble(map2.starting_amount))+"元");
		       fg1_amount_raised.setText("融资金额"+String.valueOf((int)Double.parseDouble(map2.amount_raised))+"元");
		       fg1_has_sell.setText(String.valueOf(format.format((Double.parseDouble(map2.amount_raised)-Double.parseDouble(map2.selling_amount))/Double.parseDouble(map2.amount_raised)*100)+"%"));
		       fg1_selling_amount.setText(String.valueOf((int)Double.parseDouble(map2.selling_amount)));
	       
		       //收益
		       fg1_shouyi.setText(format.format((10000+map2.experience_amount)*(map2.annual_rate)*map2.financial_period/365) );
		   
		       if(map2.experience_amount!=0){
		    	   fg1_experience_amount.setVisibility(View.VISIBLE);
		    	   fg1_experience_amount.setText("送"+String.valueOf(map2.experience_amount)+"元体验金");
		    	   fg1_shouyi_test.setText("收益举例:投资100000元,加体验金后到期收益");
		       }else{
		    	   fg1_shouyi_test.setText("收益举例:投资100000元,到期收益");
		       }
	         
		      
		       formatr1=new SimpleDateFormat("yyyy-MM-dd");
		       formatr2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		       formatr3=new SimpleDateFormat("yyyy年MM月dd日");
		       
		       try {
		        date1=formatr2.parse(map2.cur_server_time);
				date2=formatr1.parse(map2.fake_repayment_time);
		        } catch (ParseException e) {
			 	e.printStackTrace();
			  }
		      
		 
		       fg1_cur_server_time.setText(formatr3.format(date1));
		       fg1_fake_repayment_time.setText(formatr3.format(date2));
		       fg1_repayment_desc.setText(map2.repayment_type); 
		       
		       //如果有活动奖励就显示
		      if(!map2.activity_detail.equals("NO_DETAIL")){
		    	  ll_jiangli.setVisibility(View.VISIBLE);
		    	  fg1_jiangli.setText(map2.activity_detail);
		      } 
		       
		       fg1_person_amount.setText(String.valueOf(map2.person_amount)); 
		       
		       fg1_project_summary.setText(map2.project_summary);
		       fg1_borrower_summary.setText(map2.borrower_summary);
		       
		       //标题不为抢购就为灰色，不可点击
		       if(map2.button_title.equals("立即抢购")){
		    	   fg1_bt_title.setEnabled(true);
		       }
		        
		       fg1_bt_title.setText(map2.button_title);   
		     
		       fg1_activity_detail.setOnClickListener(this);
		       ll_person_amount.setOnClickListener(this);
		       ll_project_summary.setOnClickListener(this);
		       ll_borrower_summary.setOnClickListener(this);
		       fg1_calculator.setOnClickListener(this);
		       fg1_bt_title.setOnClickListener(this);   
		       fg1_activity_detail.setOnClickListener(this); 
         	
		       ll_project_summary.setOnClickListener(this); 
		       ll_borrower_summary.setOnClickListener(this); 
         	
         	}
	
	
	
          
    		
    		 
    		
         	
	 
	
         	


	private void findview() {
		fg1_financial_period=(TextView) findViewById(R.id.fg1_financial_period);
		fg1_annual_rate=(TextView) findViewById(R.id.fg1_annual_rate);
		fg1_starting_amount=(TextView) findViewById(R.id.fg1_starting_amount);
		fg1_amount_raised=(TextView) findViewById(R.id.fg1_amount_raised);
		fg1_has_sell=(TextView) findViewById(R.id.fg1_has_sell);
		fg1_selling_amount=(TextView) findViewById(R.id.fg1_selling_amount);
		fg1_shouyi=(TextView) findViewById(R.id.fg1_shouyi);
		fg1_experience_amount=(Button) findViewById(R.id.fg1_experience_amount);
		fg1_cur_server_time=(TextView) findViewById(R.id.fg1_cur_server_time);
		fg1_fake_repayment_time=(TextView) findViewById(R.id.fg1_fake_repayment_time);
		fg1_repayment_desc=(TextView) findViewById(R.id.fg1_repayment_desc);
		fg1_jiangli=(TextView) findViewById(R.id.fg1_jiangli);
		fg1_activity_detail=(LinearLayout) findViewById(R.id.fg1_activity_detail);
	 	ll_person_amount=(LinearLayout) findViewById(R.id.ll_person_amount);
	 	fg1_person_amount=(TextView) findViewById(R.id.fg1_person_amount);
		ll_project_summary=(LinearLayout) findViewById(R.id.ll_project_summary);
		fg1_project_summary=(TextView) findViewById(R.id.fg1_project_summary);
		ll_borrower_summary=(LinearLayout) findViewById(R.id.ll_borrower_summary);
		fg1_borrower_summary=(TextView) findViewById(R.id.fg1_borrower_summary);
		fg1_calculator=(ImageButton) findViewById(R.id.fg1_calculator);
		fg1_bt_title=(Button) findViewById(R.id.fg1_bt_title);
		fg1_shouyi_test=(TextView) findViewById(R.id.fg1_shouyi_test);
		 
		
		ll_jiangli=(LinearLayout) findViewById(R.id.ll_jiangli);
		
		
		
	}


	@Override
	public void onClick(View v) {
		 switch (v.getId()) {
		//奖励
		 case R.id.fg1_activity_detail:
			  intent=new Intent(context,JiangLiActivity.class);
		     intent.putExtra("activity_link", map2.activity_link);
			 startActivity(intent);
			 
	  
			break;
			//申购情况
		case R.id.ll_person_amount:
			
			 Intent intent2=new Intent(context,ShengGouActivity.class);
		     intent2.putExtra("product_id", map2.product_id);
			 startActivity(intent2);
			
			
			
			
			break; 
			//项目描述
       case R.id.ll_project_summary:
    	   Intent intent3=new Intent(context,ProjectActivity.class);
		     intent3.putExtra("product_id", map2.product_id);
			 startActivity(intent3);
    	   
    	    
    	   
    	   
			break;
			 //资金保障
       case R.id.ll_borrower_summary:
			
    	   
    	     Intent intent4=new Intent(context,GuaranteeActivity.class);
		     intent4.putExtra("product_id", map2.product_id);
			 startActivity(intent4);
    	   
    	   
			break;
			 //计算器
       case R.id.fg1_calculator:
			
    	   
    	   dialog=new Dialog(context, R.style.dialog);
    	   dialog.show();
    	   dialog.setContentView(R.layout.dialog);
    	  
    	   cancle=(ImageButton) dialog.getWindow().findViewById(R.id.cancle);
    	   cacul_edit=(EditText)dialog.getWindow().findViewById(R.id.cacul_edit);
    	   cacul_shoyi1=(TextView)dialog.getWindow().findViewById(R.id.cacul_shoyi1);
    	   dialog_date=(TextView)dialog.getWindow().findViewById(R.id.dialog_date);
    	   
    	   dialog_date.setText(String.valueOf(map2.financial_period));
    	   cacul_edit.setText("10000");
    	    
    	   String input=cacul_edit.getText().toString().trim();
    	   cacul_shoyi1.setText(format.format((Integer.parseInt(input)+map2.experience_amount)*map2.annual_rate*map2.financial_period/365));
    	   
    	   
    	   //监听输入变化
    	   cacul_edit.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				 String input=cacul_edit.getText().toString().trim();
				 if(!TextUtils.isEmpty(input)){
			    	   cacul_shoyi1.setText(format.format((Integer.parseInt(input)+map2.experience_amount)*map2.annual_rate*map2.financial_period/365));
			           }else{
			        	   cacul_shoyi1.setText("0");   
			           }
			}
	 		@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
		  	}
	 		@Override
			public void afterTextChanged(Editable s) {
		 	}
		});
   	   
    	   cancle.setOnClickListener(new OnClickListener() {
		 	@Override
			public void onClick(View v) {
				 dialog.dismiss();
		 	}
		});
	    
			break;
		 //奖励
        
       //立即抢购
       case R.id.fg1_bt_title:
			
    		if(login==false){
				 intent=new Intent(context,PreLoginActivity.class);
			     context.startActivity(intent);
	 			
			}else{
		 	    intent=new Intent(context,GouMaiActivity.class);
			    intent.putExtra("product_name", map2.product_name);
			    intent.putExtra("raise", map2.annual_rate);  
			   
			    
			    
			    intent.putExtra("experience_amount", map2.experience_amount);
			    intent.putExtra("financial_period", map2.financial_period);  
			    intent.putExtra("value_date", map2.value_date);       
			    intent.putExtra("product_id", map2.product_id);
			    intent.putExtra("starting_amount", map2.starting_amount); 
			     
			    context.startActivity(intent); 
		
			}
        
			break;
			
       case R.id.cancle:
			dialog.dismiss();
			System.out.println("xiaoshi b");
			
			break;	 
		       
		       
		       
		        
		default:
			break;
		}
		
	} 
	  
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
}
