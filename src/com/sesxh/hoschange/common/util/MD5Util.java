/**
 * Copyright (c) 2016-2020 https://github.com/zhaohuatai
 *
 * contact z_huatai@qq.com
 *  
 */

package com.sesxh.hoschange.common.util;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * 
 * @author zhaohutai
 *
 */
public class MD5Util {
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	public static String encrypt( String pssword, String salt){
		pssword = pssword + salt;
		return encrypt(pssword);
	}
	
    public static String genRandomSalt(){
    	String salt=System.nanoTime()+UUID.randomUUID().toString();//
    	return salt;
    }
    
	/**
	 * 模式： salt+pssword
	 * @param pssword
	 * @param salt
	 * @return
	 */
	public static String encryptA( String pssword, String salt){
		pssword = salt+pssword ;
		return encrypt(pssword);
	}
	/**
	 * 模式： salt+"{" + salt + "}"
	 * @param pssword
	 * @param salt
	 * @return
	 */
	public static String encryptB( String pssword, String salt){
		pssword = salt+"{" + salt + "}" ;
		return encrypt(pssword);
	}
	/**
	 * 模式："{" +salt+ "}" + salt
	 * @param pssword
	 * @param salt
	 * @return
	 */
	public static String encryptC( String pssword, String salt){
		pssword = "{" +salt+ "}" + salt ;
		return encrypt(pssword);
	}
	/**
	 * 模式："{" +salt+ "}" +"{" + salt+ "}"
	 * @param pssword
	 * @param salt
	 * @return
	 */
	public static String encryptD( String pssword, String salt){
		pssword = "{" +salt+ "}" +"{" + salt+ "}" ;
		return encrypt(pssword);
	}
	public static String encrypt(String str){
		try {
			byte[]strBytes = str.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			//md.update(str.getBytes());
			byte[] digest = md.digest(strBytes);
			return getFormattedText(digest);
		} catch (Exception e) {
			return null;
		}
	}
	
	   private static String getFormattedText(byte[] bytes) {
		  int len = bytes.length;
		   StringBuilder buf = new StringBuilder(len * 2);
		      for (int j = 0; j < len; j++) {
		           buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
		           buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		      }
		      return buf.toString();
		 }
	//   admin
//	   pasword  from page: 0336a8f2cde0da1142b4ea02cc9534f7
//	   salt: 51523913897952509d57b0-7057-4cb8-8f7a-11885bef71a9
//	   final_password: bebcc8e0e431b610d4978df1d82f46fa
	    public static void main(String[] argd){//eef3a22a128d5adb5699e3c7da7a6fc8
	    	String userName="admin";
	    	String password="admin";
	    	String page_salt="@sdsesxh_94DABGioQOq2tTUO0AXYow";
	    	String password_from_page=MD5Util.encrypt(password,userName+page_salt);
	    	System.out.println("pasword  from page: "+password_from_page);
	    	String salt=genRandomSalt();
	    	System.out.println("salt: "+salt);;
	    	String final_password=MD5Util.encrypt( password_from_page, salt);
	    	System.out.println("final_password: "+final_password);;
	    }
}
