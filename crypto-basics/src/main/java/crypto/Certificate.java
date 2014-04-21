package crypto;

import java.security.PublicKey;

public class Certificate {
    private String subjectName;
    private String issuerName;
    private PublicKey publicKey;
    private byte[] signature;

    public Certificate(String subjectName, PublicKey publicKey,
            String issuerName, byte[] signature) {
        this.subjectName = subjectName;
        this.issuerName = issuerName;
        this.publicKey = publicKey;
        this.signature = signature;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public byte[] getSignature() {
        return signature;
    }
}
