package com.sfy.util.thread;

import java.util.concurrent.*;

/**
 * 线程池
 */
public class ThreadPool {

	
	private final static int corePoolSize = 40;// 核心池大小
	private final static int maximumPoolSize = 100;// 最大线程数
	private final static long keepAliveTime = 2;// 空闲等待时间
	private final static TimeUnit timeUnit = TimeUnit.MINUTES;// 时间单位
	private final static int queueSize = 200;// 队列大小
	private final static BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<Runnable>(queueSize);// 队列
	private final static RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();// 反馈控制机制
	private static ExecutorService pool;// 线程池
	
	/****** 初始化线程池 ******/
	static {
		pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, blockingQueue, rejectedExecutionHandler);
	}
	
	/**
	 * 在未来某个时间执行给定的命令
	 * @param runnable
	 */
	public static void execute(Runnable runnable) throws RejectedExecutionException {
		pool.execute(runnable);
	}
	
	/**
	 * 提交一个 Runnable 任务用于执行,并返回一个表示该任务的Future
	 * @param runnable
	 */
	public static Future<?> submit(Runnable runnable) throws RejectedExecutionException {
		return pool.submit(runnable);
	}
}
