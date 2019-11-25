package runnable;

import services.MyCallableService;
import services.MyRunnableService;
import services.MyThreadService;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest {

    /**
    public static void main (String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println("MultiThreadController myThread, thread name:" + Thread.currentThread().getName() + ", i:"+ i);
            if (i == 30) {
                // 创建一个新的线程myThread1，此线程进入新建状态
                Thread myThread1 = new MyThreadService();
                // 创建一个新的线程myThread2，此线程进入新建状态
                Thread myThread2 = new MyThreadService();
                // 调用start()方法，使线程进入就绪状态
                myThread1.start();
                myThread2.start();
            }
        }
    }
     **/

    /**
    public static void main (String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println("MultiThreadController myRunnable, thread name:" + Thread.currentThread().getName() + ", i:"+ i);
            if (i == 30) {
                // 创建一个Runnable实现类的对象
                Runnable myRunnable = new MyRunnableService();

                // 将myRunnable作为Thread target创建新的线程
                Thread thread1 = new Thread(myRunnable);
                Thread thread2 = new Thread(myRunnable);

                // 调用start()方法，使线程进入就绪状态
                thread1.start();
                thread2.start();
            }
        }
    }
     **/

    public static void main (String[] args) {
        // 创建myCallable对象
        Callable<Integer> myCallable = new MyCallableService();
        // 使用FutureTask来包装myCallable对象
        FutureTask<Integer> ft = new FutureTask<Integer>(myCallable);

        for(int i = 0; i < 100; i++) {
            System.out.println("MultiThreadController myCallable, thread name:"+ Thread.currentThread().getName() + ", i:"+ i);
            if (i == 30) {
                // FutureTask对象作为Thread对象的target创建新的线程
                Thread thread = new Thread(ft);
                // 线程进入到就绪状态
                thread.start();
            }
        }
        System.out.println("主线程for执行完毕...");

        try {
            // 取得新创建的新线程中的call()方法返回的结果
            int sum = ft.get();
            System.out.println("sum:"+ sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
