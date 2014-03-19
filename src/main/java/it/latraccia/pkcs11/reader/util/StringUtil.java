package it.latraccia.pkcs11.reader.util;

/**
 * Date: 18/03/14
 * Time: 12.32
 *
 * @author Francesco Pontillo
 */
public class StringUtil {

    /**
     * Check if a {@link String} is null or empty.
     * @param string The {@link String} to check
     * @return true if the {@link String} is null or empty, false otherwise
     */
    public static boolean isNullOrEmpty(String string) {
        return (string == null || string.isEmpty());
    }
}
