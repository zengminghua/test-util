package com.zmh.util.tools;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Random Number tool
 *
 * @author zengminghua
 * @version 0.0.1
 * @update 20180724
 */
public class RandomNumber {

    private static final int RANDOM_LENGTH = 19;

    /**
     * @return 19 numbers
     */
    public static String generate() {

        SecureRandom random = new SecureRandom();
        long randomNu = random.nextLong();
        String randomStr = String.valueOf(Math.abs(randomNu));

        // 如果生成的随机数小于19位, 随机补一位
        while (randomStr.length() < RANDOM_LENGTH) {
            randomStr = randomStr + String.valueOf(random.nextInt(10));
        }

        return randomStr;
    }
}
