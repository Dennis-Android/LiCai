package model;

import java.util.ArrayList;

public class fg2 {
	public String active;
	public ArrayList<Array2> array;
   
        
	  @Override
	public String toString() {
		return "fg2 [active=" + active + ", array=" + array + "]";
	}


	public class Array2{
	    public String amount_raised;   
	    public double annual_rate;               //折扣
	    public String operation_tag;               //父类分裂
		public int  product_id;
		public String product_name;
	    public int	experience_amount;
	    public String starting_amount;               //起购jine
		             
		public int period;                     //还款时间
		public String repayment_type;              //到期自动还款
		public String series;                     //名称
		
		public int person_amount;

		@Override
		public String toString() {
			return "Array2 [amount_raised=" + amount_raised + ", annual_rate="
					+ annual_rate + ", operation_tag=" + operation_tag
					+ ", product_id=" + product_id + ", product_name="
					+ product_name + ", experience_amount=" + experience_amount
					+ ", starting_amount=" + starting_amount + ", period="
					+ period + ", repayment_type=" + repayment_type
					+ ", series=" + series + ", person_amount=" + person_amount
					+ "]";
		}
	 	
	  	 
	 	
	}
 
}
