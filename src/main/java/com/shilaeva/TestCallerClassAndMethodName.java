package com.shilaeva;

public class TestCallerClassAndMethodName {
    public static void main(String[] args) {
        System.out.println(getCallerClassAndMethodName());
        anotherMethod();
    }

    private static void anotherMethod() {
        System.out.println(getCallerClassAndMethodName());
    }

    public static String getCallerClassAndMethodName() {
        Throwable throwable = new Throwable();
        StackTraceElement[] elements = throwable.getStackTrace();

        if (elements.length <= 2) {
            return null;
        }

        return elements[2].getClassName() + "#" + elements[2].getMethodName();
    }
}
