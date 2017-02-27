package com.linkwee.thread;

import java.util.Date;

/**
 * 
 * 线程控制方法：
 * isAlive()- 判断线程是否还活着，即是否终止
 * getPriority() - 获取线程的优先级数值(优先级高表示CPU给你执行的时间长)
 * setPriority() - 设置线程的优先级数值
 * Thread.sleep() - 将当前线程休眠指定的毫秒数
 * join() - 某线程调用该方法，表示将当前线程与该线程“合并”，即：等待该线程结束，再恢复到当前线程的运行。
 * yield() - 让出CPU，当前线程进入就绪，等待再次被调度
 * -----------------------线程同步---------------------
 * wait()  - 当前线程进入对象的等待池(wait pool)
 * notify()/notifyAll() - 唤醒对象等待池(wait pool)中的一个/所有等待线程
 * 
 * @author Mignet
 *
 */
public class ThreadSleepTest {
	public static void main(String[] args) {
		Chronograph target = new Chronograph();
		Thread sub = new Thread(target);
		sub.start();
		//让主线程睡眠10s
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//10s之后让子线程打断
//		sub.interrupt();//粗暴打断
		target.shutdown();//让run方法不再执行，线程就会结束。温柔的结束。
		
	}
}
/**
 * 秒表类，测试sleep
 * @author Mignet
 *
 */
class Chronograph implements Runnable{
	private boolean flag = true;
	@Override
	public void run() {
		while(flag){
			System.out.println(new Date());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}
	public void shutdown(){
		flag = false;
	}
}