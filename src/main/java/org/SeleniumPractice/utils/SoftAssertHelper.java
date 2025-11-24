package org.SeleniumPractice.utils;

import org.testng.asserts.SoftAssert;

public class SoftAssertHelper {
    private static ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();

    public static SoftAssert getSoftAssert() {
        if (softAssert.get() == null) {
            softAssert.set(new SoftAssert());
        }
        return softAssert.get();
    }

    public static void assertAll() {
        if (softAssert.get() != null) {
            try {
                softAssert.get().assertAll();
            } catch (AssertionError e) {
                System.out.println("Soft assertions failed: " + e.getMessage());
                throw e;
            } finally {
                softAssert.remove();
            }
        }
    }

    public static void reset() {
        softAssert.remove();
    }
}