package com.shilaeva.collections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordsCounter {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        Stream<String> stream = bufferedReader.lines();

        stream.flatMap(s -> Arrays.stream(s.split("[^a-zA-Zа-яА-Я0-9']+")))
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting())).entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry::getKey))
                .limit(10).forEach(entry -> System.out.println(entry.getKey()));
    }
}
