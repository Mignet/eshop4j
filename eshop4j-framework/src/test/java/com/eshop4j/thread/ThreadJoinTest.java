package com.eshop4j.thread;


/**
 * 
 * 线程控制方法：
 * isAlive()- 判断线程是否还活着，即是否已终止
 * getPriority() - 获取线程的优先级数值(优先级高表示CPU给你执行的时间长)
 * setPriority() - 设置线程的优先级数值
 * Thread.sleep() - 将当前线程休眠指定的毫秒数
 * join() - 某线程调用该方法，表示将当前线程与该线程“合并”，
 * 			即等待该线程结束，再恢复到当前线程的运行。
 * yield() - 让出CPU，当前线程进入就绪，等待再次被调度
 * -----------------------线程同步---------------------
 * wait()  - 当前线程进入对象的等待池(wait pool)
 * notify()/notifyAll() - 唤醒对象等待池(wait pool)中的一个/所有等待线程
 * 
 * @author Mignet
 *
 */
public class ThreadJoinTest {
	public static void main(String[] args) {
		MyThread sub = new MyThread();
		sub.setName("sub-thread");
		sub.start();
		try {
			sub.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(int i=0;i<10;i++){
			System.out.println(" I 'm Main thread! ");
		}
	}
}

class MyThread extends Thread{
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.printf("My name is %s\n",this.getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}
}