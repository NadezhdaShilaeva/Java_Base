package com.shilaeva.mailService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class MailService<T> implements Consumer<Sendable<T>> {
    private final Map<String, List<T>> mailBox;

    public MailService() {
        mailBox = new HashMap<String, List<T>>() {
            @Override
            public List<T> get(Object key) {
                return super.get(key) == null ? Collections.<T>emptyList() : super.get(key);
            }
        };
    }

    public Map<String, List<T>> getMailBox() {
        return mailBox;
    }

    @Override
    public void accept(Sendable<T> t) {
        if (mailBox.get(t.getTo()).equals(Collections.<T>emptyList())) {
            mailBox.put(t.getTo(), List.of(t.getContent()));
        } else {
            mailBox.get(t.getTo()).add(t.getContent());
        }
    }
}
