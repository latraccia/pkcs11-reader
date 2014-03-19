package it.latraccia.pkcs11.reader.test.cli;

import it.latraccia.pkcs11.reader.test.Const;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test class for InfoCert cards.
 *
 * @author Francesco Pontillo
 */
@RunWith(JUnit4.class)
public class InfoCertPKCS11ReaderTest extends PKCS11ReaderTest {

    private static final String driver = "drivers/bit4ipki.dll";
    private static final String password = "16061981";
    private static final String encryptionPassword = Const.ENCRYPTION_PASSWORD;

    private static final String expectedDecrypted = Const.InfoCert.DECRYPTED;
    private static final String expectedEncrypted = Const.InfoCert.ENCRYPTED;

    public InfoCertPKCS11ReaderTest() {
        super(driver, password, encryptionPassword, expectedEncrypted, expectedDecrypted);
    }
}
