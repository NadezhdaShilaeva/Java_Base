package com.shilaeva.mailService;

import java.util.Objects;

public class MailMessage implements Sendable<String> {
    private final String from;
    private final String to;
    private final String content;

    public MailMessage(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailMessage that = (MailMessage) o;

        return Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, content);
    }
}
