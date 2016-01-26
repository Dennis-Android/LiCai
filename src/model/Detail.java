package model;

public class Detail {
                  
	
	 public Map2 map;
	
	
	  @Override
	public String toString() {
		return "Detail [map=" + map + "]";
	}


	public class Map2{
	    	 public String   product_name;  // "新手标B1233" 
	    	 public int financial_period;
	    	 public double   annual_rate;  
	    	 public String   starting_amount;  //起购金额            
	         public String  amount_raised;
	         public String  selling_amount;
	    	 public String  cur_server_time;              
	    	 public String  fake_repayment_time;
	         public String   repayment_type;  
	         public int  experience_amount;
	         public String  activity_detail;
	         public String  activity_link;
	         public int     person_amount;
	         public String   project_summary;   //项目描述       
	         public String borrower_summary;   //资金保障
	         public String button_title;
			
	         public int  product_id;
	         public String  value_date;
	         
	         @Override
			public String toString() {
				return "Map2 [product_name=" + product_name
						+ ", financial_period=" + financial_period
						+ ", annual_rate=" + annual_rate + ", starting_amount="
						+ starting_amount + ", amount_raised=" + amount_raised
						+ ", selling_amount=" + selling_amount
						+ ", cur_server_time=" + cur_server_time
						+ ", fake_repayment_time=" + fake_repayment_time
						+ ", repayment_type=" + repayment_type
						+ ", experience_amoun=" + experience_amount
						+ ", activity_detail=" + activity_detail
						+ ", activity_link=" + activity_link
						+ ", person_amount=" + person_amount
						+ ", project_summary=" + project_summary
						+ ", borrower_summary=" + borrower_summary
						+ ", button_title=" + button_title + "]";
			}
	         
	        
	    
	         
	
	
	  }	
}
