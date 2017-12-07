package smaug.cloud.provider.mode.singleton;

/**
 * Created by naonao on 17/11/26.
 * 饿汉模式
 */
public class Singleton1 {
    private static Singleton1 singleton1 = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 newInstance() {
        return singleton1;
    }
}
