package org.oss.tx.utils;

import org.apache.commons.text.RandomStringGenerator;

import java.security.SecureRandom;

public class IdGenerator {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    char[][] CHARACTERS_RANGE = {{'a', 'z'}, {'A', 'Z'}};

    private final RandomStringGenerator randomGenerator;

    private final int idDefaultSize;

    public IdGenerator(int idDefaultSize) {
        this.randomGenerator = new RandomStringGenerator.Builder()
                .withinRange(CHARACTERS_RANGE)
                .usingRandom(SECURE_RANDOM::nextInt)
                .build();

        this.idDefaultSize = idDefaultSize;
    }

    public String generateId() {
        return randomGenerator.generate(idDefaultSize);
    }

    public String generateId(String prefix, int size) {
        if (prefix == null) {
            throw new IllegalArgumentException("Invalid prefix - Cannot be null.");
        }
        return prefix + randomGenerator.generate(size - prefix.length());
    }
}
