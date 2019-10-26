package com.lq.concurrency._02_principle;

import java.util.concurrent.CountDownLatch;

public class _01_CacheLineFill implements Runnable {
	static class VolatileLong {
		// �������Ҫ��䣬ֻ��Ҫע�͵���δ��뼴��
	    public volatile long p1, p2, p3, p4, p5, p6; 
	    //ʵ�ʲ�����ֵ
	    public volatile long value = 0L;
	}
	
    //�̸߳���
    public static final int NUM_THREADS = 4; // change
    //ѭ���޸����������ݵĴ���
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    //�����±�
    private final int arrayIndex;
    //����������
    private static VolatileLong[] longs;
    //��������ͬʱ��������߳�
    private static CountDownLatch cdl = new CountDownLatch(NUM_THREADS);

    public _01_CacheLineFill(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }
    
    private static void runTest() throws InterruptedException {
        //��ʼ���߳���
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new _01_CacheLineFill(i));
        }
        //��ʼ���������߳�
        for (Thread t : threads) {
            t.start();
        }
        //���߳�����ֱ���������߳̽���
        cdl.await();
    }

    @Override
    public void run() {
        //���߳�����³����޸�������ĳһ��volatileֵ
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
        cdl.countDown();
    }
	
	public static void main(final String[] args) throws Exception {
        Thread.sleep(1000);
        System.out.println("starting....");
        //��ʼ������
        longs = new VolatileLong[NUM_THREADS];
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
        final long start = System.nanoTime();
        runTest();
        System.out.println("duration = " + (System.nanoTime() - start) / 1000000);
    }

	
	
}
