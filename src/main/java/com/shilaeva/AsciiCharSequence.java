package com.shilaeva;

import java.lang.reflect.Array;

public class AsciiCharSequence implements CharSequence {
    private final byte[] array;

    public  AsciiCharSequence(byte[] array) {
        this.array = array;
    }

    @Override
    public int length() {
        return array.length;
    }

    @Override
    public char charAt(int index) {
        return (char) array[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        byte[] newArray = new byte[end - start];
        for (int i = start; i < end; ++i) {
            newArray[i - start] = array[i];
        }

        return new AsciiCharSequence(newArray);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (byte oneByte: array) {
            result.append((char) oneByte);
        }

        return result.toString();
    }
}
