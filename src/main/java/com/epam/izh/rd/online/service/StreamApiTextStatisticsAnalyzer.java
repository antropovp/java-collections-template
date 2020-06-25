package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        return getWords(text).stream().reduce((o1, o2) -> o1 + o2).get().length();
    }

    @Override
    public int countNumberOfWords(String text) {
        return getWords(text).size();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return getUniqueWords(text).size();
    }

    @Override
    public List<String> getWords(String text) {

        List<String> listOfWords = new ArrayList<>();
        Arrays.stream(text.split("\\p{P}|\\s")).forEach(o -> listOfWords.add(o));
        listOfWords.removeAll(Arrays.asList(""));

        return listOfWords;
    }

    @Override
    public Set<String> getUniqueWords(String text) {

        Set<String> listOfWords = new HashSet<>();
        listOfWords.addAll(getWords(text));

        return listOfWords;
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {

        Map<String, Integer> mapOfWords = new HashMap<>();
        getWords(text).stream().forEach(o -> {
            if (mapOfWords.containsKey(o)) {
                mapOfWords.put(o, mapOfWords.get(o) + 1);
            } else {
                mapOfWords.put(o, 1);
            }
        });

        return mapOfWords;
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {

        List<String> sortedWords = new ArrayList<>();

        if (direction == Direction.ASC) {
            getWords(text).stream().sorted(Comparator.comparingInt(String::length))
                    .forEach(o -> sortedWords.add(o));
        } else if (direction == Direction.DESC) {
            getWords(text).stream().sorted(Comparator.comparingInt(String::length).reversed())
                    .forEach(o -> sortedWords.add(o));
        }

        return sortedWords;
    }
}
