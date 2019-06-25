package com.song.play.cloud.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileTools {

	public static String getFileType(String fileName)
	{
		String suffix="folder";
		if(fileName.indexOf(".")>=0)
		{
			suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
	        System.out.println(suffix);
		}
		return suffix.toLowerCase();
	}
	
	public static Properties getProperties()
	{
		InputStream stream=FileTools.class.getResourceAsStream("/Fmpeg.properties");
		Properties properties=new Properties();
		try {
			properties.load(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
	public static Properties getVideoProperties()
	{
		InputStream stream=FileTools.class.getResourceAsStream("/video.properties");
		Properties properties=new Properties();
		try {
			properties.load(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
	public static boolean delAllFile(String path) {
	       boolean flag = false;
	       File file = new File(path);
	       if (!file.exists()) {
	         return flag;
	       }
	       if (!file.isDirectory()) {
	         return flag;
	       }
	       String[] tempList = file.list();
	       File temp = null;
	       for (int i = 0; i < tempList.length; i++) {
	          if (path.endsWith(File.separator)) {
	             temp = new File(path + tempList[i]);
	          } else {
	              temp = new File(path + File.separator + tempList[i]);
	          }
	          if (temp.isFile()) {
	             temp.delete();
	          }
	          if (temp.isDirectory()) {
	             delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
	             delFolder(path + "/" + tempList[i]);//再删除空文件夹
	             flag = true;
	          }
	       }
	       return flag;
	     }
public static void delFolder(String folderPath) {
    try {
       delAllFile(folderPath); //删除完里面所有内容
       String filePath = folderPath;
       filePath = filePath.toString();
       java.io.File myFilePath = new java.io.File(filePath);
       myFilePath.delete(); //删除空文件夹
    } catch (Exception e) {
      e.printStackTrace(); 
    }
}
	public static void main(String[] args) {
		
	}
}
