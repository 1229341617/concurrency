package com.lq.concurrency._01_cs;

public class _01_Concurrent {
	
	private static final long count = 10*10000*10000;
	
	/**
	 * 1w		并发:1ms,串行:0s
	 * 10w		并发:2ms,串行:3s
	 * 100w		并发:6ms,串行:4s
	 * 1000w	并发:11ms,串行:14s
	 * 10000w	并发:70ms,串行:96s
	 * 100000w	并发:665ms,串行:940s
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		System.out.println(concurrency());
		System.out.println(serial());
	}
	
	private static String concurrency() throws InterruptedException {
		long startTimes = System.currentTimeMillis();
		
		Thread start = new Thread(new Runnable() {
			@Override
			public void run() {
				int a = 0;
				for(int i = 0; i < count; i++) {
					a += 1;
				}
			}
		});
		start.start();
		
		int b = 0;
		for(int i = 0; i < count; i++) {
			b += 1;
		}
		
		start.join();
		
		long times = System.currentTimeMillis() - startTimes;
		
		return "currency:" + times + "ms";
	}
	
	private static String serial() {
		long startTimes = System.currentTimeMillis();
		
		int a = 0;
		for(int i = 0; i < count; i++) {
			a += 1;
		}
		
		int b = 0;
		for(int i = 0; i < count; i++) {
			b += 1;
		}
		
		long times = System.currentTimeMillis() - startTimes;
		
		return "serial:" + times + "ms";
	}
	
	
}
