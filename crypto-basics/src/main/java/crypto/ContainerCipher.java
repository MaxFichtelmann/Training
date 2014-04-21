package crypto;

import java.security.KeyPair;
import java.security.PrivateKey;

import javax.crypto.SecretKey;

public class ContainerCipher {

    private SymmetricCipher symmetricCipher;
    private AsymmetricCipher asymmetricCipher;

    public ContainerCipher(SymmetricCipher symmetricCipher,
            AsymmetricCipher asymmetricCipher) {
        this.symmetricCipher = symmetricCipher;
        this.asymmetricCipher = asymmetricCipher;
    }

    public Container encrypt(byte[] message, Certificate certificate) {
        SecretKey secretKey = symmetricCipher.generateKey(128);
        byte[] iv = symmetricCipher.generateIV();

        byte[] cipherText = symmetricCipher.encrypt(message, secretKey, iv);

        byte[] encryptedKey = asymmetricCipher.wrapKey(secretKey, certificate);

        return new Container(encryptedKey, certificate, cipherText, iv);
    }

    public byte[] decrypt(Container container, PrivateKey privateKey) {
        String algorithmName = symmetricCipher.getAlgorithmName();
        SecretKey secretKey = asymmetricCipher.unwrapKey(
                container.getEncryptedKey(), algorithmName, privateKey);

        byte[] plaintext = symmetricCipher.decrypt(
                container.getEncryptedData(), secretKey, container.getIv());

        return plaintext;
    }

    public static void main(String[] args) {
        SymmetricCipher aesCipher = new AESCipher(new AESKeyGeneratorFactory(),
                new AESCipherFactory());
        AsymmetricCipher rsaCipher = new RSACipher();
        ContainerCipher containerCipher = new ContainerCipher(aesCipher,
                rsaCipher);

        KeyPair keyPair = rsaCipher.generateKeyPair();
        CertificateAuthority ca = new CertificateAuthority("CA",
                rsaCipher.generateKeyPair());
        Certificate certificate = ca.issue("Mareike", keyPair.getPublic());

        String message = "Max quaelt mich ganz ganz ganz ganz ganz dolle!";
        Container container = containerCipher.encrypt(message.getBytes(),
                certificate);

        System.out.println(new String(container.getEncryptedData()));

        byte[] plaintext = containerCipher.decrypt(container,
                keyPair.getPrivate());

        System.out.println(new String(plaintext));
    }
}
