package com.linkwee.thread;


/**
 * 
 * 线程控制方法：
 * isAlive()- 判断线程是否还活着，即是否已终止
 * getPriority() - 获取线程的优先级数值(优先级高表示CPU给你执行的时间长)
 * setPriority() - 设置线程的优先级数值
 * Thread.sleep() - 将当前线程休眠指定的毫秒数
 * join() - 某线程调用该方法，表示将当前线程与该线程“合并”，
 * 			即等待该线程结束，再恢复到当前线程的运行。
 * yield() - 让出CPU，当前线程进入就绪，等待再次被调度。
 * -----------------------线程同步---------------------
 * wait()  - 当前线程进入对象的等待池(wait pool)
 * notify()/notifyAll() - 唤醒对象等待池(wait pool)中的一个/所有等待线程
 * 
 * @author Mignet
 *
 */
public class ThreadPriorityTest {
	public static void main(String[] args) {
		SubThread sub1 = new SubThread("sub1-thread");
		SubThread sub2 = new SubThread("sub2-thread");
		sub2.setPriority(Thread.NORM_PRIORITY+3);
		sub1.start();
		sub2.start();
		System.out.println(sub1.getPriority());
		System.out.println(Thread.currentThread().getPriority());
		System.out.println(Thread.currentThread().isAlive());
	}
}

class SubThread extends Thread{
	public SubThread(String threadName){
		super(threadName);
	}
	@Override
	public void run() {
		for(int i=1;i<=100;i++){
			System.out.printf("%s now is %d \n",this.getName(),i);
		}
	}
}