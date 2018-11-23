package com.zmh.util.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 对称加密算法工具类
 * <p>
 * 支持AES
 *
 * @author zengminghua
 * @date 20180911
 */
public class SymmetricEncryptTool {

    /**
     * sl4j日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(SymmetricEncryptTool.class);

    /**
     * 密码器类型，算法/模式/补码方式
     */
    private static final String CIPHER_TYPE = "AES/CBC/PKCS5Padding";

    /**
     * 安全随机数算法
     */
    private static final String SECURE_RANDOM_ALGORITHMS = "SHA1PRNG";

    /**
     * 密钥长度
     */
    private static final int SYMMETRIC_KEY_LENGTH = 128;

    /**
     * AES对称加密
     *
     * @param content  加密内容
     * @param password 加密密码
     *                 <p>
     * @return result 密文（Base64）
     */
    public String symmetricEncryprt(String content, String password) {

        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(CIPHER_TYPE);

            // 转换待加密内容为byte
            byte[] byteContent = content.getBytes(Constant.CHARSET_UTF8);

            // build the initialization vector.  This example is all zeros, but it
            // could be any value or generated using a random number generator.
            IvParameterSpec ivspec = new IvParameterSpec(initIv(CIPHER_TYPE));

            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password), ivspec);

            // 加密
            byte[] result = cipher.doFinal(byteContent);

            //通过Base64转码返回
            String strResultnew = new BASE64Encoder().encode(result);

            logger.info("加密完成，密文：[{}]", strResultnew);

            return strResultnew;
        } catch (Exception e) {

            logger.error("加密失败，错误信息：", e);
            return null;
        }
    }

    /**
     * AES解密操作
     *
     * @param content
     * @param password
     * @return
     */
    public String symmetricDecrypt(String content, String password) {

        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(CIPHER_TYPE);

            // 转换待加密内容为byte
            byte[] byteContent = new BASE64Decoder().decodeBuffer(content);

            // build the initialization vector.  This example is all zeros, but it
            // could be any value or generated using a random number generator.
            IvParameterSpec ivspec = new IvParameterSpec(initIv(CIPHER_TYPE));

            // 初始化为加密模式的密码器
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password), ivspec);

            // 加密
            byte[] result = cipher.doFinal(byteContent);

            //通过Base64转码返回
            String strResultnew = new String(result, Constant.CHARSET_UTF8);

            logger.info("解密完成，密文：[{}]", strResultnew);

            return strResultnew;
        } catch (Exception e) {
            logger.error("解密失败，错误信息：", e);
            return null;
        }
    }

    /**
     * 生成密钥对象
     *
     * @param password 密码
     * @return
     */
    private static SecretKeySpec getSecretKey(String password) {

        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator keyGenerator = null;

        try {
            keyGenerator = KeyGenerator.getInstance(Constant.ALGORITHM_AES);

            SecureRandom random = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHMS);
            random.setSeed(password.getBytes(Constant.CHARSET_UTF8));

            keyGenerator.init(SYMMETRIC_KEY_LENGTH, random);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();

            // 转换为AES专用密钥
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, Constant.ALGORITHM_AES);

            return secretKeySpec;
        } catch (NoSuchAlgorithmException ex) {
            logger.error("生成加密密钥失败，捕获到无此算法错误，具体信息：{}", ex);
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成随机密钥，返回Base64编码后的密钥
     */
    public String generateSymmetricKey() {

        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(Constant.ALGORITHM_AES);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 要生成多少位，只需要修改这里即可128, 192或256
        kg.init(SYMMETRIC_KEY_LENGTH);
        SecretKey sk = kg.generateKey();
        byte[] b = sk.getEncoded();
        String key = new BASE64Encoder().encode(b);
        logger.info("生成的密钥为：{}", key);
        return key;
    }

    /**
     * 初始向量的方法, 全部为0. 这里的写法适合于其它算法,针对AES算法的话,IV值一定是128位的(16字节).
     *
     * @param fullAlg
     * @return
     * @throws
     */
    private static byte[] initIv(String fullAlg) {

        try {
            Cipher cipher = Cipher.getInstance(fullAlg);
            int blockSize = cipher.getBlockSize();
            byte[] iv = new byte[blockSize];
            for (int i = 0; i < blockSize; ++i) {
                iv[i] = 0;
            }
            return iv;
        } catch (Exception e) {

            int blockSize = 16;
            byte[] iv = new byte[blockSize];
            for (int i = 0; i < blockSize; ++i) {
                iv[i] = 0;
            }
            return iv;
        }
    }
}
