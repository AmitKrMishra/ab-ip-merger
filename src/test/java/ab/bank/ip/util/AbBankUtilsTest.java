package ab.bank.ip.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ab.bank.ip.model.AbFile;

/**
 * Utility class use for file handling.
 * 
 * @author Reverside
 *
 */
public class AbBankUtilsTest {

	private AbFile firstFile;
	private AbFile secondFile;

	@Before
	public void setUp() throws Exception {
		firstFile = new AbFile("file1.txt", "txt", "C:");
		secondFile = new AbFile("FileNFound.txt", "txt", "C:");
	}
	
	@Test
	public void isFileExistTest() throws Exception {
		boolean isFileValid = false;
		isFileValid = AbBankUtils.isFileExist(firstFile.getFileName());
		assertTrue(isFileValid);
	}
	
	@Test
	public void isFileExistInvalidFileNameTest() throws Exception {
		boolean isFileValid = false;
		isFileValid = AbBankUtils.isFileExist(secondFile.getFileName());
		assertFalse(isFileValid);
	}
	
}