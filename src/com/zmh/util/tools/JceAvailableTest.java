package com.zmh.util.tools;

import javax.crypto.Cipher;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Set;

/**
 * 验证是否安装JCE
 * <p>
 * 需要先配置运行该代码的JDK版本
 *
 * @author zengminghua
 * @version 0.0.1
 * @update 20180724
 */
public class JceAvailableTest {

    /**
     * 校验的安全算法
     */
    private static final String SECURITY_ALGORITHMS_CIPHER = "Cipher";

    public static void main(String[] args) {
        try {
            Set<String> algorithms = Security.getAlgorithms(SECURITY_ALGORITHMS_CIPHER);
            for (String algorithm : algorithms) {
                int max;
                max = Cipher.getMaxAllowedKeyLength(algorithm);

                // 若结果输出［2147483647bit］，即验证通过
                System.out.printf("%-22s: %dbit%n", algorithm, max);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
