package testsmellproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class ProjectController {
	HashMap<String,String> smellInfo=new HashMap<String,String>();
	private JFrame  frame;
	private JPanel panel;
	JLabel label;
	JTextArea textArea;
	JTextPane pane,smelldataText;
	Document doc ;
	JList<String> list1;
	DefaultListModel<String> classList;
	TestClassSeperator testClassSeperator;
	ArrayList<String> classNameList=new ArrayList<String>();
	ArrayList<String> classPathList=new ArrayList<String>();
	public ProjectController() throws IOException, BadLocationException {
		
		InitGUI();
			
	}
	
	private void readFile(String path) throws IOException, BadLocationException {
		doc.remove(0, doc.getLength());
		FileReader fr=new FileReader(new File(path));
		BufferedReader br=new BufferedReader(fr);
		String s=null;
		String str="";
		boolean multilineComment=false;
		int linenumber=0;
		while((s=br.readLine())!=null) {
			linenumber++;
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
			         
			        	 
			        	
			        	 if(m.find( )) System.out.println("Found value: " + m.group(0) );
			        	 else if(m2.find( )) System.out.println("Found value: " + m2.group(0) );
			        	 str+=s+"\n";
				         SimpleAttributeSet set = new SimpleAttributeSet();
						 StyleConstants.setItalic(set, true);
						 StyleConstants.setForeground(set, Color.red);
						 doc.insertString(doc.getLength(),"\n"+linenumber+"  "+s, set);
			        
			    }
			 else if(s.contains("new File(")|s.contains("new Path(")) {
				 
				 SimpleAttributeSet set = new SimpleAttributeSet();
				 StyleConstants.setItalic(set, true);
				 StyleConstants.setForeground(set, Color.red);
				 doc.insertString(doc.getLength(),"\n"+linenumber+"  "+s, set);
			 }
			 else {		    	
			         System.out.println("NO MATCH");
			         str+=s+"\n";
			         SimpleAttributeSet set = new SimpleAttributeSet();
					 StyleConstants.setItalic(set, true);
					 StyleConstants.setForeground(set, Color.BLUE);
					 doc.insertString(doc.getLength(),"\n"+linenumber+"  "+s, set);
			    }
			  /*  str+=s+"\n";
				SimpleAttributeSet set = new SimpleAttributeSet();
			    StyleConstants.setItalic(set, true);
			    StyleConstants.setForeground(set, Color.BLUE);
				doc.insertString(doc.getLength(),"\n"+linenumber+"  "+s, set);*/
			
			
		
			
			
		}
		
		br.close();
		fr.close();
		//str+="</html>";
		
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

	public void InitGUI() throws BadLocationException {
		
		frame=new JFrame("Test Smell Detector..!");
		frame.setSize(1550,850);
		frame.setBackground(Color.GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		panel = new JPanel();
		panel.setBounds(200,0,1000,1000);
		panel.setBackground(Color.BLACK);
		label=new JLabel();
		textArea=new JTextArea();
		textArea.setForeground(Color.green);
		textArea.setFont(new Font("serif",Font.BOLD,23));
		textArea.setOpaque(true);
		textArea.setBackground(Color.BLACK);
		textArea.setEditable(false);
		
		pane = new JTextPane();
	    SimpleAttributeSet set = new SimpleAttributeSet();
	    StyleConstants.setBold(set, true);
	    // Set the attributes before adding text
	    pane.setCharacterAttributes(set, true);
	    pane.setForeground(Color.green);
	    pane.setFont(new Font("serif",Font.BOLD,23));
	    pane.setOpaque(true);
	    pane.setBackground(Color.BLACK);
	    pane.setEditable(false);
	    set = new SimpleAttributeSet();
	    StyleConstants.setItalic(set, true);
	    StyleConstants.setForeground(set, Color.red);

	    doc = pane.getStyledDocument();
	    doc.insertString(doc.getLength(),"No File", set);
        JScrollPane scrollableTextArea = new JScrollPane(pane);  
        scrollableTextArea.setBounds(260,0,800,795);
        scrollableTextArea.setForeground(Color.BLACK);
        scrollableTextArea.setOpaque(true);
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        frame.getContentPane().add(scrollableTextArea);
        classList= new DefaultListModel<>(); 
        list1 = new JList<>(classList);
        list1.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {

                	
                		
                		int index=list1.getSelectedIndex();
                		try {
							
                			
                			if(classPathList.size()>0&&index>=0) readFile(classPathList.get(index));
                			
						} catch (IOException | BadLocationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
						}
                }
                	
                 
                
            }
        });
        list1.setFont(new Font("serif",Font.BOLD,18));
        list1.setBounds(20,0,240,800); 
        
        list1.setForeground(Color.black);
        list1.setBackground(Color.WHITE);
        JScrollPane scrollablefile = new JScrollPane(list1); 
        scrollablefile.setBounds(20,0,240,780);
        frame.getContentPane().add(scrollablefile);
        
        JButton btn=new JButton("Choose Your Folder..!");
        btn.setBounds(1100,5,200,50);
        final JFileChooser chooser = new JFileChooser(); 
        chooser.setBounds(1100,10,400,400);
        chooser.setDialogTitle("Choose folder..");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	 System.out.println(chooser.getSelectedFile());
            	 try {
            		
					testClassSeperator=new TestClassSeperator(chooser.getSelectedFile());
					classNameList=testClassSeperator.getAllTestFileName();
					classPathList=testClassSeperator.getAllTestFilePath();					
					classList.clear();
					for(String name:classNameList) {
						classList.addElement(" "+name);
					}
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

            }
         });
        frame.add(chooser);
        JButton detectButton=new JButton("Detect Test Smell");
        detectButton.setBounds(1150,430,280,40);
        detectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	TestSmellDetector testsmell=new TestSmellDetector();
            	try {
            		
					smellInfo=testsmell.detectSmell(classPathList, classNameList);
					updateClassList();
					showSmellInfo();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
            	
            }

			
        });
            
      
        smelldataText= new JTextPane();
        smelldataText.setForeground(Color.green);
        smelldataText.setFont(new Font("serif",Font.BOLD,23));
        smelldataText.setOpaque(true);
        smelldataText.setBackground(Color.BLACK);
        smelldataText.setEditable(false);   
        smelldataText.setText("No error");
        JScrollPane smellSpec = new JScrollPane(smelldataText);  
        smellSpec.setBounds(1090,500,400,275);
        smellSpec.setForeground(Color.BLACK);
        smellSpec.setBackground(Color.RED);
        smellSpec.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        smellSpec.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        frame.getContentPane().add(smellSpec);
        
        frame.add(detectButton);
        frame.setVisible(true);
	
	}
	public void showSmellInfo() throws IOException {
		
		FileWriter csvWriter = new FileWriter("EditProjectTestOutput.csv");
		csvWriter.append("Class Name");
		csvWriter.append(",");
		csvWriter.append("Line Number");
		csvWriter.append("\n");

		
		
		String str="Smell Info: \n";
		boolean smell=false;
		for(String key:smellInfo.keySet()) {
			
			String[] line=smellInfo.get(key).split(",");
			if(line.length>1) {
				for(int i=0;i<line.length;i++) {
					str+="\n Class Name: "+key+"\n Line Number: "+line[i];			
					csvWriter.append(String.join(",", key+","+line[i]));
				    csvWriter.append("\n");
				    smell=true;
				}
			}
			else {
				str+="\n Class Name: "+key+"\n Line Number: "+line[0];			
				csvWriter.append(String.join(",", key+","+line[0]));
			    csvWriter.append("\n");
			    smell=true;
			}
			
		    
		}
		csvWriter.flush();
		csvWriter.close();
		if(smell) smelldataText.setText(str);
		else smelldataText.setText("no smell");
	}
	public void updateClassList() {
		classList.clear();
		for(String className:classNameList) {
			if(smellInfo.get(className)!=null) {
				
				classList.addElement("<html> <p style=\"color:red;padding-left:5px\"> "+className+"</p></html>");  
				System.out.println("Line: "+smellInfo.get(className));
			}
			else {
				
				classList.addElement("<html> <p style=\"color:black;padding-left:5px\"> "+className+"</p></html>");
			}
		}
		
	}

	
}
