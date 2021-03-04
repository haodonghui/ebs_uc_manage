package com.yestae.user.common.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtil {

	private static class Holder {
		final static ThreadPoolUtil instance = new ThreadPoolUtil();
		
	}

	private ThreadPoolUtil() {
	}

	public static ThreadPoolUtil getInstance() {
		return Holder.instance;
	}

	private ExecutorService executorService = null;

	public ExecutorService getExecutorService() {
		if (executorService == null) {
			executorService = Executors.newFixedThreadPool(15);
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					executorService.shutdown();
					try {
						executorService.awaitTermination(30, TimeUnit.SECONDS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		return executorService;
	}

}
