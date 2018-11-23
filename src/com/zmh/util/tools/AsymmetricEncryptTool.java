package com.zmh.util.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 非对称加解密/签名验签工具
 * <p>
 *
 * @author zengminghua
 * @date 20180911
 */
public class AsymmetricEncryptTool {

    /**
     * sl4j日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(AsymmetricEncryptTool.class);

    /**
     * 默认报文编码
     */
    private static final String DEFAULT_CHARSET = "utf-8";

    /**
     * 加密算法
     */
    public static final String KEY_ALGORITHM = "AES";

    /**
     * 安全随机数算法
     */
    public static final String SECURE_RANDOM_ALGORITHMS = "SHA1PRNG";

    /**
     * 对称加密
     *
     * @param content   加密内容
     * @param password  加密密码
     * @param algorithm 加密算法
     *                  <p>
     * @return result 密文（Base64）
     */
    public String symmetricEncryprt(String content, String password, String algorithm) {

        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(algorithm);

            // 转换待加密内容为byte
            byte[] byteContent = content.getBytes(DEFAULT_CHARSET);

            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password, "AES"));

            // 加密
            byte[] result = cipher.doFinal(byteContent);

            //通过Base64转码返回
            String strResultnew = new String(new BASE64Encoder().encode(result));

            logger.info("加密完成，密文：[{}]", strResultnew);

            return strResultnew;
        } catch (Exception e) {

            return null;
        }
    }

    /**
     * 解密操作
     *
     * @param content
     * @param password
     * @return
     */
    public String symmetricDecrypt(String content, String password, String algorithm) {

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(algorithm);

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password, "AES"));

            byte[] contentByte = new BASE64Decoder().decodeBuffer(content);

            //执行操作
            byte[] result = cipher.doFinal(contentByte);

            String strResult = new String(result, DEFAULT_CHARSET);

            logger.info("解密完成，结果为：[{}]", strResult);

            return strResult;
        } catch (Exception ex) {
            logger.error("加密失败，错误信息：{}", ex);
            return null;
        }
    }

    /**
     * 生成密钥对象
     *
     * @param password     密码
     * @param keyAlgorithm 密码算法
     * @return
     */
    private static SecretKeySpec getSecretKey(String password, String keyAlgorithm) {

        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator keyGenerator = null;

        try {
            keyGenerator = KeyGenerator.getInstance(keyAlgorithm);
            SecureRandom random = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHMS);
            random.setSeed(password.getBytes(DEFAULT_CHARSET));
            keyGenerator.init(128, random);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();

            // 转换为AES专用密钥
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, KEY_ALGORITHM);

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
}
