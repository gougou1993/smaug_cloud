package smaug.cloud.provider.mode.singleton;

/**
 * Created by naonao on 17/11/26.
 */
public class Singleton3 {
    private static class SingletonHolder {
        private static Singleton3 instance = new Singleton3();
    }

    private Singleton3() {

    }

    public static Singleton3 getInstacne() {
        return SingletonHolder.instance;
    }
}
