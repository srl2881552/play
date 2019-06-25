package com.song.play.cloud.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;







import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class JedisManager {

	  private static final Logger log=LogManager.getLogger(JedisManager.class);
	  private static final String defaultEncoding = "UTF-8";
	  private static boolean inited = false;
	  private static ShardedJedisPool pool;
	  private static JedisPoolConfig poolConfig = new JedisPoolConfig();

	  public static void init(Properties prop)
	  {
	    if (!inited) {
	      log.info("JedisManager init...");
	      initShardedPool(prop);
	      inited = true;
	    }
	  }

	  /**
	   * 初始化切片池
	   * @param prop
	   */
	  public static void initShardedPool(Properties prop)
	  {
	    log.info("JedisManager initConf...");
	    destroy();
	    //读取配置文件，构建redispool,格式：192.168.1.200,192.168.1.64 端口号默认
	    String[] strArray = prop.getProperty("redis.host").split(",");
	    List<JedisShardInfo> shardJedis = new ArrayList<JedisShardInfo>();
	    for (int i = 0; i < strArray.length; i++) {
	    	String addressconfig[]=strArray[i].split(":");
	    	int length=addressconfig.length;
	    	if(length==1){
	    		shardJedis.add(new JedisShardInfo(strArray[i]));
	    		
	    	}else{
	    		System.out.println("************"+addressconfig);
	    		shardJedis.add(new JedisShardInfo(addressconfig[0],addressconfig[1]));
	    	}
	      
	    }
	    pool = new ShardedJedisPool(poolConfig, shardJedis);
	  }

	  public static String setString(String key, String str, int liveSeconds)
	  {
	    if ((key == null) || (str == null))
	      return null;
	    ShardedJedis jedis = null;
	    String ret = null;
	    try {
	      jedis = pool.getResource();
	      if (liveSeconds <= 0)
	    	  //永久保存
	        ret = jedis.set(encode(key), str);
	      else
	    	  //设置有效期
	        ret = jedis.setex(encode(key), liveSeconds, str);
	    }
	    catch (Exception e) {
	    	log.error(e);
	    } finally {
	      if (jedis != null) 
	    	  pool.returnResource(jedis);
	    }
	    return ret;
	  }

	  public static String getString(String key)
	  {
	    ShardedJedis jedis = null;
	    String ret = null;
	    try {
	      jedis = pool.getResource();
	      ret = jedis.get(encode(key));
	    } catch (Exception e) {
	      log.error(e);
	    } finally {
	      if (jedis != null) 
	    	  pool.returnResource(jedis);
	    }
	    return ret;
	  }

	  public static long delString(String key)
	  {
	    return del(key);
	  }

	  public static long delStringNew(String key)
	  {
	    return del(encode(key));
	  }

	  public static long delObject(String key)
	  {
	    return del(key);
	  }

	  public static Object getObject(String key)
	  {
	    if (key == null)
	      return null;
	    byte[] ret = getBytes(key.getBytes());
	    if (ret == null)
	      return null;
	    return ObjectBytesExchange.toObject(ret);
	  }

	  public static String setObject(String key, int seconds, Object obj) {
	    if ((key == null) || (obj == null))
	      return null;
	    byte[] byteObj = ObjectBytesExchange.toByteArray(obj);
	    if (null == byteObj)
	      return null;
	    return setBytes(key.getBytes(), seconds, byteObj);
	  }

	  private static byte[] getBytes(byte[] key)
	  {
	    if (key == null)
	      return null;
	    ShardedJedis jedis = null;
	    byte[] ret = null;
	    try {
	      jedis = (ShardedJedis)pool.getResource();
	      ret = jedis.get(key);
	    } catch (Exception e) {
	      log.error(e);
	    } finally {
	      if (jedis != null) pool.returnResource(jedis);
	    }
	    return ret;
	  }

	  private static String setBytes(byte[] key, int seconds, byte[] bytes) {
	    if ((key == null) || (bytes == null))
	      return null;
	    ShardedJedis jedis = null;
	    String ret = null;
	    try {
	      jedis = (ShardedJedis)pool.getResource();
	      if (seconds <= 0)
	        ret = jedis.set(key, bytes);
	      else
	        ret = jedis.setex(key, seconds, bytes);
	    }
	    catch (Exception e) {
	      log.error(e);
	    } finally {
	      if (jedis != null) pool.returnResource(jedis);
	    }
	    return ret;
	  }

	  public static String encode(String str) {
	    String ret = null;
	    if (str != null) {
	      try {
	        ret = URLEncoder.encode(str, "UTF-8");
	      } catch (UnsupportedEncodingException e) {
	        log.error(e);
	      }
	    }
	    return ret;
	  }

	  private static long del(String key)
	  {
	    if (key == null) {
	      return 0L;
	    }
	    ShardedJedis jedis = null;
	    long ret = 0L;
	    try {
	      jedis = pool.getResource();
	      ret = jedis.del(key).longValue();
	    } catch (Exception e) {
	      log.error(e);
	      ret = -1L;
	    } finally {
	      if (jedis != null) 
	    	  pool.returnResource(jedis);
	    }
	    return ret;
	  }

	  public static boolean lock(String key, int seconds)
	  {
	    if (seconds <= 0)
	    {
	      seconds = 21600;
	    }
	    boolean result = false;
	    if (key == null)
	      return result;
	    ShardedJedis jedis = null;
	    long currentTime = System.currentTimeMillis();
	    long expireTime = currentTime + seconds * 1000;
	    try {
	      jedis = pool.getResource();
	      if (jedis.setnx(encode(key), String.valueOf(expireTime)).longValue() == 1L) {
	        result = true;
	        jedis.expire(encode(key), seconds);
	      } else {
	        Long oldExpireTime = Long.valueOf(getString(encode(key)));
	        if ((oldExpireTime != null) && (currentTime > oldExpireTime.longValue() + 300000L)) {
	          del(encode(key));
	          if (jedis.setnx(encode(key), String.valueOf(expireTime)).longValue() == 1L) {
	            result = true;
	            jedis.expire(encode(key), seconds);
	          }
	        }
	      }
	    } catch (Exception e) {
	      log.error(e);
	    } finally {
	      if (jedis != null) 
	    	  pool.returnResource(jedis);
	    }
	    return result;
	  }

	  public static ShardedJedisPool getPool() {
	    return pool;
	  }

	  private static void destroy() {
	    if (pool != null) {
	      try {
	        pool.destroy();
	      } catch (Exception ex) {
	        log.warn("Cannot properly close ShardedJedisPool ", ex);
	      }
	      pool = null;
	    }
	  }

	  public static long incrByStr(String key)
	  {
	    if (key == null) {
	      return 0L;
	    }
	    ShardedJedis jedis = null;
	    long ret = 0L;
	    try {
	      jedis = pool.getResource();
	      ret = jedis.incr(encode(key)).longValue();
	    } catch (Exception e) {
	      log.error(e);
	      ret = -1L;
	    } finally {
	      if (jedis != null) 
	    	  pool.returnResource(jedis);
	    }
	    return ret;
	  }

	  public static long incrByByte(byte[] key)
	  {
	    if ((key == null) || (key.length == 0)) {
	      return 0L;
	    }
	    ShardedJedis jedis = null;
	    long ret = 0L;
	    try {
	      jedis = pool.getResource();
	      ret = jedis.incr(key).longValue();
	    } catch (Exception e) {
	      log.error(e);
	      ret = -1L;
	    } finally {
	      if (jedis != null) 
	    	  pool.returnResource(jedis);
	    }
	    return ret;
	  }

	  
	public static String getHashString(String key, String field) {
		ShardedJedis jedis = null;
		String ret = null;
		try {
			jedis = pool.getResource();
			ret = jedis.hget(key, field);
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (jedis != null)
				pool.returnResource(jedis);
		}
		return ret;
	}
	  
	public static void setHashString(String key, String field, String str) {
		if ((key == null) || (str == null))
			return;
		ShardedJedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.hset(key, field, str);
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (jedis != null)
				pool.returnResource(jedis);
		}
	}
	  
	public static Set<String> getHashKeys(String key) {
		ShardedJedis jedis = null;
		Set<String> hkeys = null;
		try {
			jedis = pool.getResource();
			hkeys = jedis.hkeys(key);
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (jedis != null)
				pool.returnResource(jedis);
		}
		return hkeys;
	}
	  
	public static List<String> getHashValues(String key) {
		ShardedJedis jedis = null;
		List<String> hvals = null;
		try {
			jedis = pool.getResource();
			hvals = jedis.hvals(key);
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (jedis != null)
				pool.returnResource(jedis);
		}
		return hvals;
	}
	
	public static Long delHashKey(String key,String ...fields) {
		ShardedJedis jedis = null;
		Long hdel = null;
		try {
			jedis = pool.getResource();
			 hdel = jedis.hdel(key, fields);
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (jedis != null)
				pool.returnResource(jedis);
		}
		return hdel;
	}
	  
	  static
	  {
	    log.info("JedisManager static code block loader...");
	    //最大活动的对象个数
	    poolConfig.setMaxTotal(500);
	    //对象最大空闲时间
	    poolConfig.setMaxIdle(60*1000);
	    //获取对象时最大等待时间
	    poolConfig.setMaxWaitMillis(1000L);
	    //当调用borrow Object方法时，是否进行有效性检查
	    poolConfig.setTestOnBorrow(true);
	    String[] strArray = RedisConfig.getStringArray("redis.host");
	    List<JedisShardInfo> shardJedis = new ArrayList<JedisShardInfo>();
	    for (int i = 0; i < strArray.length; i++) {
	    	String addressconfig[]=strArray[i].split(":");
	    	int length=addressconfig.length;
	    	if(length==1){
	    		shardJedis.add(new JedisShardInfo(strArray[i]));
	    		
	    	}else{
	    		System.out.println("************"+addressconfig[0]+":"+addressconfig[1]);
	    		shardJedis.add(new JedisShardInfo(addressconfig[0],Integer.valueOf(addressconfig[1])));
	    	}
	    }
	    pool = new ShardedJedisPool(poolConfig, shardJedis);
	  }
}
