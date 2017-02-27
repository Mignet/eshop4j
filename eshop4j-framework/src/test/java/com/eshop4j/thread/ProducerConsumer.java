package com.eshop4j.thread;

public class ProducerConsumer {
	public static void main(String[] args) {
		Basket b = new Basket();
	/*	for(int i=0;i<10;i++){
			System.out.println(b.index);
			b.push(new Bread(i));
		}
		for(int j=b.index;j>0;j--){
			b.pop();
			System.out.println(b.index);
		}*/
		Producer p1 = new Producer(b);
		Producer p2 = new Producer(b);
		Producer p3 = new Producer(b);
		Consumer c = new Consumer(b);
		new Thread(p1).start();
		new Thread(p2).start();
		new Thread(p3).start();
		new Thread(c).start();
	}
}
/**
 * 面包
 * @author Mignet
 *
 */
class Bread {
	int id;//编号

	public Bread(int id) {
		this.id = id;
	}
}
/**
 * 篮子
 * @author Mignet
 *
 */
class Basket {
	int index=0;
	int capacity = 10;
	Bread[] list = null;
	public Basket(){
		this.list = new Bread[capacity];
	}
	public Basket(int capacity){
		this.capacity = capacity;
		this.list = new Bread[capacity];
	}
	public synchronized Bread pop(){
		while(index==0){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notifyAll();
		--index;
		return list[index];
	}
	public synchronized int push(Bread b){
		while(index==capacity){
			try {
				this.wait();//有锁才有wait,wait的时候就不再拥有锁
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notifyAll();
		list[index] = b;
		index++;
		return index;
	}
}
/**
 * 生产者：做包子的厨子
 * @author Mignet
 *
 */
class Producer implements Runnable{

	Basket b = null;
	public Producer(Basket b){
		this.b = b;
	}
	
	@Override
	public void run() {
		for(int i=0;i<20;i++){
			Bread bb = new Bread(i);
			b.push(bb);
			System.out.println("Producer bread:"+bb.id);
			try {
				Thread.sleep((int)Math.random()*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
/**
 * 消费者：吃货
 * @author Mignet
 *
 */
class Consumer implements Runnable{
	
	Basket b = null;
	public Consumer(Basket b){
		this.b = b;
	}
	
	@Override
	public void run() {
		while(b.index>0){
			System.out.println("Consumer bread:"+b.pop().id);
			try {
				Thread.sleep((int)Math.random()*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}