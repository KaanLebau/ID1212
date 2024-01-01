package dev.kadan.kthForum.config;

import jakarta.persistence.AttributeConverter;
import org.hibernate.metamodel.internal.AttributeContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SerializationUtils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Configuration
public class AESEncryptor implements AttributeConverter<Object, String> {
@Value("${aes.encryption.key}")
    private String encryptionKey;
    private  final String encryptionChiper = "AES";

    private Key key;
    private Cipher cipher;

    private  Key getKey() {
        System.out.println("KEY LENGTH = " + encryptionKey.length());
        if(key == null)
            if (encryptionKey.length() != 16 && encryptionKey.length() != 24 && encryptionKey.length() != 32)
                throw new IllegalArgumentException("Invalid AES key length");
        key = new SecretKeySpec(encryptionKey.getBytes(), encryptionChiper);
        return key;
    }

    public Cipher getCipher() throws GeneralSecurityException {
        if (cipher == null)
            cipher = Cipher.getInstance(encryptionChiper);
        return cipher;
    }

    private void initCipher(int encryptMode) throws GeneralSecurityException {
        getCipher().init(encryptMode, getKey());

    }
    @Override
    public String convertToDatabaseColumn(Object o) {
        if(o == null)
            return null;
        try {
            initCipher(Cipher.ENCRYPT_MODE);
            byte[] bytes = SerializationUtils.serialize(o);
            return Base64.getEncoder().encodeToString(getCipher().doFinal(bytes));
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object convertToEntityAttribute(String s) {
        if(s == null)
            return null;
        try {
            initCipher(Cipher.DECRYPT_MODE);
            byte[] bytes = getCipher().doFinal(Base64.getDecoder().decode(s));
            return SerializationUtils.deserialize(bytes);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }
}
