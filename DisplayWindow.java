import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class DisplayWindow {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	JFrame frame;
	JTextArea text;
	JScrollPane scroll;
	JButton done;
	DisplayWindow(String t) {
		frame = new JFrame("Display Window");
		text = new JTextArea(t);
		scroll = new JScrollPane(text);
		done = new JButton("Done");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setSize(220, 240);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(scroll);
		frame.setSize(600, 500);
		frame.setLocation((int)(screenSize.getWidth()/2) - (frame.getWidth()/2),(int) (screenSize.getHeight()/2) - (frame.getHeight()/2));
		frame.setVisible(true);
		
	}
}
