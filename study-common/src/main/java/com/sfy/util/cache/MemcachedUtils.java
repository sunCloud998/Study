package com.sfy.util.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sfy.util.prop.PropertiesUtils;
import org.apache.commons.net.telnet.TelnetClient;
import org.apache.log4j.Logger;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class MemcachedUtils {

	private static final Logger logger = Logger.getLogger(MemcachedUtils.class);

	private static final String FILE_PATH = "/memcached.properties";

	private static String[] servers = { "127.0.0.1:11211" };
	private static Integer[] weights = { 1 };
	private static final Map<String, String> serverConfig = new HashMap<String, String>();

	// 是否启用memcached
	private static boolean isUsed = true;

	private static final MemCachedClient client = new MemCachedClient();

	private static MemcachedUtils memcachedUtils;

	/**
	 * @功能: 初始化参数配置
	 * flight
	 * @创建日期: 2014年3月29日 下午10:46:55
	 */
	private void initConfig() {
		String[] sers = PropertiesUtils.getProperty(FILE_PATH, "memcached.servers").split(",");
		String[] wgts = PropertiesUtils.getProperty(FILE_PATH, "memcached.weights").split(",");
		List<Integer> serverUsed = new ArrayList<Integer>();

		TelnetClient telnet = new TelnetClient();
		for (int i = 0, len = sers.length; i < len; i++) {
			int seg = sers[i].indexOf(":");
			try {
				telnet.connect(sers[i].substring(0, seg), Integer.parseInt(sers[i].substring(seg + 1)));
				telnet.disconnect();
				serverUsed.add(i);
			} catch (Exception e) {
			}
		}
		int serverCount = serverUsed.size();
		if (serverCount == 0) {
			isUsed = false;
			return;
		}

		servers = new String[serverCount];
		weights = new Integer[serverCount];
		for (int i = 0; i < serverCount; i++) {
			servers[i] = sers[serverUsed.get(i)];
			weights[i] = Integer.parseInt(wgts[serverUsed.get(i)]);
		}

		serverConfig.put("initConn", PropertiesUtils.getProperty(FILE_PATH, "memcached.initConn", "8"));
		serverConfig.put("minConn", PropertiesUtils.getProperty(FILE_PATH, "memcached.minConn", "8"));
		serverConfig.put("maxConn", PropertiesUtils.getProperty(FILE_PATH, "memcached.maxConn", "32"));
		serverConfig.put("maxIdle", PropertiesUtils.getProperty(FILE_PATH, "memcached.maxIdle", "8"));
		serverConfig.put("maintSleep", PropertiesUtils.getProperty(FILE_PATH, "memcached.maintSleep", "30000"));
		serverConfig.put("socketConnTO", PropertiesUtils.getProperty(FILE_PATH, "memcached.socketConnTO", "0"));
		serverConfig.put("socketTO", PropertiesUtils.getProperty(FILE_PATH, "memcached.socketTO", "10000"));
	}

	private void initMemcached() {
		SockIOPool pool = SockIOPool.getInstance();

		// 服务器地址
		pool.setServers(servers);
		// 权重
		pool.setWeights(weights);

		// 初始连接数
		pool.setInitConn(Integer.parseInt(serverConfig.get("initConn")));
		// 最小连接数
		pool.setMinConn(Integer.parseInt(serverConfig.get("minConn")));
		// 最大连接数
		pool.setMaxConn(Integer.parseInt(serverConfig.get("maxConn")));
		// 最大空闲连接数
		pool.setMaxIdle(Integer.parseInt(serverConfig.get("maxIdle")));

		// 守护线程的休眠时间
		pool.setMaintSleep(Long.parseLong(serverConfig.get("maintSleep")));

		// 关闭nagle算法
		pool.setNagle(false);
		// 连接操作的超时时间, 0为不限制
		pool.setSocketConnectTO(Integer.parseInt(serverConfig.get("socketConnTO")));
		// 读取操作的超时时间
		pool.setSocketTO(Integer.parseInt(serverConfig.get("socketTO")));

		pool.initialize();
	}

	private MemcachedUtils() {
		logger.info("=================== 初始化配置文件 ===================");
		initConfig();
		logger.info("=================== 初始化 memcached ===============");
		initMemcached();
	}

	public synchronized static MemcachedUtils getInstance() {
		if (memcachedUtils == null) {
			memcachedUtils = new MemcachedUtils();
		}
		return memcachedUtils;
	}

	/**
	 * @功能: 是否启用memcached
	 * flight
	 * @创建日期: 2014年3月29日 下午10:47:28
	 * @return
	 */
	public boolean isUsedMemcached() {
		return isUsed;
	}

	/**
	 * @功能: 插入新记录, 前提是记录的Key在缓存中不存在
	 * flight
	 * @创建日期: 2014年3月29日 下午10:47:50
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add(String key, Object value) {
		if (isUsed) {
			return client.add(key, value);
		}
		return false;
	}

	/**
	 * @功能: 插入新记录, 前提是记录的Key在缓存中不存在
	 * flight
	 * @创建日期: 2014年3月29日 下午10:49:21
	 * @param key
	 * @param value
	 * @param date 超时日期
	 * @return
	 */
	public boolean add(String key, Object value, Date date) {
		if (isUsed) {
			return client.add(key, value, date);
		}
		return false;
	}

	/**
	 * @功能: 插入新记录或更新已有记录, 记录的Key在缓存中不存在则插入否则更新
	 * flight
	 * @创建日期: 2014年3月29日 下午10:50:17
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, Object value) {
		if (isUsed) {
			return client.set(key, value);
		}
		return false;
	}

	/**
	 * @功能: 插入新记录或更新已有记录, 记录的Key在缓存中不存在则插入否则更新
	 * flight
	 * @创建日期: 2014年3月29日 下午10:51:40
	 * @param key
	 * @param value
	 * @param date 超时日期
	 * @return
	 */
	public boolean set(String key, Object value, Date date) {
		if (isUsed) {
			return client.set(key, value, date);
		}
		return false;
	}

	/**
	 * @功能: 更新已有记录, 前提是记录的Key在缓存中已经存在
	 * flight
	 * @创建日期: 2014年3月29日 下午10:52:00
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean replace(String key, Object value) {
		if (isUsed) {
			return client.replace(key, value);
		}
		return false;
	}

	/**
	 * @功能: 更新已有记录, 前提是记录的Key在缓存中已经存在
	 * flight
	 * @创建日期: 2014年3月29日 下午10:52:06
	 * @param key
	 * @param value
	 * @param date 超时日期
	 * @return
	 */
	public boolean replace(String key, Object value, Date date) {
		if (isUsed) {
			return client.replace(key, value, date);
		}
		return false;
	}

	/**
	 * @功能: 返回单条记录
	 * flight
	 * @创建日期: 2014年3月29日 下午11:05:06
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T> T get(String key, Class<T> clazz) {
		if (isUsed) {
			return clazz.cast(client.get(key));
		}
		return null;
	}

	/**
	 * @功能: 返回多条记录
	 * flight
	 * @创建日期: 2014年3月29日 下午11:05:29
	 * @param keys
	 * @return
	 */
	public Map<String, Object> get(String[] keys) {
		if (isUsed) {
			return client.getMulti(keys);
		}
		return null;
	}

	/**
	 * @功能: 删除记录, 执行该方法之后, 使用stats的统计结果会同步更新
	 * flight
	 * @创建日期: 2014年3月29日 下午11:05:53
	 * @param key
	 * @return
	 */
	public boolean delete(String key) {
		if (isUsed) {
			return client.delete(key);
		}
		return false;
	}

	/**
	 * @功能: 清空全部缓存数据, 执行该方法之后, 使用stats的统计结果不会马上发生变化, 每get一个不存在的item之后, 该item的值才会被动清空
	 * flight
	 * @创建日期: 2014年3月29日 下午11:06:02
	 * @return
	 */
	public boolean flushAll() {
		if (isUsed) {
			return client.flushAll();
		}
		return false;
	}

}
