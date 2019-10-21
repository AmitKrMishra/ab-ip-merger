package ab.bank.ip.core;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ab.bank.ip.model.AbBankFile;
import ab.bank.ip.model.AbFile;

/**
 *This handler class handle only text file.
 *
 * @author Reverside
 */
public class TextFileHandler implements IHandler {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private String handlerName;
    
    /**
     * <p>Constructor for TextFileHandler.</p>
     *
     * @param handlerName a {@link java.lang.String} object.
     */
    public TextFileHandler(String handlerName){
        this.handlerName = handlerName;
    }

    /** {@inheritDoc} */
    @Override
    public boolean process(AbBankFile abFile) throws IOException {
        logger.debug("Inside process");
        boolean MERGE_STATUS = false;
        System.out.println("Process IP file... by "+handlerName);
        List<AbFile> fileList = abFile.getFile();
        HashMap<String,HashMap<String, String>> fileHmap = new HashMap<>();
        ProcessTextFile ptf = new ProcessTextFile();
        for (AbFile file : fileList) {
            if(file.getFileType().equals("txt")){
                fileHmap.put(file.getFileName(), ptf.processFiles(file));
            }else{
                System.out.println("You have entered "+file.getFileType()+", only .txt file type not supported");
            }
        }
        MERGE_STATUS = ptf.mergeFileMap(fileHmap);
        return MERGE_STATUS;
    }

    /** {@inheritDoc} */
    @Override
    public String getHandlerName() {
        return handlerName;
    }
}
