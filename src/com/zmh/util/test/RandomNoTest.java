package com.zmh.util.test;

import com.zmh.util.tools.RandomNumber;

/**
 * xxx
 *
 * @author zengminghua
 */
public class RandomNoTest {

    public static void main(String[] args) {

        for (int i = 0; i < 10000000; i++) {
            String result = RandomNumber.generate();
            if (result.length() < 19) {
                System.out.println("生成的19位随机数不规范，实际长度为：" + result.length());
            }
        }
    }
}
