import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.yestae.user.common.util.ThreadPoolExecutorUtil;

public class Test {
	
	
	public static void main(String[] args) {
		//创建线程池
		ThreadPoolExecutorUtil executorService = new ThreadPoolExecutorUtil(100);
		for(int i = 0; i < 200; i++){
			int j = i;
			executorService.execute(new Runnable(){
				
				@Override
				public void run() {
					try {
						System.out.println(j);
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			});
		}
		executorService.shutdown();
		
		System.out.println("执行完毕");
	}
}
