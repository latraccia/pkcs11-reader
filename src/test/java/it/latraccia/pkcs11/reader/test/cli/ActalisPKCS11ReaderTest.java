package it.latraccia.pkcs11.reader.test.cli;

import it.latraccia.pkcs11.reader.test.Const;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test class for Actalis cards.
 *
 * @author Francesco Pontillo
 */
@RunWith(JUnit4.class)
public class ActalisPKCS11ReaderTest extends PKCS11ReaderTest {

    private static final String driver = "drivers/bit4ipki.dll";
    private static final String password = "16061981";
    private static final String encryptionPassword = Const.ENCRYPTION_PASSWORD;

    private static final String expectedDecrypted = Const.Actalis.DECRYPTED;
    private static final String expectedEncrypted = Const.Actalis.ENCRYPTED;

    public ActalisPKCS11ReaderTest() {
        super(driver, password, encryptionPassword, expectedEncrypted, expectedDecrypted);
    }
}
