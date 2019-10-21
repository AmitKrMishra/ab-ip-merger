package ab.bank.ip.core;

import java.io.IOException;
import java.util.HashMap;

import ab.bank.ip.model.AbFile;

/**
 * Process the file.
 *
 * @author Reverside
 */
public interface IFileReader {

	/**
	 * Process the files.
	 *
	 * @return hashMap, Object
	 * @param abFile a {@link ab.bank.ip.model.AbFile} object.
	 * @throws java.io.IOException if any.
	 */
	public HashMap<String, String> processFiles(AbFile abFile) throws IOException;

}
