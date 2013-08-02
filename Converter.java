import java.io.*;


import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;


public class Converter{
	String input = "";
	XMLSlideShow ppt;
	XSLFSlide [] slides;
	XSLFShape[] shapes;
	FileWriter fWriter = null;
	BufferedWriter writer = null;
	public Converter(String name) {
		input = name;
		String d = input;
		while(d.contains("/")) {
			d = d.substring(d.indexOf("/") + 1);
		}
	
		try {
			if(input.contains(".pptx")) {
//				Creates a file that is named 
				grabFile(input);
				fWriter = new FileWriter(d.substring(0,d.indexOf(".")) + ".xml");
				writer = new BufferedWriter(fWriter);
				convert();
			}
			else {
//				In case of wrong type of file is selected
				System.out.println("Wrong File");
				System.exit(0);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void grabFile(String name) throws FileNotFoundException, IOException {
		//reads in data from the .pptx file
		ppt = new XMLSlideShow(new FileInputStream(name));
		slides = ppt.getSlides();
	}
	public void convert() {
		try {
//		    Goes through each slide, and then each shape (Text Box, Image, ect.) in each slide
		    for(int i = 0; i < slides.length; i ++) {
		    	writer.write("//BegSlide" + (i + 1));
		    	writer.newLine();
				shapes = slides[i].getShapes();
				if(shapes.length > 0)
//					Notes down the xml code for the shape that will contain text and so forth
					for(int j = 0; j < shapes.length; j++) {
						writer.write(shapes[j].getXmlObject().toString());
						writer.newLine();
					}	
				writer.write("//EndSlide" + (i + 1));
				writer.newLine();
			}
		    writer.close();
		} catch (Exception e) {}
	}
}
