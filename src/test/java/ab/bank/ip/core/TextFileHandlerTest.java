package ab.bank.ip.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ab.bank.ip.model.AbBankFile;
import ab.bank.ip.model.AbFile;

/**This class has test cases for TextFileHandler class.
 * @author Reverside
 *
 */
public class TextFileHandlerTest {

	@Test
	public void testProcess() throws Exception {
		TextFileHandler tfh = new TextFileHandler("Text Handler");
		AbBankFile abFile = new AbBankFile();
		List<AbFile> fileList = new ArrayList<>();
		AbFile fileFirst = new AbFile("file1.txt", "txt", "C:");
		fileList.add(fileFirst);
		AbFile fileSecond = new AbFile("file2.txt", "txt", "C:");
		fileList.add(fileSecond);
		abFile.setFile(fileList);
		tfh.process(abFile);
	}
	
	@Test
	public void testProcessNullHandler() throws Exception {
		TextFileHandler tfh = new TextFileHandler(null);
		AbBankFile abFile = new AbBankFile();
		List<AbFile> fileList = new ArrayList<>();
		AbFile fileFirst = new AbFile("file1.txt", "txt", "C:");
		fileList.add(fileFirst);
		AbFile fileSecond = new AbFile("file2.txt", "txt", "C:");
		fileList.add(fileSecond);
		abFile.setFile(fileList);
		tfh.process(abFile);
	}
	
}
