package xyz.chaofan.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.spec.KeySpec;

/**
 * 加解密
 */
@Slf4j
public class EncryptDecryptUtil {
    /**编码**/
    private static final String UNICODE_FORMAT = "UTF8";
    /**安全策略**/
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESEDE";

    private static final String CLOUD_ENCRYTIONKEY ="XZFnM2rwkJg8qsKmBYIQauHS";
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher encryptor;
    private Cipher decryptor;
    byte[] arrayBytes;
    private String myEncryptionScheme;
    SecretKey key;
    private final Object decryptLock = new Object();
    private final Object encryptorLock = new Object();

    /**
     * 初始化加解密对象
     */
    public static EncryptDecryptUtil getInstance(){
        return EncryptDecryptHolder.instance;
    }

    private static class EncryptDecryptHolder {
        private static final EncryptDecryptUtil instance = new EncryptDecryptUtil();
    }

    private EncryptDecryptUtil() {
        try {
            myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
            arrayBytes = CLOUD_ENCRYTIONKEY.getBytes(UNICODE_FORMAT);
            ks = new DESedeKeySpec(arrayBytes);
            skf = SecretKeyFactory.getInstance(myEncryptionScheme);
            encryptor = Cipher.getInstance(myEncryptionScheme);
            decryptor = Cipher.getInstance(myEncryptionScheme);
            key = skf.generateSecret(ks);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public String encrypt(String unencryptedString) {
        String encryptedString = null;
        synchronized (this.encryptorLock) {
            try {
                encryptor.init(Cipher.ENCRYPT_MODE, key);
                byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
                byte[] encryptedText = encryptor.doFinal(plainText);
                encryptedString = new String(Base64.encode(encryptedText));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return encryptedString;
    }

    public String decrypt(String encryptedString) {
        String decryptedText=null;
        byte[] encryptedText = null;
        synchronized (this.decryptLock) {
            try {
                decryptor.init(Cipher.DECRYPT_MODE, key);
                encryptedText = Base64.decode(encryptedString);
                byte[] plainText = decryptor.doFinal(encryptedText);
                decryptedText= new String(plainText);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return decryptedText;
    }


    public static void main(String[] args) {

    }
}
