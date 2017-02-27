package com.eshop4j.thread;

public class ThreadLockTest implements Runnable{
 int n = 100;
 public synchronized void m1() throws InterruptedException{
	 n = 1000;
	 Thread.sleep(5000);
	 System.out.println("m1=> n is "+n);
 }
 public void m2(){
	 System.out.println("m2=> n is "+n);
	 n = 300;
 }
 
@Override
public void run() {
	try {
		m1();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}
public static void main(String[] args) {
	ThreadLockTest target = new ThreadLockTest();
	Thread t = new Thread(target);
	t.start();
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	target.m2();
}
}
