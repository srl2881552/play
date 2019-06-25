package com.song.play.cloud.tools;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


public class SearchTools {

	public static String StringFilter(String str) throws PatternSyntaxException { 
		// 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]"; 
		// 清除掉所有特殊字符 
	if(str!=null)
	{
		String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]"; 
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}else{
		return "";
	}
	
	}
}
