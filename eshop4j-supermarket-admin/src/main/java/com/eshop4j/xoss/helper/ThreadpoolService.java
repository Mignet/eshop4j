package com.eshop4j.xoss.helper;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.eshop4j.xoss.trace.TraceRunnable;

@Component("threadpoolService")
public class ThreadpoolService implements InitializingBean,DisposableBean{
	private static Logger logger = Logger.getLogger(ThreadpoolService.class);
	private static ExecutorService execSrv = null;
	
	@Value("${THREADPOOL_CORESIZE}")
	private int THREADPOOL_CORESIZE; //线程池核心线程数

	@Value("${THREADPOOL_MAXSIZE}") 
	private int threadpool_maxsize; //线程池最大线程数

	@Value("${THREADPOOL_KEEPTIME}")
	private int threadpool_keeptime; //空闲线程存活时间

	@Value("${ARRAY_CAPACITY}") 
	private int array_capacity; //线程池队列容量
	

	public void init() {
		execSrv = new ThreadPoolExecutor(THREADPOOL_CORESIZE,
				threadpool_maxsize, threadpool_keeptime, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(array_capacity),
				new RunSelfPolicy());
	}

	public static void execute(Runnable task) {
		execSrv.execute(new TraceRunnable(task));
	}

	public static void shutdown() {
		execSrv.shutdown();
	}

	public static List<Runnable> shutdownNow() {
		List<Runnable> taskList = execSrv.shutdownNow();
		return taskList;
	}

	public static class RunSelfPolicy implements RejectedExecutionHandler {
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			if (!executor.isShutdown()) {
				if (!executor.getQueue().offer(r)) {
					logger.warn("ThreadpoolService|The ThreadPool have been exceeded! The current task must be discard!");
					r.run();
				}
			}
		}
	}

	@Override
	public void destroy() throws Exception {
		execSrv.shutdown();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}
}