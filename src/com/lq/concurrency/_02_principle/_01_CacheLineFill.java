package com.lq.concurrency._02_principle;

import java.util.concurrent.CountDownLatch;

public class _01_CacheLineFill implements Runnable {
	static class VolatileLong {
		// 如果不需要填充，只需要注释掉这段代码即可
	    public volatile long p1, p2, p3, p4, p5, p6; 
	    //实际操作的值
	    public volatile long value = 0L;
	}
	
    //线程个数
    public static final int NUM_THREADS = 4; // change
    //循环修改数组中数据的次数
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    //数组下标
    private final int arrayIndex;
    //操作的数组
    private static VolatileLong[] longs;
    //闭锁用于同时开启多个线程
    private static CountDownLatch cdl = new CountDownLatch(NUM_THREADS);

    public _01_CacheLineFill(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }
    
    private static void runTest() throws InterruptedException {
        //初始化线程组
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new _01_CacheLineFill(i));
        }
        //开始运行所有线程
        for (Thread t : threads) {
            t.start();
        }
        //主线程阻塞直到所有子线程结束
        cdl.await();
    }

    @Override
    public void run() {
        //多线程情况下持续修改数组中某一个volatile值
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
        cdl.countDown();
    }
	
	public static void main(final String[] args) throws Exception {
        Thread.sleep(1000);
        System.out.println("starting....");
        //初始化数组
        longs = new VolatileLong[NUM_THREADS];
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
        final long start = System.nanoTime();
        runTest();
        System.out.println("duration = " + (System.nanoTime() - start) / 1000000);
    }

	
	
}
