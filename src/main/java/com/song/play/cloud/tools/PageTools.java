package com.song.play.cloud.tools;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageTools {

	public PageTools(int currentPage,int totalPages)
	{
		this.currentPage=currentPage;
		this.totalPages=totalPages;
	}
	public List<String> pageCodes = new ArrayList<String>();
    public int showPageSize = 8;
    public int currentPage = 1;
    public int totalPages = 0;
	public static String getPageSql(Object object)
	{
		String where=" where 1=1 and";
		Class userCla = (Class) object.getClass();
		Field[] fs = userCla.getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val = new Object();
            try {
                val = f.get(object);
                if(val!=null&&!"".equals(val))
                {
                	if(f.getName().equals("startTime"))
                	{
                    	where+=" createAt>=? and";
                	}
                	else if(f.getName().equals("endTime"))
                	{
                		where+=" createAt<=? and";
                	}else{
                		where+=" "+f.getName()+"=? and";
                	}
                	
                }
                // 得到此属性的值
                
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            /*
             * String type = f.getType().toString();//得到此属性的类型 if
             * (type.endsWith("String")) {
             * System.out.println(f.getType()+"\t是String"); f.set(obj,"12") ;
             * //给属性设值 }else if(type.endsWith("int") ||
             * type.endsWith("Integer")){
             * System.out.println(f.getType()+"\t是int"); f.set(obj,12) ; //给属性设值
             * }else{ System.out.println(f.getType()+"\t"); }
             */

        }
		return where.substring(0, where.lastIndexOf("and"));
	}
	public static Object[] getPageValue(Object object)
	{
		java.text.SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		List<Object> objects=new ArrayList<Object>();
		Class userCla = (Class) object.getClass();
		Field[] fs = userCla.getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val = new Object();
            try {
                val = f.get(object);
                if(val!=null&&!"".equals(val))
                {
                	if(f.getName().equals("startTime"))
                	{
                		objects.add(format.parseObject(val.toString()));
                	}
                	else if(f.getName().equals("endTime"))
                	{
                		objects.add(format.parseObject(val.toString()));
                	}else{
                		objects.add(val);
                	}
                	
                }
                // 得到此属性的值
                
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            /*
             * String type = f.getType().toString();//得到此属性的类型 if
             * (type.endsWith("String")) {
             * System.out.println(f.getType()+"\t是String"); f.set(obj,"12") ;
             * //给属性设值 }else if(type.endsWith("int") ||
             * type.endsWith("Integer")){
             * System.out.println(f.getType()+"\t是int"); f.set(obj,12) ; //给属性设值
             * }else{ System.out.println(f.getType()+"\t"); }
             */

        }
		return objects.toArray();
	}
	public void reflashPageCode()
    {
		
        this.pageCodes = new ArrayList<String>();
        if (this.totalPages <= this.showPageSize)
        {
            for (int i = 1; i <= this.totalPages; i++)
            {
                this.pageCodes.add(String.valueOf(i));
            }
            return;
        }
        int middleSide = this.showPageSize >> 1;
        if (this.currentPage <= middleSide)
        {
            for (int i = 1; i <= this.showPageSize; i++)
            {
                this.pageCodes.add(String.valueOf(i));
            }
            this.pageCodes.add(String.valueOf(".."));
            return;
        }
        if ((this.totalPages - this.currentPage) <= middleSide)
        {
            this.pageCodes.add(String.valueOf(".."));
            for (int i = this.showPageSize - 1; i >= 0; i--)
            {
                this.pageCodes.add(String.valueOf(this.totalPages - i));
            }
            return;
        }
        if (middleSide < this.currentPage
                && this.currentPage - (middleSide + 1) > 0)
            this.pageCodes.add(String.valueOf(".."));

        for (int i = 0; i < this.showPageSize; i++)
        {
            this.pageCodes.add(String.valueOf((this.currentPage + i)
                    - middleSide));
        }
        if (middleSide > this.currentPage
                || this.totalPages - (this.currentPage + middleSide) > 0)
            this.pageCodes.add(String.valueOf(".."));
    }
	public static void main(String[] args) {
		PageTools tools=new PageTools(12, 10);
		tools.reflashPageCode();
		System.out.println(tools.pageCodes);
	}
}
