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
}
