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
