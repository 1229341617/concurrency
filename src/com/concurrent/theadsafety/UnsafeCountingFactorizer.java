package com.concurrent.theadsafety;



public class UnsafeCountingFactorizer{
	
	private long count = 0;
	
	
	public long getCount() {
		return count;
	}
	
	/**
	 * ��ɸò�����Ƶ���������
	 * 	1.��ȡ֮ǰ�ġ��ڹ�����(�˴��Ƕ��ڴ�)countֵ;
	 * 	2.�ۼ�count;
	 * 	3.��countֵ���ӵ�ǰ�߳������ûṲ������;
	 */
	public void service() {
		count++;
	}
}
