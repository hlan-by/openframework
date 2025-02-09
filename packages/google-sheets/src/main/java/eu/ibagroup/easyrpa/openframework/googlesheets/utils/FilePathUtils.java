package eu.ibagroup.easyrpa.openframework.googlesheets.utils;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class that provides useful methods for work with file path.
 */
public class FilePathUtils {

    /**
     * Gets {@link File} object for given file path.
     * <br>
     * Additionally performs normalization for given file path using {@link FilePathUtils#normalizeFilePath(String)}.
     *
     * @param filePath path on local file system or within resources folder of RPA process module.
     * @return related {@link File} object.
     */
    public static File getFile(String filePath) {
        File file = null;
        if (filePath != null && !filePath.trim().isEmpty()) {
            filePath = normalizeFilePath(filePath);
            try {
                file = new File(FilePathUtils.class.getResource(filePath.startsWith("/") ? filePath : "/" + filePath).toURI());
            } catch (Exception e) {
                file = new File(filePath);
            }
        }
        return file;
    }

    /**
     * Aligns file path separators according to system default and performs substitution of environment variables
     * like %USERPROFILE%.
     *
     * @param path file path to normalize.
     * @return normalized file path.
     */
    public static String normalizeFilePath(String path) {
        if (path.contains("%")) {
            path = FilenameUtils.separatorsToSystem(path);
            Matcher matcher = Pattern.compile("%\\w+%").matcher(path);
            while (matcher.find()) {
                String var = matcher.group();
                path = path.replaceAll(var, FilenameUtils.separatorsToSystem(System.getenv(var.replaceAll("%", ""))));
            }
        }
        return FilenameUtils.separatorsToSystem(path);
    }
}
