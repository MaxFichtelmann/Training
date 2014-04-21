package crypto;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

/**
 * 
 * @author Max Fichtelmann
 * 
 */
public class RSASample {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPair keyPair = generatePair();

        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        System.out.println(privateKey.getModulus());
        System.out.println(privateKey.getPrivateExponent());

        System.out.println("===");
        System.out.println(publicKey.getModulus());
        System.out.println(publicKey.getPublicExponent());

        showDemo(privateKey, publicKey);
    }

    private static void showDemo(RSAPrivateKey privateKey,
            RSAPublicKey publicKey) throws NoSuchAlgorithmException {
        if (!privateKey.getModulus().equals(publicKey.getModulus())) {
            throw new IllegalStateException(
                    "modulus of private key and public must be equal!");
        }

        BigInteger publicExponent = publicKey.getPublicExponent();
        BigInteger privateExponent = privateKey.getPrivateExponent();
        BigInteger modulus = publicKey.getModulus();

        // encryption
        System.out.println("===");
        showEncryptionDemo(publicExponent, privateExponent, modulus);

        // signature
        System.out.println("===");
        showSignatureDemo(publicExponent, privateExponent, modulus);
    }

    private static void showSignatureDemo(BigInteger publicExponent,
            BigInteger privateExponent, BigInteger modulus)
            throws NoSuchAlgorithmException {
        BigInteger signme = new BigInteger("1234567890");
        byte[] hash = hash(signme);
        System.out.println("document: " + signme);
        BigInteger signature = new BigInteger(hash).modPow(privateExponent,
                modulus);
        System.out.println("signature: " + signature);
        System.out.println(Arrays.equals(hash,
                signature.modPow(publicExponent, modulus).toByteArray()));
    }

    private static void showEncryptionDemo(BigInteger publicExponent,
            BigInteger privateExponent, BigInteger modulus) {
        BigInteger number = new BigInteger("1234567890");
        System.out.println("plain: " + number);

        BigInteger encrypted = number.modPow(publicExponent, modulus);
        System.out.println("encrypted: " + encrypted);

        BigInteger decrypted = encrypted.modPow(privateExponent, modulus);
        System.out.println("decrypted: " + decrypted);
    }

    // utility

    static byte[] hash(BigInteger data) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");

        return messageDigest.digest(data.toByteArray());
    }

    static KeyPair generatePair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        keyPairGenerator.initialize(512);
        return keyPairGenerator.generateKeyPair();
    }
}
