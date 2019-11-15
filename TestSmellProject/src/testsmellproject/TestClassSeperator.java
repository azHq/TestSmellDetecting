package testsmellproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestClassSeperator {
	
	private ArrayList<String> allTestFilePath=new ArrayList<String>();
	private ArrayList<String> allTestFileName=new ArrayList<String>();
	public TestClassSeperator(File file) throws IOException {
		
		getFiles(file);
	}
	public void getFiles(File f) throws IOException{
        File files[];
        if(f.isFile()&&f.getAbsolutePath().endsWith(".java")) 
        {
        	if(isTestClass(f.getAbsolutePath())) {
        		allTestFilePath.add(f.getAbsolutePath());
        		allTestFileName.add(f.getName());
        	}
            
		}
        else if(f.isDirectory()){
            files = f.listFiles();
            if(files!=null) {
            	for (int i = 0; i < files.length; i++) {
                    getFiles(files[i]);
                }
            }
            
        }
    }
	
	public ArrayList<String> getAllTestFilePath(){
		
		return allTestFilePath;
	}
	public ArrayList<String> getAllTestFileName(){
		
		return allTestFileName;
	}
	
	public boolean isTestClass(String path) throws IOException {
		

		FileReader fr=new FileReader(new File(path));
		BufferedReader br=new BufferedReader(fr);
		String s=null;
		String testClassSign="(org.junit.|import static org.junit.|import org.junit.|import junit.|@Test|@Before|@After|@BeforeClass|@AfterClass|@Ignore or @Ignore(\"Why disabled\")|@Test (expected = Exception.class)|@Test(timeout=100)|assertTrue|assertNull|assertNotNull|assertArrayEquals|assertSame|assertNotSame)";
		Pattern r = Pattern.compile(testClassSign);
		while((s=br.readLine())!=null) {
			Matcher m = r.matcher(s);
			if(m.find()) {
				
				return true;
			}
			
		}
		br.close();
		fr.close();
		
		return false;
	}
	
}
