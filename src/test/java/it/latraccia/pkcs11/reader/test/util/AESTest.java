package it.latraccia.pkcs11.reader.test.util;

import it.latraccia.pkcs11.reader.util.AESUtil;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

/**
 * Generic AESTest class for testing the encryption and decryption of several string combos.
 * Simply extend this class and call the super constructor with the desired values as parameters.
 *
 * @author Francesco Pontillo
 */
public abstract class AESTest {
    String encryptionPassword;
    String expectedEncrypted;
    String expectedDecrypted;

    protected AESTest(String encrypted, String decrypted, String encryptionPassword) {
        this.encryptionPassword = encryptionPassword;
        this.expectedEncrypted = encrypted;
        this.expectedDecrypted = decrypted;
    }

    @Test
    public void encrypt() throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        String encrypted = AESUtil.encryptString(expectedDecrypted, encryptionPassword);
        assertEquals(this.expectedEncrypted, encrypted);
    }

    @Test
    public void decrypt() throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        String decrypted = AESUtil.decryptString(expectedEncrypted, encryptionPassword);
        assertEquals(this.expectedDecrypted.trim(), decrypted.trim());
    }

    @Test(expected = IllegalArgumentException.class)
    public void longerPassword() throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        AESUtil.decryptString(expectedEncrypted, encryptionPassword + "a-salt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shorterPassword() throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        AESUtil.decryptString(expectedEncrypted, "short-password");
    }

    @Test
    public void wrongPassword() throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        String decrypted = null;
        try {
            decrypted = AESUtil.decryptString(expectedEncrypted, "a-wrong-password");
        } catch (javax.crypto.BadPaddingException exception) {
            // ignore, it's ok if we end up here because of a bad password
        }
        assertNotSame(expectedDecrypted, decrypted);
    }
}
