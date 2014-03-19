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

package it.latraccia.pkcs11.reader.test.cli;

import it.latraccia.pkcs11.reader.cli.ReadCLI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

/**
 * Generic PKCS11ReaderTest class for testing any kind of PKCS11 card and card reader, with any combination of parameters.
 * Simply extend this class and call the super constructor with the desired values as parameters.
 *
 * @author Francesco Pontillo
 */
public abstract class PKCS11ReaderTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    String driver;
    String password;
    String encryptionPassword;
    String expectedEncrypted;
    String expectedDecrypted;

    /**
     * Constructor to instantiate a test class.
     *
     * @param driver The driver path.
     * @param password The password for the card.
     * @param encryptionPassword The encryption password, can be empty or null if the content must not be encrypted.
     * @param expectedEncrypted The expected encrypted content, if any encryption password was provider.
     * @param expectedDecrypted The expected regular content, if no encryption password was provider.
     */
    protected PKCS11ReaderTest(String driver, String password, String encryptionPassword,
                               String expectedEncrypted, String expectedDecrypted) {
        this.driver = driver;
        this.password = password;
        this.encryptionPassword = encryptionPassword;
        this.expectedEncrypted = expectedEncrypted;
        this.expectedDecrypted = expectedDecrypted;
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void withEncryption() throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, KeyStoreException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        ReadCLI.main(new String[] {
                "-d=\"" + this.driver + "\"",
                "-p=\"" + this.password + "\"",
                "-e=\"" + this.encryptionPassword + "\""
        });
        assertEquals(expectedEncrypted.trim(), outContent.toString().trim());
    }

    @Test
    public void withoutEncryption() throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, KeyStoreException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        ReadCLI.main(new String[] {
                "-d=\"" + this.driver + "\"",
                "-p=\"" + this.password + "\""
        });
        assertEquals(expectedDecrypted.trim(), outContent.toString().trim());
    }

    @Test
    public void fail() {
        ReadCLI.main(new String[] {
                "-d=\"fakefake\"",
                "-p=\"fakethistoo\""
        });
        assertEquals("ERROR", outContent.toString().split("\\n")[0]);
    }
}
