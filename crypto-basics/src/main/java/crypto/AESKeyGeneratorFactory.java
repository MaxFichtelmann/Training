package crypto;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;

public class AESKeyGeneratorFactory {
    public KeyGenerator create() {
        try {
            return KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
