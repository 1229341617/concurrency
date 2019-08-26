package com.concurrent.theadsafety;

import java.util.concurrent.atomic.AtomicLong;


public class CountingFactorizer{
	//堆内存对象，将count自增的一系列复合操作封装成原子性操作
	private final AtomicLong count = new AtomicLong(0);
	
	//get和set都为final修饰
	public long getCount() {
		return count.get();
	}
	
	public void service() {
		//count++，该方法也为final修饰
		count.incrementAndGet();
	}

}
