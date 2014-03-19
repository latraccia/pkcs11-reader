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

package it.latraccia.pkcs11.reader.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Date: 18/03/14
 * Time: 13.07
 *
 * @author Francesco Pontillo
 */
public class AESUtil {
    public static String encryptString(String clearText, String password) throws NoSuchPaddingException,
                                                                                 NoSuchAlgorithmException,
                                                                                 InvalidAlgorithmParameterException,
                                                                                 InvalidKeyException,
                                                                                 BadPaddingException,
                                                                                 IllegalBlockSizeException {
        String encrypted;
        byte[] key = password.getBytes();
        // Check the length of the password
        if (key.length != 16) {
            throw new IllegalArgumentException("Invalid password length.");
        }
        byte[] value = clearText.getBytes();

        // Encrypt with AES/CBC/PKCS5Padding
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16]));
        byte[] encryptedBytes = cipher.doFinal(value);

        // Encode the bytes in base64
        encrypted = Base64.encodeBase64String(encryptedBytes);
        return encrypted;
    }

    public static String decryptString(String encryptedText, String password) throws NoSuchPaddingException,
                                                                                     NoSuchAlgorithmException,
                                                                                     InvalidAlgorithmParameterException,
                                                                                     InvalidKeyException,
                                                                                     BadPaddingException,
                                                                                     IllegalBlockSizeException {
        String decrypted = null;
        byte[] key = password.getBytes();
        if (key.length != 16) {
            throw new IllegalArgumentException("Invalid key size.");
        }
        byte[] value = Base64.decodeBase64(encryptedText);

        // Decrypt with AES/CBC/PKCS5Padding
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16]));
        byte[] original = cipher.doFinal(value);
        decrypted = new String(original);

        return decrypted;
    }
}
