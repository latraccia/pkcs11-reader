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
