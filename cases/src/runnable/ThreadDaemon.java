package runnable;

public class ThreadDaemon {

    public static void main(String[] args) {
        Thread myThread = new MyThreadDaemon();
        for (int i = 0; i < 100; i++) {
            System.out.println("main thread i = " + i);
            if (i == 20) {
                System.out.println("isDaemon before:"+myThread.isDaemon());
                myThread.setDaemon(true);
                myThread.start();
                System.out.println("isDaemon after:"+myThread.isDaemon());
            }
        }
    }

}

class MyThreadDaemon extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("i = " + i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
