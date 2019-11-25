package services;

import java.util.concurrent.Callable;

public class MyCallableService implements Callable<Integer> {

    /**
     * 与run()方法不同，call()方法具有返回值
     * @return
     */
    @Override
    public Integer call () {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            System.out.println("MyCallableService, thread name:"+ Thread.currentThread().getName() +", i:"+ i);
            sum += i;
        }
        return sum;
    }
}
