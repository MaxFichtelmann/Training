package crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class RSACipher implements AsymmetricCipher {
    @Override
    public KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator
                    .getInstance("RSA");
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] wrapKey(SecretKey secretKey, Certificate certificate) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.WRAP_MODE, certificate.getPublicKey());

            return cipher.wrap(secretKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SecretKey unwrapKey(byte[] encryptedKey, String algorithm,
            PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.UNWRAP_MODE, privateKey);

            return (SecretKey) cipher.unwrap(encryptedKey, algorithm,
                    Cipher.SECRET_KEY);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
