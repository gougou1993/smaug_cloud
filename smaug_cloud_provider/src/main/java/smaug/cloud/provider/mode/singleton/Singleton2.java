package smaug.cloud.provider.mode.singleton;

/**
 * Created by naonao on 17/11/26.
 * 懒汉模式
 */
public class Singleton2 {
    private static volatile Singleton2 instance = null;

    private Singleton2() {
    }

    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            synchronized (Singleton2.class){
                if (instance == null) {
                    instance = new Singleton2(); //2
                }
            }
        }
        return instance;
    }
}
