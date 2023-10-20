package io.lazyegg.core.security;

import org.jasypt.util.text.BasicTextEncryptor;

public class ENCUtil {

    /**
     * 加密
     *
     * @param input
     * @param password
     * @return
     */
    public static String encrypt(String input, String password) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(password);
        return textEncryptor.encrypt(input);
    }

    /**
     * 解密
     *
     * @param input
     * @param password
     * @return
     */
    public static String decrypt(String input, String password) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(password);
        return textEncryptor.decrypt(input);
    }
}
