package smaug.cloud.provider.thread.thread1;

/**
 * Created by naonao on 17/12/7.
 */
public class ThreadData {
    private int count = 0;

    /**
     * 加
     */
    public synchronized void addCount() {
        System.out.println(Thread.currentThread().getName() + " before zhi " + count);
        count++;
        System.out.println(Thread.currentThread().getName() + " after zhi " + count);
    }

    public synchronized void decCount() {
        System.out.println(Thread.currentThread().getName() + " before zhi " + count);
        count--;
        System.out.println(Thread.currentThread().getName() + " after zhi " + count);
    }
}
