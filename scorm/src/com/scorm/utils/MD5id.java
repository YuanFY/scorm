package com.scorm.utils;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

public class MD5id {
	public static String encode(String value){
		String result = null;
		try{
			MessageDigest md = MessageDigest.getInstance("md5");
			byte b[] = md.digest(value.getBytes());//�õ�value�����ָ��
			BASE64Encoder be = new BASE64Encoder();
			result = be.encode(b);//��base64����ת��Ϊ����
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
