package com.song.play.cloud.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Tools1 {

	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream(new File("C:/Users/dell/Desktop/temp_user.csv"));
		 
		//Construct BufferedReader from InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		File fout = new File("C:/Users/dell/Desktop/out.txt");	
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));	            
		String line = null;
		int i=0;
		while ((line = br.readLine()) != null) {
			String[] values=line.split(",");
			i++;
			System.out.println("insert into p_user (id,login,password,user_type,created_at,created_by,status,source,login_status) values (SEQ_P_USER_ID.NEXTVAL,'"+values[0].replaceAll(" ", "")+"','"+MD5.GetMD5Code("ddky"+values[0].replaceAll(" ", ""))+"',0,sysdate,'sys',1,'cxzx_cpicys',0);");
			if(i%10000==0)
			{
				bw.write("commit;");
				bw.newLine();
			}
			
			
			bw.write("insert into p_user (id,login,password,user_type,created_at,created_by,status,source,login_status) values (SEQ_P_USER_ID.NEXTVAL,'"+values[0].replaceAll(" ", "")+"','"+MD5.GetMD5Code("ddky"+values[0].replaceAll(" ", ""))+"',0,sysdate,'sys',1,'cxzx_cpicys',0);");
			bw.newLine();
			
		}
		bw.flush();//刷新内存，将内存中的数据立刻写出。
        bw.close();
		br.close();
	}
}
