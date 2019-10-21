package ab.bank.ip.model;

import java.util.List;

/**
 *Model class for Ab bank file.
 *
 * @author Reverside
 */
public class AbBankFile {

    private List<AbFile> file;

    /**
     * <p>Getter for the field <code>file</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<AbFile> getFile() {
        return file;
    }

    /**
     * <p>Setter for the field <code>file</code>.</p>
     *
     * @param file a {@link java.util.List} object.
     */
    public void setFile(List<AbFile> file) {
        this.file = file;
    }
    
}
