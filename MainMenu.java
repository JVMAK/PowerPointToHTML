import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class MainMenu {
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	ChoiceMenu u;
	JFrame frame;
	JLabel label;
	JButton upload;
	JButton read;
	JButton done;
	MainMenu() {
//		Creates the basics for a Java applet window
		frame = new JFrame("Selection Menu");
		label = new JLabel("Choose to either upload a .pptx file or read an xml file.");
		upload = new JButton("Upload");
		read = new JButton("Read");
		done = new JButton("Done");
		frame.getContentPane().setLayout(new FlowLayout());
		frame.setSize(220, 230);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Brings up the Menu to select a .pptx file to convert to xml
		upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(ae.getActionCommand().equals("Upload"))
					u = new ChoiceMenu(true);
			}
		});
//		Brings up Menu to select a .xml file to read and output data from
		read.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(ae.getActionCommand().equals("Read")) 
					u = new ChoiceMenu(false);
			}
		});
//		Closes the Program
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(ae.getActionCommand().equals("Done"))
					System.exit(0);
			}
		});
		frame.getContentPane().add(label);
		frame.getContentPane().add(upload);
		frame.getContentPane().add(read);
		frame.getContentPane().add(done);
		frame.setSize(400, 100);
//		Centers the Window
		frame.setLocation((int)(screenSize.getWidth()/2) - (frame.getWidth()/2),(int) (screenSize.getHeight()/2) - (frame.getHeight()/2));
		frame.setVisible(true);
	}
//	Starts the Program
	public static void main(String [] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainMenu();
			}
		});
	}
}
