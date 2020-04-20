package br.com.flaviogf.criptografiadejuliocesar;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Cryptography {

    public String encrypt(String text) {
        if (text == null) {
            throw new NullPointerException();
        }

        if (text.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return encrypt(chars(text.toLowerCase()));
    }

    public String decrypt(String text) {
        if (text == null) {
            throw new NullPointerException();
        }

        if (text.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return decrypt(chars(text.toLowerCase()));
    }

    private Stream<Character> chars(String text) {
        return text.chars().mapToObj(it -> new Character((char) it));
    }

    private String encrypt(Stream<Character> chars) {
        return chars.map(this::encrypt).map(String::valueOf).collect(Collectors.joining(""));
    }

    private Character encrypt(Character letter) {
        if (Character.isWhitespace(letter) || Character.isDigit(letter)) {
            return letter;
        }

        List<Character> alphabetic = alphabetic();

        Integer current = alphabetic.indexOf(letter);

        Integer next = (current + 3) % 26;

        return alphabetic.get(next);
    }

    private String decrypt(Stream<Character> chars) {
        return chars.map(this::decrypt).map(String::valueOf).collect(Collectors.joining(""));
    }

    private Character decrypt(Character letter) {
        if (Character.isWhitespace(letter) || Character.isDigit(letter)) {
            return letter;
        }

        List<Character> alphabetic = alphabetic();

        Integer current = alphabetic.indexOf(letter);

        if (current > 2) {
            Integer next = (current - 3) % 26;
            return alphabetic.get(next);
        }

        Integer next = (current - 3) % 26 + 26;

        return alphabetic.get(next);
    }

    private List<Character> alphabetic() {
        return IntStream.range(97, 123).mapToObj(it -> new Character((char) it)).collect(Collectors.toList());
    }
}
