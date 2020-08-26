package com.deway.blog.tool;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * Base64编码规则：将二进制数据按字节分隔开，以3个8位字节为一组（24位），划分为4个字节（24 / 4 = 6，
 * 每个6位数据高两位补零成8位，也就是一个字节，末尾不足三个字节，则补位 n / 3 个零字节），再按照Base64
 * 编码表(实际有65个字符，‘=’为填充字符)进行编码，最后的零补位根据剩余的字节数编码成'='，Base64编码都
 * 是ASCII表中的可打印字符，因此数据会膨胀:
 * <em>BYTE_SIZE  = (BYTE_SIZE / 3) * 4 + (3 - (BYTE_SIZE % 3) == 0 ? 0 : 4)</em>.
 *
 * @author Deway
 */
public abstract class Pbkdf2Util {

    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    public static final int HASH_BIT_SIZE = 64 * 8;

    public static final int PBKDF2_ITERATIONS = 1024;

    public static String encrypt(String pwd, byte[] salt) throws InvalidKeySpecException {
        var pbeKeySpec = new PBEKeySpec(pwd.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BIT_SIZE);
        byte[] encoded;
        try {
            encoded = SecretKeyFactory
                    .getInstance(PBKDF2_ALGORITHM)
                    .generateSecret(pbeKeySpec)
                    .getEncoded();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(encoded);
    }

}