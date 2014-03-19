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

package it.latraccia.pkcs11.reader.cli.argument.converter;

import com.beust.jcommander.IStringConverter;
import it.latraccia.pkcs11.reader.util.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Converter for arguments representing a {@link java.io.File} path that may be located in a "resources" folder
 * or in an absolute path.
 *
 * @author Francesco Pontillo
 */
public class FileConverter implements IStringConverter<File> {
    @Override
    public File convert(String value) {
        try {
            return new File(FileUtil.getFileInAbsolutePathOrResources(value));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
