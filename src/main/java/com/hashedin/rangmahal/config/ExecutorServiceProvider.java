package com.hashedin.rangmahal.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Pratap Bhanu
 *
 */
public class ExecutorServiceProvider {

	private static ExecutorServiceProvider INSTANCE;

	private ExecutorService executorService;

	private ExecutorServiceProvider() {
		executorService = new ThreadPoolExecutor(1, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
	}

	public synchronized static ExecutorServiceProvider getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ExecutorServiceProvider();
		}
		return INSTANCE;
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

}
