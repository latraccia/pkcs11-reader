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
