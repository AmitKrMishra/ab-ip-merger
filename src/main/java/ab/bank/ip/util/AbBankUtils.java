package ab.bank.ip.util;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class use for file handling.
 *
 * @author Reverside
 */
public class AbBankUtils {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * This method check the file exist to specified location.
     *
     * @return boolean
     * @param file a {@link java.lang.String} object.
     */
    public static boolean isFileExist(String file) {
        logger.debug("Inside isFileExist");
        File f = new File(file);
        try {
            if (f.exists()) {
                return true;
            } else {
                System.out.println("File does not exist: " + f.getCanonicalPath());
                return false;
            }
        } catch (IOException e) {
            System.out.println("Invalid Filename: " + file);
            return false;
        }
    }

    /**
     * This method check the filename are same. If it founds it prompts the
     * message.
     *
     * @param file1 a {@link java.lang.String} object.
     * @param file2 a {@link java.lang.String} object.
     */
    public static void validateFilename(String file1, String file2) {
        if (file1.equals(file2)) {
            System.out.println("Entered same file :" + file1 + ", " + file2 + ". Please enter correct files.");
        }
    }

}
