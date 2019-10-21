package ab.bank.ip.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import ab.bank.ip.model.AbFile;

/**
 * This class has test cases for ProcessTextFile class.
 * 
 * @author Reverside
 *
 */
public class ProcessTextFileTest {

	private AbFile firstFile;
	private AbFile secondFile;
	private AbFile thirdFile;

	@Before
	public void setUp() throws Exception {
		firstFile = new AbFile("file1.txt", "txt", "C:");
		secondFile = new AbFile("file2.txt", "txt", "C:");
		thirdFile = new AbFile("file3.txt", "txt", "C:");
	}

	@Test
	public void testProcessFile() throws Exception {
		ProcessTextFile ptf = new ProcessTextFile();
		HashMap<String, String> pfHMap = ptf.processFiles(firstFile);
		assertNotNull(pfHMap);
		assertEquals(pfHMap.size(), 2);
	}

	@Test
	public void mergeFileMapTest() throws Exception {
		ProcessTextFile ptf = new ProcessTextFile();
		HashMap<String, HashMap<String, String>> fileHmap = new HashMap<>();
		HashMap<String, String> pfHMapFile1 = ptf.processFiles(firstFile);
		HashMap<String, String> pfHMapFile2 = ptf.processFiles(secondFile);
		fileHmap.put(firstFile.getFileName(), pfHMapFile1);
		fileHmap.put(secondFile.getFileName(), pfHMapFile2);
		assertNotNull(pfHMapFile1);
		assertNotNull(pfHMapFile2);
		assertEquals(pfHMapFile1.size(), 2);
		assertEquals(pfHMapFile1.size(), 2);
	}

	@Test
	public void mergeInValidFileContentTest() throws Exception {
		ProcessTextFile ptf = new ProcessTextFile();
		HashMap<String, String> pfHMapFile1 = ptf.processFiles(secondFile);
		HashMap<String, String> pfHMapFile2 = ptf.processFiles(thirdFile);
		assertNotNull(pfHMapFile1);
		assertNull(pfHMapFile2);
	}

}
