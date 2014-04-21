package crypto;

import javax.crypto.Cipher;

public class AESCipherFactory {
    public Cipher create() {
        try {
            return Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
