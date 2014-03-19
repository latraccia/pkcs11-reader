/*
 * PKCS#11-Reader, a utility to read PKCS#11 card information from any card and card reader.
 * Copyright (C) 2014 La Traccia http://www.latraccia.it/en/
 * Developed by Francesco Pontillo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see [http://www.gnu.org/licenses/].
 */

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
