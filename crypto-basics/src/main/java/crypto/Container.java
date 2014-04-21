package crypto;

public class Container {
    private byte[] encryptedKey;
    private Certificate recipient;
    private byte[] encryptedData;
    private byte[] iv;

    public Container(byte[] encryptedKey, Certificate recipient,
            byte[] encryptedData, byte[] iv) {
        this.encryptedKey = encryptedKey;
        this.recipient = recipient;
        this.encryptedData = encryptedData;
        this.iv = iv;
    }

    public byte[] getEncryptedKey() {
        return encryptedKey;
    }

    public Certificate getRecipient() {
        return recipient;
    }

    public byte[] getEncryptedData() {
        return encryptedData;
    }

    public byte[] getIv() {
        return iv;
    }
}
