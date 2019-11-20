package com.lq.concurrency._03_concbase;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class _01_MultiThread {
	
	public static void main(String[] args) {
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
		for (int i = 0; i < threadInfos.length; i++) {
			System.out.println("[" + threadInfos[i].getThreadId() + "]" + threadInfos[i].getThreadName());
		}
	}
	
}
