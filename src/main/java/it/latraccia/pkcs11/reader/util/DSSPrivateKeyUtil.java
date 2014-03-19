package it.latraccia.pkcs11.reader.util;

import eu.europa.ec.markt.dss.signature.token.DSSPrivateKeyEntry;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimePrinter;

import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Date: 18/03/14
 * Time: 12.32
 *
 * @author Francesco Pontillo
 */
public class DSSPrivateKeyUtil {

    /**
     * Get the subject DN {@link String} from a certificate.
     * @param cert A {@link java.security.cert.X509Certificate} to read the subject DN from
     * @return The {@link String} representing the subject for the {@link java.security.cert.X509Certificate}
     */
    public static String getSubjectDN(X509Certificate cert) {
        String subjectDN = cert.getSubjectDN().getName();
        int dnStartIndex = subjectDN.indexOf("CN=") + 3;
        if (dnStartIndex > 0 && subjectDN.indexOf(",", dnStartIndex) > 0) {
            subjectDN = subjectDN.substring(dnStartIndex, subjectDN.indexOf(",", dnStartIndex)) + " (SN:"
                    + cert.getSerialNumber() + ")";
        }
        return subjectDN;
    }

    /**
     * Create a {@link java.lang.String} representation for a {@link eu.europa.ec.markt.dss.signature.token.DSSPrivateKeyEntry}.
     *
     * @param key the input {@link eu.europa.ec.markt.dss.signature.token.DSSPrivateKeyEntry}.
     * @return A {@link java.lang.String} representation.
     */
    public static StringBuilder DSSKeyToString(DSSPrivateKeyEntry key) {
        StringBuilder keyBuilder = new StringBuilder();
        X509Certificate certificate = key.getCertificate();
        // The serial number
        keyBuilder.append("SERIAL_NUMBER=");
        keyBuilder.append(certificate.getSerialNumber().toString());
        keyBuilder.append("\n");
        // The validity dates
        keyBuilder.append("VALIDITY_FROM=");
        keyBuilder.append(new DateTime(certificate.getNotBefore()).toString());
        keyBuilder.append("\n");
        keyBuilder.append("VALIDITY_TO=");
        keyBuilder.append(new DateTime(certificate.getNotAfter()).toString());
        keyBuilder.append("\n");

        // Get the issuer data and prepend them with "ISSUER_"
        String prependIssuer = "ISSUER_";
        String issuer = certificate.getIssuerDN().getName();
        String[] issuerData = issuer.split(", ");
        for (String data : issuerData) {
            keyBuilder.append(prependIssuer + data);
            keyBuilder.append("\n");
        }

        // Get the subject data and prepend them with "SUBJECT_"
        String prependSubject = "SUBJECT_";
        String subject = certificate.getSubjectDN().getName();
        String[] subjectData = subject.split(", ");
        for (String data : subjectData) {
            keyBuilder.append(prependSubject + data);
            keyBuilder.append("\n");
        }

        return keyBuilder;
    }

    /**
     * Returns the {@link java.lang.String} representation of a {@link java.util.List} of {@link eu.europa.ec.markt.dss.signature.token.DSSPrivateKeyEntry}s.
     * @param keys The list of keys that will be serialized.
     * @return The resulting serialization.
     */
    public static String DSSKeysToString(List<DSSPrivateKeyEntry> keys) {
        StringBuilder serializedStringBuilder = new StringBuilder();
        for (DSSPrivateKeyEntry key : keys) {
            serializedStringBuilder.append(DSSPrivateKeyUtil.DSSKeyToString(key));
            serializedStringBuilder.append("\n");
        }
        return serializedStringBuilder.toString();
    }
}
