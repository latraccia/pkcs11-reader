package it.latraccia.pkcs11.reader.test.util;

import it.latraccia.pkcs11.reader.test.Const;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test class for Actalis string encryption/decryption.
 *
 * @author Francesco Pontillo
 */
@RunWith(JUnit4.class)
@Category(value = AESTest.class)
public class ActalisAESTest extends AESTest {
    public ActalisAESTest() {
        super(Const.Actalis.ENCRYPTED, Const.Actalis.DECRYPTED, Const.ENCRYPTION_PASSWORD);
    }
}
