package com.yestae.user.common.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorUtil extends ThreadPoolExecutor {

	private CountDownLatch latch;
	
	public ThreadPoolExecutorUtil(int poolSize) {
		super(poolSize, poolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		latch = new CountDownLatch(1);
	}
	
	protected void terminated() { 
		this.latch.countDown();
	}
	
	public void shutdown(){
		super.shutdown();
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
