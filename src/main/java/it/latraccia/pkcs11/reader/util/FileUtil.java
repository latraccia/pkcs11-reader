package it.latraccia.pkcs11.reader.util;


import java.io.*;
import java.net.URL;

public class FileUtil {

    /**
     * Search a file in the resources, and return its absolute path if it exists,
     * otherwise return a null {@link String}.
     *
     * @param fileName The name of the file in the resources
     * @return The path {@link String} of the file, if any, or null
     */
    public static String getFileInResources(String fileName) {
        URL fileURL = FileUtil.class.getClassLoader().getResource(fileName);
        String filePath = null;
        if (fileURL != null) {
            filePath = fileURL.getFile();
        }
        return filePath;
    }

    /**
     * Search for a file in an absolute path, and always returns a {@link String},
     * even if the file does not exist.
     *
     * @param fileName The file absolute path
     * @return The path {@link String} of the file
     */
    public static String getFileInAbsolutePath(String fileName) {
        File file = new File(fileName);
        return file.getAbsolutePath();
    }

    /**
     * Search for a given file path:
     *  - handling the fileName as an absolute path
     *  - fallback in resources, returns it if it exists
     *
     * @param fileName File name or path of a file in resources or an absolute path
     * @return  The file absolute path as a {@link String}, if it was found anywhere
     *          in the absolute path or in the resources
     */
    public static String getFileInAbsolutePathOrResources(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        String filePath = file.getAbsolutePath();
        if (!file.exists()) {
             filePath = getFileInResources(fileName);
        }
        return filePath;
    }
}
