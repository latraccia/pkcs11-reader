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
