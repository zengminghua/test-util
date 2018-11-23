package com.zmh.util.test;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * 循环方法测试
 *
 * @author zengminghua
 */
public class CycleTest {

    public static void main(String[] args) {

        List<Integer> integerList = new ArrayList<Integer>(16);

        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < 20; i++) {
            integerList.add(secureRandom.nextInt());
            if (i == 15) {
                integerList.add(20);
            }
        }

        for (int i = 0; i < integerList.size(); i++) {

            System.out.println(integerList.get(i));
            if (integerList.get(i) == 20) {
                break;
            }
        }
        System.out.println("done.");
    }
}
