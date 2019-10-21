package ab.bank.ip.main;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ab.bank.ip.core.IHandler;
import ab.bank.ip.core.TextFileHandler;
import ab.bank.ip.model.AbBankFile;
import ab.bank.ip.model.AbFile;
import ab.bank.ip.util.AbBankUtils;

/**
 * This class work as controller class from here all merge file process is
 * begins.
 *
 * @author Reverside
 */
public class MergeFileAction {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * mergeAction takes file name as input from console do validation and process
     * to merge.
     */
    public void mergeAction() {
        logger.debug("Inside mergeAction");
        String file1 = null;
        String file2 = null;
        while (true) {
            boolean MERGE_STATUS = false;
            boolean isFileValid = false;
            Scanner sc = new Scanner(System.in);
            try {
                System.out.println("Enter the file name(.txt):");
                file1 = sc.next();
                isFileValid = AbBankUtils.isFileExist(file1);
                System.out.println("Enter the file name(.txt):");
                file2 = sc.next();
                isFileValid = AbBankUtils.isFileExist(file2);
                AbBankUtils.validateFilename(file1, file2);
                if (isFileValid) {
                	MERGE_STATUS = processFileContent(file1, file2);
                    isFileValid = false;
                }
                moreOption(MERGE_STATUS, sc);
            } catch (Exception e) {
                e.printStackTrace();
                logger.debug("Exception:{}",e.getMessage());
            }
        }
    }

    /**
     * This method takes input form console to continue and exit.
     *
     * @param MERGE_STATUS a boolean.
     * @param sc           a {@link java.util.Scanner} object.
     */
    public static void moreOption(boolean MERGE_STATUS, Scanner sc) {
        logger.debug("Inside moreOption");
        if (MERGE_STATUS) {
            while (true) {
                System.out.println("Do you want to contine(Y/N):");
                String toContinue = sc.next();
                if (toContinue.equalsIgnoreCase("Y")) {
                	break;
                } 
                if (!toContinue.equalsIgnoreCase("Y") || !toContinue.equalsIgnoreCase("N")) {
                    System.out.println("Some invalid charater is entered, Please enter in (Y/N):");
                    toContinue = sc.next();
                }
                if (toContinue.equalsIgnoreCase("N")) {
                	System.out.println("Exit from the file merge system");
                	System.exit(0);
                } 
            }
        } else {
        	System.out.println("Oops, Please Try again!!");
        }
    }
    
    
	private boolean processFileContent(String file1, String file2) throws IOException {
		/*
		 * Used hear ChainofResponsibility design pattern so that if in future if any
		 * new type of file comes that be supported.
		 */
		IHandler textHandler = new TextFileHandler("Text Handler");
		AbBankFile abFile = new AbBankFile();
		List<AbFile> fileList = new ArrayList<>();
		AbFile fileFirst = new AbFile(file1, "txt", "C:");
		fileList.add(fileFirst);
		AbFile fileSecond = new AbFile(file2, "txt", "C:");
		fileList.add(fileSecond);
		abFile.setFile(fileList);
		return textHandler.process(abFile);
	}

}
