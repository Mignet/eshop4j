package com.linkwee.thread;
/**
 * 
 * 主线程与子线程
 * [进程是静态的概念，包含多个线程，线程是执行路径]
 * @author Mignet
 *
 */
public class ThreadBaseTest {
	public static void main(String[] args) {
		new Thread(new RunnableObj()).start();
		for(int i=0;i<10;i++){
			System.out.println("main thread:"+i);
		}
	}
	
}
class RunnableObj implements Runnable{
	
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println("obj:"+i);
		}
		
	}
}
