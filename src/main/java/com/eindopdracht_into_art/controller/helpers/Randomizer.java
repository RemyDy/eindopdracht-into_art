package com.eindopdracht_into_art.controller.helpers;

import java.util.UUID;

public final class Randomizer {

    public static String createRandomToken() {
        return UUID.randomUUID().toString();
    }
}
