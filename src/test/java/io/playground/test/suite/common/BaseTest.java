package io.playground.test.suite.common;

public class BaseTest {

    private final String toString = System.nanoTime() + "@" + getClass().getName();

    @Override
    public String toString() {
        return toString;
    }

    protected SoftAssertJ softly() {
        return SoftAssertJ.getInstance();
    }
}
