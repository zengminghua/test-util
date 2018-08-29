package com.zmh.util;

import com.zmh.util.tools.RandomNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * common tools for zmh
 *
 * @author zengminghua
 * @version v0.0.1
 * @update 20180724
 */
public class Main {

    /**
     * the value of for(xxx){} counts
     */
    private static final int CIRCLE_COUNT = 1;

    private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        // 循环调用方法
        for (int i = 0; i < CIRCLE_COUNT; i++) {

            String result = RandomNumber.generate();

            LOGGER.info("输出结果：{}", result);
        }

    }
}


