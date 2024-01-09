package com.shilaeva.collections;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SequenceConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number;
        Deque<Integer> numbers = new ArrayDeque<>();

        int i = 0;
        while (scanner.hasNext()) {
            number = scanner.nextInt();
            if (i % 2 != 0) {
                numbers.addFirst(number);
            }

            ++i;
        }

        numbers.forEach(num -> System.out.print(num + " "));
    }

    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {

        List<? extends T> list = stream.toList();

        Optional<? extends T> min = list.stream().min(order);
        Optional<? extends T> max = list.stream().max(order);
        minMaxConsumer.accept(min.orElse(null), max.orElse(null));
    }
}
