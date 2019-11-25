package services;

public class MyThreadService extends Thread {

    @Override
    public void run () {
        for (int i = 0; i < 100; i++) {
            System.out.println("MyThreadService, thread name:"+ Thread.currentThread().getName() +", i:"+ i);
        }
    }

}
