package io.playground.test.suite.util;

public class SleepUtil {
    
    private SleepUtil() {}

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
