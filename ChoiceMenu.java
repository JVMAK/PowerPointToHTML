
import javax.swing.*;


import java.awt.*;
import java.awt.event.*;

public class ChoiceMenu{
	Converter c;
	Reader i;
	boolean upload = false;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	JFrame frame = new JFrame("Select a File");
	JLabel label;
	JFileChooser choose = new JFileChooser();
	ChoiceMenu(boolean n) {
		final boolean d = n;
//		Differentiates between Uploading and reading
		if(n == true)
			label = new JLabel("Select a File to Upload");
		if(n != true)
			label = new JLabel("Select a File to Read");
		frame.getContentPane().setLayout(new FlowLayout());
		frame.setSize(220, 240);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		choose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (ae.getActionCommand().equals("ApproveSelection")) {					
					if(choose.getSelectedFile().getPath().contains(".pptx") && d == true)
//						Converts a .pptx file into xml
						c = new Converter(choose.getSelectedFile().getPath());
					if (choose.getSelectedFile().getPath().contains(".xml") && d != true) {
//						Reads in the data in an xml file
						i = new Reader(choose.getSelectedFile().getPath());
					}
					frame.setVisible(false);
				}
				if (ae.getActionCommand().equals("CancelSelection")) {
					frame.setVisible(false);
				}
			}
		});
		frame.getContentPane().add(label);
		frame.getContentPane().add(choose);
		frame.setSize(600, 500);
		frame.setLocation((int)(screenSize.getWidth()/2) - (frame.getWidth()/2),(int) (screenSize.getHeight()/2) - (frame.getHeight()/2));
		frame.setVisible(true);
	}
}
