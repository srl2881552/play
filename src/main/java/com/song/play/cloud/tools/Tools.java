package com.song.play.cloud.tools;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class Tools {

	public static String basePath="basePath";
	public static String getContextValue(String keyParam)
	{
		String value=null;
		try {
			Context context=new InitialContext();
			Context envCtx = (Context)context.lookup("java:comp/env");
			value = (String)envCtx.lookup(keyParam);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
	}
	 public static String getHostIp() {
		 String ip="";
		 try {
			 InetAddress addr = InetAddress.getLocalHost();  
	         ip=addr.getHostAddress().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	     return ip;
	    }
	 public static String getVideoLength(File video)
	 {
		 String result="";
		 try {
				Encoder encoder = new Encoder();
				MultimediaInfo m = encoder.getInfo(video);
				System.out.print(Tools.getFormatLength(String.valueOf(m.getDuration()/(3600*1000))) + ":" 
				        + Tools.getFormatLength(String.valueOf(m.getDuration()%(3600*1000)/(60*1000)))+ ":" 
				        + Tools.getFormatLength(String.valueOf(m.getDuration()%(3600*1000)%(60*1000)/1000)));
				result=Tools.getFormatLength(String.valueOf(m.getDuration()/(3600*1000))) + ":" 
				        + Tools.getFormatLength(String.valueOf(m.getDuration()%(3600*1000)/(60*1000)))+ ":" 
				        + Tools.getFormatLength(String.valueOf(m.getDuration()%(3600*1000)%(60*1000)/1000));
				} catch (Exception e) {
				e.printStackTrace();
				}
		 return result;
	 }
	 public static String getFormatLength(String length)
	 {
		 if(length.length()==1)
		 {
			 length="0"+length;
		 }
		 return length;
	 }
	 public static void main(String[] args) throws Exception {
         String url="http://192.168.0.15:9000/002f18f0222f4330a5b7b01e3601f7fb.mp4";
         String videoIdMp4=url.substring(url.lastIndexOf("/")+1, url.length());
         String videoId=videoIdMp4.substring(0, videoIdMp4.lastIndexOf("."));
         System.out.println();
         System.out.println();
    }
}
