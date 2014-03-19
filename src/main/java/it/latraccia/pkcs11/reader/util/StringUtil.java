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
