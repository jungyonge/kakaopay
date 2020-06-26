package com.jungyonge.kakaopay.util;

import java.util.Date;
import java.util.Random;

public class RandomTokenUtil {

    public static String getRandomToken(int length){
        StringBuffer tempRandomToken = new StringBuffer();
        Random rnd = new Random();
        rnd.setSeed((new Date().getTime()));
        for (int i = 0; i < length; i++) {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
                case 0:
                    // a-z
                    tempRandomToken.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    // A-Z
                    tempRandomToken.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    // 0-9
                    tempRandomToken.append((rnd.nextInt(10)));
                    break;
            }
        }

        return tempRandomToken.toString();
    }
}
