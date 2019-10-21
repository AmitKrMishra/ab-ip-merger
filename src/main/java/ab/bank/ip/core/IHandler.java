package ab.bank.ip.core;

import java.io.IOException;

import ab.bank.ip.model.AbBankFile;

/**
 * Handles the process related to file handler.
 *
 * @author Reverside
 */
public interface IHandler {

	/**
	 * Return the handler name
	 *
	 * @return string
	 */
	public String getHandlerName();

	/**
	 * Method process the handler process.
	 *
	 * @param file a {@link ab.bank.ip.model.AbBankFile} object.
	 * @return boolean
	 * @throws java.io.IOException if any.
	 */
	public boolean process(AbBankFile file) throws IOException;
}
