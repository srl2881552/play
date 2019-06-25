package com.song.play.cloud.tools;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
public class RedisConfig {

	/**
	 * redis配置key
	 */
	 public static final String REDIS_POOL = "redis.host";
	 private static Configuration config = null;
	 /**
	  * 配置文件名称
	  */
	 private static String configfile = "redis.properties";

	  public static String getString(String key){
	    return config.getString(key);
	  }

	  public static String[] getStringArray(String key) {
	    return config.getStringArray(key);
	  }

	  public static void setPropertiesConfig(PropertiesConfiguration prop) {
	    config = prop;
	  }

	  public static void main(String[] args) {
	    String[] strArray = getStringArray(REDIS_POOL);
	    System.out.println(strArray[0]);
	  }

	  static
	  {
	    try
		    {
		      config = new PropertiesConfiguration(configfile);
		    } catch (ConfigurationException e) {
		      e.printStackTrace();
		    }
	  }
}
