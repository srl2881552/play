package com.song.play.cloud.tools;

import java.io.IOException;

/**
 * 
 * @author wanghg
 */
public class Crypt {
    private static final String CHARSET = "UTF-8";
	public static String encrypt(String str) throws IOException {
		return Base64.encode(new AES().encrypt(str.getBytes(CHARSET)));
	}
	public static String decrypt(String str) throws IOException {
		return new String(new AES().decrypt(Base64.decode(str)), CHARSET).trim();
	}
	public static byte[] decrypt(byte[] data) throws IOException {
		return new AES().decrypt(data);
	}
	public static byte[] encrypt(byte[] data) throws IOException {
		return new AES().encrypt(data);
	}
	
	public static void main(String[] args) throws IOException {
		///System.out.println(Crypt.encrypt("1000004204"));
		System.out.println(Crypt.decrypt("x48QxJyhED60Unh/VuQyMw=="));
	}
}