package crypto;

import java.security.KeyPair;
import java.security.PrivateKey;

import javax.crypto.SecretKey;

public interface AsymmetricCipher {
    KeyPair generateKeyPair();

    byte[] wrapKey(SecretKey secretKey, Certificate certificate);

    SecretKey unwrapKey(byte[] encryptedKey, String algorithm,
            PrivateKey privateKey);
}
