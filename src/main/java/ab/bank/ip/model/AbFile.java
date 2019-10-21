package ab.bank.ip.model;

/**
 * Model class for Ab bank file.
 *
 * @author Reverside
 */
public class AbFile {

    private final String fileName;
    private final String fileType;
    private final String filePath;

    /**
     * <p>
     * Constructor for AbFile.
     * </p>
     *
     * @param fileName a {@link java.lang.String} object.
     * @param fileType a {@link java.lang.String} object.
     * @param filePath a {@link java.lang.String} object.
     */
    public AbFile(String fileName, String fileType, String filePath) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
    }

    /**
     * <p>
     * Getter for the field <code>fileName</code>.
     * </p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * <p>
     * Getter for the field <code>fileType</code>.
     * </p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * <p>
     * Getter for the field <code>filePath</code>.
     * </p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFilePath() {
        return filePath;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "AbFile [fileName=" + fileName + ", fileType=" + fileType + ", filePath=" + filePath + "]";
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
        result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
        result = prime * result + ((fileType == null) ? 0 : fileType.hashCode());
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbFile other = (AbFile) obj;
        if (fileName == null) {
            if (other.fileName != null)
                return false;
        } else if (!fileName.equals(other.fileName))
            return false;
        if (filePath == null) {
            if (other.filePath != null)
                return false;
        } else if (!filePath.equals(other.filePath))
            return false;
        if (fileType == null) {
            if (other.fileType != null)
                return false;
        } else if (!fileType.equals(other.fileType))
            return false;
        return true;
    }

}
