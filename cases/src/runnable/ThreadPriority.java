package runnable;

public class ThreadPriority {

    public static void main(String[] args) {
        Thread myThread = new MyThreadPriority();
        for (int i = 0; i < 100; i++) {
            System.out.println("main thread i = " + i);
            if (i == 20) {
                myThread.setPriority(Thread.MAX_PRIORITY);
                myThread.start();
            }
        }
    }
}

class MyThreadPriority extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("i = " + i);
        }
    }
}

