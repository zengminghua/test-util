package com.zmh.util.test;

import com.zmh.util.tools.ConcealTool;

/**
 * xxx
 *
 * @author zengminghua
 */
public class SubStringTest {

    public static void main(String[] args) {
        //        String fileName = "aaa.bbb.ccc.ggg.gpg";
        //        System.out.println(fileName.substring(0, fileName.indexOf(".gpg")));

        //        String str = "asdasdas|d";
        //
        //        String[] strArray = str.split("\\|");
        //        for(int i = 0; i < strArray.length; i++){
        //            System.out.println(strArray[i]);
        //        }

        String testStr = "asd|ads|asd";
        if (testStr.endsWith("|")) {
            testStr = testStr.substring(0, testStr.length() - 1);
        }

//        System.out.println(testStr);


        String key = "1名asdasdasd";

        System.out.println(String.valueOf(key.charAt(0)));

//        String replaceAfter = key.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");

//        System.out.println(ConcealTool.doConceal(key, 60));
    }
}
