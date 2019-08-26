package com.concurrent.theadsafety;



public class UnsafeCountingFactorizer{
	
	private long count = 0;
	
	
	public long getCount() {
		return count;
	}
	
	/**
	 * 完成该操作设计到三个步骤
	 * 	1.获取之前的、在共享区(此处是堆内存)count值;
	 * 	2.累加count;
	 * 	3.将count值，从当前线程中设置会共享区中;
	 */
	public void service() {
		count++;
	}
}
