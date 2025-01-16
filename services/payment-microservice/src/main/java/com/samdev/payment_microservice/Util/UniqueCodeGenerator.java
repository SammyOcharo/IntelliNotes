package com.samdev.payment_microservice.Util;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UniqueCodeGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 9;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateUniqueCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }
        return code.toString();
    }
}
