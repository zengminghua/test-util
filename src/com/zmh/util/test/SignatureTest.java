package com.zmh.util.test;

import com.zmh.util.tools.SymmetricEncryptTool;
import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * xxx
 *
 * @author zengminghua
 */
public class SignatureTest {

    public static void main(String[] args) {

        SymmetricEncryptTool symmetricEncryptTool = new SymmetricEncryptTool();

        String password = "cSjbFQ3aOFNRJkWO+wxDGg==";
//        String encryptResult = symmetricEncryptTool.symmetricEncryprt("猜猜我是谁", password);
//        symmetricEncryptTool.symmetricDecrypt(encryptResult, password);
        symmetricEncryptTool.generateSymmetricKey();

        String key = "cSjbFQ3aOFNRJkWO+wxDGg==";
        try {
            System.out.println(new BASE64Decoder().decodeBuffer(key).length);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
