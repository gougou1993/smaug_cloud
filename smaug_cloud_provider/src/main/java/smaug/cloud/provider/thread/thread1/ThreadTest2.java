package smaug.cloud.provider.thread.thread1;

/**
 * Created by naonao on 17/12/7.
 */
public class ThreadTest2  {
    public static void main(String[] args) {
        ThreadData data = new ThreadData();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    data.addCount();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    data.decCount();
                }
            }).start();
        }
    }

}
