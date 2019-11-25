package services;

public class MyRunnableService implements Runnable {

    @Override
    public void run () {
        for (int i = 0; i < 100; i++) {
            System.out.println("MyRunnableService, thread name:"+ Thread.currentThread().getName() +", i:"+ i);
        }
    }

}