package testsmellproject;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class TestSmellDetector {

	HashMap<String,String> smellInfo=new HashMap<String,String>();
	public TestSmellDetector() {
		
	}
	public HashMap<String,String> detectSmell(ArrayList<String> pathlist,ArrayList<String> classNameList) throws IOException{
		
		int classindex=0;
		for(String path:pathlist) {
			
			FileReader fr=new FileReader(new File(path));
			BufferedReader br=new BufferedReader(fr);
			String s=null;
			String str="";
			boolean multilineComment=false;
			int line=0;
			while((s=br.readLine())!=null) {
				line++;
			
				multilineComment=checkMultilineComment(s,multilineComment);
				
					System.out.println(s);
					String absolutePath = "[a-zA-Z]:[\\\\|//|////]+.*[a-zA-Z0-9]+(.png\"|.jpg\"|.txt\"|.xml\"|.csv\"|.jpeg\"|.gif\"|.pdf\"|.doc\"|.docx\"|.html\"|.ppt\"|.pptx\"|.XLS\"|.mp3\"|.mp4\"|.3gpa\"|.tar\"|.zip\"|.jar\"|.svg\")";
					String relativePath="[a-zA-Z0-9]+(.png\"|.jpg\"|.txt\"|.xml\"|.csv\"|.jpeg\"|.gif\"|.pdf\"|.doc\"|.docx\"|.html\"|.ppt\"|.pptx\"|.XLS\"|.mp3\"|.mp4\"|.3gpa\"|.tar\"|.zip\"|.jar\"|.svg\")";
					String path1="[a-zA-Z]:[\\\\|//|////]";
					String path2="(.git|.txt)";
				    Pattern r = Pattern.compile(absolutePath);
				    Pattern r2=Pattern.compile(relativePath);
				    Matcher m = r.matcher(s);
				    Matcher m2 = r2.matcher(s);
				    if (m.find( )||m2.find()) {
				        
				        	 String linenumber="";
				    	     if(smellInfo.get(classNameList.get(classindex))!=null) linenumber=smellInfo.get(classNameList.get(classindex))+","+line;
				    	     else linenumber=line+"";
				        	 smellInfo.put(classNameList.get(classindex),linenumber);
				        	 if(m.find( )) System.out.println("Found value: " + m.group(0) );
				        	 else if(m2.find( )) System.out.println("Found value: " + m2.group(0) );
				        	 
					         
				         
				    }else if(s.contains("new File(")|s.contains("new Path(")) {		    	
				    	 String linenumber="";
			    	     if(smellInfo.get(classNameList.get(classindex))!=null) linenumber=smellInfo.get(classNameList.get(classindex))+","+line;
			    	     else linenumber=line+"";
			        	 smellInfo.put(classNameList.get(classindex),linenumber);
			        	 
				         
				    }
				
				
				
			}
			classindex++;
			br.close();
			fr.close();
		}
		
		return smellInfo;
  }
  private boolean checkMultilineComment(String s,boolean multilineComment) {
		
		if(s.contains("/*")||multilineComment) {
			
			return true;
		}
		else if(s.contains("*/")) {
			
			return false;
		}
		
		return false;
	}

	public boolean checkSingleLineComment(String s) {
		
		String str=s.replace(" ","").replace("\t","");
		if(str.contains(";//")||str.indexOf("//")==0) {
		
			return true;
		}
		else {
			
			return false;
			
		}
		
		
	}
}
