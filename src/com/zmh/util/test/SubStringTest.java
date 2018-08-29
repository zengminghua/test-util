package com.zmh.util.test;

/**
 * xxx
 *
 * @author zengminghua
 */
public class SubStringTest {

    public static void main(String[] args){
        String fileName = "aaa.bbb.ccc.ggg.gpg";
        System.out.println(fileName.substring(0, fileName.indexOf(".gpg")));
    }
}
