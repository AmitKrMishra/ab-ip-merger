package ab.bank.ip.main;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *Ab file merge main class.
 *
 * @author Reverside
 */
public class FileMergeMain {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        logger.debug("Inside FileMergeMain main method");
        MergeFileAction mergeFileAction = new MergeFileAction();
        mergeFileAction.mergeAction();
    }
}
