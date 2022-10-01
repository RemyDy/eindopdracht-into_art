package com.eindopdracht_into_art.helpers;

import java.util.Base64;
import java.util.Random;
import java.util.UUID;

public final class Randomizer {

    public static String createRandomUUIDToken() {
        return UUID.randomUUID().toString();
    }

    public static String generateRandomizedByteArrayAndBase64EncodeToString() {
        byte[] bytes = new byte[24];
        new Random().nextBytes(bytes);
        return new String(Base64.getEncoder().encode(bytes));
    }

}
