import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;



public class Reader {
	
	XMLSlideShow ppt;
	XSLFSlide [] slides;
	XSLFShape[] shapes;
	DisplayWindow display;
	ArrayList <String> line = new ArrayList <String>();
	ArrayList <Slide> slide = new ArrayList< Slide>();
	public Reader(String s) {
		read(s);
		while(line.size() != 0)
			convert();
		readOut();
	}
	public void read(String p) {
		try {
//			Reads in the file with the name specified in "p"
			Scanner s = new Scanner(new File(p));
			while (s.hasNextLine()) {
				String g = s.nextLine();
//				Adds each line into an arraylist
				line.add(g);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void convert() {
		int beg = 0;
		int end = 0;
		Slide s = new Slide();
		for( int i = 0; i < line.size(); i ++) {
//			Determines the beginning of a slide
			if(line.get(i).contains("//BegSlide"))
				beg = i;
//			Determines the ending of a slide
			if(line.get(i).contains("//EndSlide")) {
				end = i;
				break;
			}
		}
//		Loops through those lines and adds them to an Slide object
		for(int i = beg; i < end + 1; i ++)
			s.readIn(line.get(i));
//		Adds Slide to ArrayList of Slides
		slide.add(s);
//		MAkes sure that all the lines are recorded and no endless loop is created
		while(!line.get(beg).contains("//EndSlide"))
			line.remove(beg);
		line.remove(beg);
	}
	public void readOut() {
		String text = "";
		for(int i = 0; i < slide.size(); i ++) {
			text += "__________________Slide " + (i +1) + "________________________\n";
			slide.get(i).createResults();
			text += slide.get(i).getResults();
			text += "_________________End Slide " + (i + 1) + "___________________\n";
		}
//		Creates a window that displays all the data that is outputted
		display = new DisplayWindow(text);
	}
}