package com.fa993.utils;

import java.util.UUID;

public class Utility {

    public static String getId() {
        return UUID.randomUUID().toString();
    }

    public static int getPageLength() {
        return 10;
    }

}
