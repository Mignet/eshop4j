package com.eshop4j.test.concurrent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 并发测试
 * @author Mignet
 *
 */
public class CyclicBarrierTest {
	/**并发线程数 */
	private static int count = 1000;
	//初始化
    public static void main(String[] args) {
         CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
         ExecutorService executorService = Executors.newFixedThreadPool(count);
         for (int i = 0; i < count; i++)
              executorService.execute(new CyclicBarrierTest().new Task(cyclicBarrier));

         executorService.shutdown();
         while (!executorService.isTerminated()) {
              try {
                   Thread.sleep(10);
              } catch (InterruptedException e) {
                   e.printStackTrace();
              }
         }
    }

    public class Task implements Runnable {
         private CyclicBarrier cyclicBarrier;

         public Task(CyclicBarrier cyclicBarrier) {
              this.cyclicBarrier = cyclicBarrier;
         }

         @Override
         public void run() {
              try {
                   // 等待所有任务准备就绪
                   cyclicBarrier.await();
                   // 并发请求
                // 待测试的url https://www.baidu.com/s?ie=UTF-8&wd=t%E5%91%97
					String host = "https://www.baidu.com/s?";
					String para = "ie=UTF-8&wd=t%E5%91%97";
					System.out.println(host + para);
					URL url = new URL(host);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					// connection.setRequestMethod("POST");
					// connection.setRequestProperty("Proxy-Connection", "Keep-Alive");
					connection.setDoOutput(true);
					connection.setDoInput(true);
					PrintWriter out = new PrintWriter(connection.getOutputStream());
					out.print(para);
					out.flush();
					out.close();
					BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					String line = "";
					String result = "";
					while ((line = in.readLine()) != null) {
						result += line;
					}
					System.out.println(result);
              } catch (Exception e) {
                   e.printStackTrace();
              }
         }
    }
}