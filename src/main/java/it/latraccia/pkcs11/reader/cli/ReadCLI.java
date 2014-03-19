package it.latraccia.pkcs11.reader.cli;

import eu.europa.ec.markt.dss.signature.token.DSSPrivateKeyEntry;
import it.latraccia.pkcs11.reader.cli.argument.ReadingArgs;
import com.beust.jcommander.JCommander;
import eu.europa.ec.markt.dss.signature.token.Pkcs11SignatureToken;
import it.latraccia.pkcs11.reader.util.AESUtil;
import it.latraccia.pkcs11.reader.util.DSSPrivateKeyUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Command Line Interface for reading and encrypting information from a PKCS#11 card.
 *
 * @author Francesco Pontillo
 */
public class ReadCLI {

    public static void main(String[] args) {
        ReadingArgs readingArgs = new ReadingArgs();
        String returnThis = null;

        try {
            new JCommander(readingArgs, args);

            // Get the keys
            List<DSSPrivateKeyEntry> keys = getKeys(readingArgs);
            // Get a string representation
            String stringKeys = DSSPrivateKeyUtil.DSSKeysToString(keys);
            // Possibly encrypt the resulting string
            returnThis = cryptMeMaybe(stringKeys, readingArgs);
        } catch (Exception e) {
            returnThis = "ERROR\n" + e.getMessage();
        }

        // Print the resulting string
        System.out.println(returnThis);
    }

    /**
     * Get the {@link eu.europa.ec.markt.dss.signature.token.DSSPrivateKeyEntry}s from the PKCS#11 card.
     * @param readingArgs The {@link it.latraccia.pkcs11.reader.cli.argument.ReadingArgs} to get the card password from.
     * @return A list of found keys.
     * @throws KeyStoreException If the card could not be read.
     */
    private static List<DSSPrivateKeyEntry> getKeys(ReadingArgs readingArgs) throws KeyStoreException {
        Pkcs11SignatureToken token = new Pkcs11SignatureToken(
                readingArgs.getDriver().getAbsolutePath(), readingArgs.getPassword().toCharArray());
        List<DSSPrivateKeyEntry> keys = token.getKeys();
        return keys;
    }

    /**
     * Encrypts the input {@link java.lang.String} (or not) according to the presence of the encryption password
     * in the {@link it.latraccia.pkcs11.reader.cli.argument.ReadingArgs}.
     *
     * @param cryptMe The {@link java.lang.String} that will possibly be encrypted.
     * @param readingArgs The {@link it.latraccia.pkcs11.reader.cli.argument.ReadingArgs} to get the encryption
     *                    password from.
     * @return The {@link java.lang.String} to be printed.
     *
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     */
    private static String cryptMeMaybe(String cryptMe, ReadingArgs readingArgs) throws NoSuchPaddingException,
                                                                                       InvalidKeyException,
                                                                                       NoSuchAlgorithmException,
                                                                                       IllegalBlockSizeException,
                                                                                       BadPaddingException,
                                                                                       InvalidAlgorithmParameterException {
        if (readingArgs.getEncryptionPassword() != null) {
            return AESUtil.encryptString(cryptMe, readingArgs.getEncryptionPassword());
        }
        return cryptMe;
    }
}