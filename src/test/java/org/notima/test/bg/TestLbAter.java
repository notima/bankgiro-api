package org.notima.test.bg;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.notima.bg.BgFile;
import org.notima.bg.LbFile;
import org.notima.util.NotimaUtil;


/*
 * JUnit test for reading BGMax file. Note, for this to work the sample files must be in the 
 * current class path.
 * The sample files are found in the directory test/samplefiles.
 */
public class TestLbAter extends TestCase {

	@Test
	public void testReadLbAter1() {
		
		URL url = ClassLoader.getSystemResource("samplefiles/GEUT_LBATER_Sample1.txt");
		if (url==null) {
			Assert.fail("Can't find GEUT_LBATER_Sample1.txt in class path. The files are normally located in test/samplefiles");
		}
		File reportFile = new File(url.getFile());
        BgFile inFile = new LbFile();
        Date fileDate;
        Charset cs = Charset.forName("Cp850"); // Default (other options Cp850)
        try {
        	inFile.readFromFile(reportFile, cs);
        	fileDate = inFile.getFileDate();
        	// Test file date
        	Assert.assertEquals("160222", NotimaUtil.getDateString(fileDate));
        	
        	// Test recipient accounts
        	Assert.assertEquals(true, ((LbFile)inFile).getBgSenders().contains("2407617"));
        	Assert.assertEquals(true, ((LbFile)inFile).getBgSenders().contains("54728027"));
        	
        } catch (Exception e) {
        	e.printStackTrace(System.err);
        }
		
	}
	
	
	@Test
	public void testReadLbAter2() {
		
		URL url = ClassLoader.getSystemResource("samplefiles/GEUT_LBATER_Sample2.txt");
		if (url==null) {
			Assert.fail("Can't find GEUT_LBATER_Sample2.txt in class path. The files are normally located in test/samplefiles");
		}
		File reportFile = new File(url.getFile());
        BgFile inFile = new LbFile();
        Date fileDate;
        Charset cs = Charset.forName("Cp850"); // Default (other options Cp850)
        try {
        	inFile.readFromFile(reportFile, cs);
        	fileDate = inFile.getFileDate();
        	// Test file date
        	Assert.assertEquals("160224", NotimaUtil.getDateString(fileDate));
        	
        	// Test recipient accounts
        	Assert.assertEquals(true, ((LbFile)inFile).getBgSenders().contains("2407617"));
        	
        } catch (Exception e) {
        	e.printStackTrace(System.err);
        }
		
	}
	
	@Test
	public void testSplitLb() {
		
		URL url = ClassLoader.getSystemResource("samplefiles/GEUT_LBATER_Sample1.txt");
		if (url==null) {
			Assert.fail("Can't find GEUT_LBATER_Sample1.txt in class path. The files are normally located in test/samplefiles");
		}
		
		// Create tmp path
		String tmpDirStr = System.getProperty("java.io.tmpdir");
		
		// Clear tmp from old tests
		File tmpDir = new File(tmpDirStr);
		
		String[] files = tmpDir.list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				if (name==null) return false;
				
				if (name.contains(LbFile.FILE_PREFIX) && name.endsWith(LbFile.FILE_SUFFIX)) {
					return true;
				}
				
				return false;
			}
			
		});

		File deleteMe;
		if (files!=null && files.length>0) {
			// Delete files
			for (int i=0; i<files.length; i++) {
				try {
					deleteMe = new File(tmpDir.getCanonicalPath() + File.separator + files[i]);
					deleteMe.delete();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		Charset cs = Charset.forName("Cp850");
		
		try {
		
			LbFile.splitToFiles(new File(url.getFile()), tmpDir, cs);
			
		} catch (Exception e) {
			Assert.fail(e.getMessage());
			e.printStackTrace(System.err);
		}
		
	}
	
}
