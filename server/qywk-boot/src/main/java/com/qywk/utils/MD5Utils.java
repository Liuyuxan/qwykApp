/**
 * 
 */
package com.qywk.utils;
 
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
/**
 * MD5加密解密工具类
 * @author admin
 *
 */
public class MD5Utils {
	/**
	 * 使用 MD5算法加密生成32位md5码
	 * 不可逆
	 * @param str
	 * @return
	 */
	public static String getMD5String(String str) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(str.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}
	
	/**
	 * 	可逆的加密解密算法,执行一次加密,两次解密 
	 * @param str
	 * @return
	 */
	public static String convertMD5(String str){
		char[] a = str.toCharArray();
		for (int i = 0; i < a.length; i++){
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return str;
	}
	/*
	 前端js可逆算法配合使用
	 */
	/*
	function convertMD5(str) {
		var a = str.split('');
		for (var i = 0; i < a.length; i++) {
			a[i] = String.fromCharCode(a[i].charCodeAt(0) ^ 't'.charCodeAt(0));
		}
		var s = a.join('');
		return s;
	}
	 */

	public static String encrypt(String plaintext, int shift) {
		StringBuilder encryptedText = new StringBuilder();

		for (char c : plaintext.toCharArray()) {
			if (Character.isLetter(c)) {
				char base = Character.isUpperCase(c) ? 'A' : 'a';
				char encryptedChar = (char) ((c - base + shift) % 26 + base);
				encryptedText.append(encryptedChar);
			} else {
				encryptedText.append(c);
			}
		}

		return encryptedText.toString();
	}

	public static String decrypt(String ciphertext, int shift) {
		return encrypt(ciphertext, 26 - shift);
	}

}