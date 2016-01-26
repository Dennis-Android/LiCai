package model;

import java.util.ArrayList;

public class fg1 {
	public String active;
	public ArrayList<Array> array;

	@Override
	public String toString() {
		return "fg1 [active=" + active + ", array=" + array + "]";
	}

	public class Array{
		
		public int	experience_amount;
		public int  product_id;
		public String product_name;                //父标题
		public int period;                     //还款时间
		public String repayment_type;              //到期自动还款
		public String series;                     //名称
		public String starting_amount;               //起购jine
	 	public double annual_rate;               //折扣
	 	public String operation_tag;               //父类分裂
		@Override
		public String toString() {
			return "Array [experience_amount=" + experience_amount
					+ ", product_id=" + product_id + ", product_name="
					+ product_name + ", period=" + period + ", repayment_type="
					+ repayment_type + ", series=" + series
					+ ", starting_amount=" + starting_amount + ", annual_rate="
					+ annual_rate + ", operation_tag=" + operation_tag + "]";
		}
	 	
	 	
	 	
	     	
	 	
	 	
	 	
	 	
	}
 
}
