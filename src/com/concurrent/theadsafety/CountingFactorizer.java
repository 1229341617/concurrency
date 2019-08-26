package com.concurrent.theadsafety;

import java.util.concurrent.atomic.AtomicLong;


public class CountingFactorizer{
	//���ڴ���󣬽�count������һϵ�и��ϲ�����װ��ԭ���Բ���
	private final AtomicLong count = new AtomicLong(0);
	
	//get��set��Ϊfinal����
	public long getCount() {
		return count.get();
	}
	
	public void service() {
		//count++���÷���ҲΪfinal����
		count.incrementAndGet();
	}

}
