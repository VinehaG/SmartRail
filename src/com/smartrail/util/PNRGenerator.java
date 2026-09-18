package com.smartrail.util;

import java.util.UUID;

public class PNRGenerator {
    public static String generatePNR() {
        // SR + 6 alphanumeric characters
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
        return "SR" + uuid;
    }
}
