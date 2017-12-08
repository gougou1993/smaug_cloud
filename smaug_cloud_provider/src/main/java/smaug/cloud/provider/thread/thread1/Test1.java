package smaug.cloud.provider.thread.thread1;

/**
 * Created by naonao on 17/12/7.
 */
public class Test1 {
    public static void main(String[] args) {
        /**
         * 几个线程共同操作一个值,并且 run方法里的代码和逻辑一毛一样,我们只需要写一个类来实现Runnable 接口, 然后丢给几个线程共同去操作即可
         */
        ThreadTest1 test1 = new ThreadTest1();
        Thread t1 = new Thread(test1, "t1");
        Thread t2 = new Thread(test1, "t2");
        Thread t3 = new Thread(test1, "t3");
        Thread t4 = new Thread(test1, "t4");
        Thread t5 = new Thread(test1, "t5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
