package com.example;

import java.util.Random;

public class Joker {

    private static Random random = new Random();
    private static String jokes[] = {
            "Pretend I said something funny, and laugh, hahaha.",
            "No really. Just laugh.",
            "Here's a funny joke",
            "Here's a copy of funny joke",
            "And another funny joke",
            "Second last funny joke",
            "Last funny joke",
    };

    /**
     * @return a random joke from an array of jokes
     */
    public static String getJoke() {
        return jokes[random.nextInt(jokes.length)];
    }

}
