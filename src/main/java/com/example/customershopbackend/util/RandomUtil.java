package com.example.customershopbackend.util;

import java.util.Random;

public class RandomUtil {

    public static String random6Digits(){
        Random random = new Random();
        return String.valueOf(random.nextInt(999999));
    }

}
