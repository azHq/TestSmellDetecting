package testsmellproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

public class MainClass {

	public static void main(String[] args) throws IOException, BadLocationException {
		
		JFrame frame=new JFrame("Test Smell Detector..!");
		frame.setSize(1550,1030);
		frame.setBackground(Color.GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = null;

                try {
	                image = ImageIO.read(getClass().getResourceAsStream("/image2.png"));
	                g.drawImage(image, 0, 0,1550,1000,null);
                }
                catch(IOException e) {
                	
                }
            }
        };
        panel.setBounds(0,0,1550,1030);
        frame.add(panel);
		//JLabel background=new JLabel(new ImageIcon("D:\\android-ndk-r13b\\eclipse project\\image2.png"));
		//background.setSize(1550,1030);
		JLabel l=new JLabel();
		l.setBounds(550,100,1000,200);
		l.setFont(new Font("serif",Font.BOLD,45));
		l.setText("Welcome To Our Community");
		l.setForeground(Color.BLUE);
		panel.add(l);
		JButton btn=new JButton();
		btn.setBounds(670,700,400,50);
		btn.setBackground(Color.BLUE);
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font("serif",Font.BOLD,25));
		btn.setText("Click Here to Detect Smell..!");
		btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	try {
					ProjectController test=new ProjectController();
				} catch (IOException | BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
		});
		frame.add(btn);
		frame.setVisible(true);
		
		
	}

}
