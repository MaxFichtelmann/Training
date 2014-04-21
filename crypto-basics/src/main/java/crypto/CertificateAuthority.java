package crypto;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 * 
 * @author Max Fichtelmann
 * 
 */
public class CertificateAuthority {

    private KeyPair keyPair;
    private String name;

    public CertificateAuthority(String name, KeyPair keyPair) {
        this.name = name;
        this.keyPair = keyPair;
    }

    public PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    public Certificate issue(String subjectName, PublicKey publicKey) {
        try {
            byte[] signature = sign(subjectName, publicKey);

            return new Certificate(subjectName, publicKey, this.name, signature);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verify(Certificate certificate, PublicKey issuerKey) {
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(issuerKey);

            signature.update(certificate.getSubjectName().getBytes());
            signature.update(certificate.getPublicKey().getEncoded());

            return signature.verify(certificate.getSignature());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] sign(String subjectName, PublicKey publicKey)
            throws NoSuchAlgorithmException, InvalidKeyException,
            SignatureException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(keyPair.getPrivate());

        signature.update(subjectName.getBytes());
        signature.update(publicKey.getEncoded());

        return signature.sign();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        KeyPair caKeys = keyPairGenerator.generateKeyPair();

        CertificateAuthority ca = new CertificateAuthority("myCA", caKeys);

        KeyPair myKeys = keyPairGenerator.generateKeyPair();

        Certificate certificate = ca.issue("max", myKeys.getPublic());

        System.out.println(CertificateAuthority.verify(certificate,
                ca.getPublicKey()));
        System.out.println(certificate.getIssuerName());
        System.out.println(certificate.getSubjectName());
    }
}
