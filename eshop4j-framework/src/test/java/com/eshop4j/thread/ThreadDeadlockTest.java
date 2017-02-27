package com.eshop4j.thread;


/**
 * 死锁
 * 
 * @author Mignet
 *
 */
public class ThreadDeadlockTest implements Runnable{
	private int num = 0;
	
	private static Object o1=new Object();
	private static Object o2=new Object();
	
	public static void main(String[] args) {
		ThreadDeadlockTest target1 = new ThreadDeadlockTest();
		ThreadDeadlockTest target2 = new ThreadDeadlockTest();
		Thread t1 = new Thread(target1);
		target2.num = 1;
		Thread t2 = new Thread(target2);
		t1.setName("t1");
		t2.setName("t2");
		t1.start();
		t2.start();
	}

	@Override
	public void run() {
		if(num == 0){
			synchronized (o1) {
				System.out.printf("%s===>%s\n",Thread.currentThread().getName(),o1);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (o2) {
					System.out.printf("%s===>%s\n",Thread.currentThread().getName(),o2);
				}
			}
		}
		if(num == 1){
			synchronized (o2) {
				System.out.printf("%s===>%s\n",Thread.currentThread().getName(),o2);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (o1) {
					System.out.printf("%s===>%s\n",Thread.currentThread().getName(),o1);
				}
			}
		}
	}
}
