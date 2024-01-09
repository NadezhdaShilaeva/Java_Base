package com.shilaeva.mailService;

public interface Sendable<T> {
    String getFrom();
    String getTo();
    T getContent();
}
