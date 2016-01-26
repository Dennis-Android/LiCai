package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

     public String  MD5(String a) throws NoSuchAlgorithmException{
    	 MessageDigest digest = MessageDigest.getInstance("md5");
 	 
 		byte [] bytes =  digest.digest(a.getBytes());
 		StringBuffer buffer = new StringBuffer();
 		for(byte b: bytes){
 			int number = b & 0xff;//加盐
 			String hex = Integer.toHexString(number);
 			if(hex.length()==1){
 				buffer.append("0");
 			}
 			buffer.append(hex);
 		}
 		//md5加密后的值
		return buffer.toString();
 		 
     } 
	
	 
	
	
	
	
	
	 /*public  void Md5 throws NoSuchAlgorithmException(){
		MessageDigest digest = MessageDigest.getInstance("md5");
		String password = "123456";
		byte [] bytes =  digest.digest(password.getBytes());
		StringBuffer buffer = new StringBuffer();
		for(byte b: bytes){
			int number = b & 0xff;//加盐
			String hex = Integer.toHexString(number);
			if(hex.length()==1){
				buffer.append("0");
			}
			buffer.append(hex);
		}
		//md5加密后的值
		System.out.println(buffer);

	}*/

}