package smaug.cloud.provider.thread.thread1;

/**
 * Created by naonao on 17/12/7.
 */
public class ThreadTest1 implements Runnable {
    private int i = 5;

    @Override
    public void run() {
        /**
         * 我看这里有的地方也用了 synchronized () 结果是一样的哦
         */

//        if (i >= 0) {
//            i--;
//            System.out.println(Thread.currentThread().getName() + " 值 " + i);
//        }
        synchronized (this) {
            if (i >= 0) {
                i--;
                System.out.println(Thread.currentThread().getName() + " 值 " + i);
            }
        }
    }
}
