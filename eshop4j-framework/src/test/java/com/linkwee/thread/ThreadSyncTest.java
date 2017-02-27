package com.linkwee.thread;


/**
 * -----------线程同步(多个线程怎么协调资源的问题,比如上大号)-----------
 * wait()  - 当前线程进入对象的等待池(wait pool)
 * notify()/notifyAll() - 唤醒对象等待池(wait pool)中的一个/所有等待线程
 * 
 * @author Mignet
 *
 */
public class ThreadSyncTest implements Runnable{
	Timer timer = new Timer();
	public static void main(String[] args) {
		ThreadSyncTest target = new ThreadSyncTest();
		Thread t1 = new Thread(target);
		Thread t2 = new Thread(target);
		Thread t3 = new Thread(target);
		t1.setName("t1");
		t2.setName("t2"); 
		t3.setName("t3"); 
		t1.start();
		t2.start();
		t3.start();
		//同一个静态变量被多个线程操作，同时中间被sleep了一下，值就被修改了，最后得到的结果是
		/**
		 * t2 是第3个使用timer的线程 
		 * t1 是第3个使用timer的线程 
		 * t3 是第3个使用timer的线程 
		 */
		//加上锁之后，得到想要的结果。
	}

	@Override
	public void run() {
		timer.add(Thread.currentThread().getName());
	}
}

class Timer{
	private static int num = 0;
	public synchronized void add(String name) {
//		synchronized (this) {//互斥锁-锁定当前对象
			num++;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
			System.out.printf("%s 是第%d个使用timer的线程 \n", name, num);
//		}
	}
}