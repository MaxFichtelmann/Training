package crypto;

import javax.crypto.SecretKey;

public interface SymmetricCipher {

    SecretKey generateKey(int size);

    byte[] generateIV();

    /**
     * @param message
     *            plain text
     * @param key
     *            symmetric key
     * @param iv
     *            initialization vector
     * @returns encrypted
     */
    byte[] encrypt(byte[] message, SecretKey key, byte[] iv);

    /**
     * @param cipherText
     *            encrypted
     * @param key
     *            symmetric key
     * @param iv
     *            initialization vector
     * @returns plain text
     */
    byte[] decrypt(byte[] cipherText, SecretKey key, byte[] iv);

    String getAlgorithmName();
}