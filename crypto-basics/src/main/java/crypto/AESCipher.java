package crypto;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class AESCipher implements SymmetricCipher {
    private final AESKeyGeneratorFactory generatorFactory;
    private final AESCipherFactory cipherFactory;

    public AESCipher(AESKeyGeneratorFactory generatorFactory,
            AESCipherFactory cipherFactory) {
        this.generatorFactory = generatorFactory;
        this.cipherFactory = cipherFactory;
    }

    @Override
    public SecretKey generateKey(int size) {
        KeyGenerator keyGenerator = generatorFactory.create();
        keyGenerator.init(size);

        return keyGenerator.generateKey();
    }

    @Override
    public byte[] generateIV() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] iv = secureRandom.generateSeed(16);

        return iv;
    }

    @Override
    public byte[] encrypt(byte[] message, SecretKey key, byte[] iv) {
        try {
            Cipher cipher = cipherFactory.create();
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));

            return cipher.doFinal(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] decrypt(byte[] cipherText, SecretKey key, byte[] iv) {
        try {
            Cipher cipher = cipherFactory.create();
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));

            return cipher.doFinal(cipherText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getAlgorithmName() {
        return "AES";
    }

    public static void main(String[] args) {
        SymmetricCipher cipher = new AESCipher(new AESKeyGeneratorFactory(),
                new AESCipherFactory());
        SecretKey secretKey = cipher.generateKey(128);
        byte[] message = "Ich liebe meinen lieben Mann Max!".getBytes();
        SecureRandom secureRandom = new SecureRandom();
        byte[] iv = secureRandom.generateSeed(16);

        byte[] cipherText = cipher.encrypt(message, secretKey, iv);
        System.out.println(new String(cipherText));

        byte[] decryptedMessage = cipher.decrypt(cipherText, secretKey, iv);
        System.out.println(new String(decryptedMessage));
    }

}
