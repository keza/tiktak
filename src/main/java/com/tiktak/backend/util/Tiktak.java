package com.tiktak.backend.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tiktak {

    public static List<Integer> fixStream(Stream<Stream<String>> streams) {
        List<String> allStrings = streams.flatMap(s -> s).collect(Collectors.toList());

        List<Integer> numbers = allStrings.stream()
                .filter(s -> s != null && s.matches("-?\\d+"))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        List<List<Integer>> triplets = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i += 3) {
            if (i + 2 < numbers.size()) {
                List<Integer> triplet = numbers.subList(i, i + 3);
                if (triplet.stream().mapToInt(Integer::intValue).sum() >= 90) {
                    triplets.add(triplet);
                }
            }
        }

        List<Integer> validNumbers = triplets.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        if (validNumbers.size() % 3 != 0) {
            validNumbers.subList(validNumbers.size() - validNumbers.size() % 3, validNumbers.size()).clear();
        }

        return validNumbers;
    }

    public static void main(String[] args) {
        Stream<String> firstStream = Stream.of("0", "s1", null, "35", "90", "60");
        Stream<String> secondStream = Stream.of("t!t", null, null, "15", "");
        Stream<String> thirdStream = Stream.of("75", "95", "0", "0", null, "ssss", "0", "-15");
        Stream<String> fourthStream = Stream.of("25", "fgdfg", "", "105", "dsfsdf", "-5");

        Stream<Stream<String>> mainStream = Stream.of(firstStream, secondStream, thirdStream, fourthStream);

        List<Integer> result = fixStream(mainStream);
        System.out.println(result);
    }
}

