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

package it.latraccia.pkcs11.reader.cli.argument;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.converters.FileConverter;

import java.io.File;

/**
 * Mapping class containing all the definitions for the {@link it.latraccia.pkcs11.reader.cli.ReadCLI} arguments.
 * Every argument hereby contained will be automaticall mapepd by {@link com.beust.jcommander.JCommander}.
 *
 * @author Francesco Pontillo
 */
@Parameters(separators = "=")
public class ReadingArgs {

    @Parameter(
            names = {"-d", "--driver"},
            required = true,
            converter = it.latraccia.pkcs11.reader.cli.argument.converter.FileConverter.class,
            description = "The PKCS#11 card driver path.")
    private File driver;

    @Parameter(
            names = {"-p", "--password"},
            description = "Password for the PKCS#11 card.")
    private String password;

    @Parameter(
            names = {"-e", "--encrypt"},
            description = "Password for the data encryption.")
    private String encryptionPassword;

    @Parameter(names = {"-log", "--log"},
               description = "Path for the output log file")
    private String log;

    public File getDriver() {
        return driver;
    }

    public void setDriver(File driver) {
        this.driver = driver;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptionPassword() {
        return encryptionPassword;
    }

    public void setEncryptionPassword(String encryptionPassword) {
        this.encryptionPassword = encryptionPassword;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
