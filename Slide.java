import java.util.ArrayList;

public class Slide {
	ArrayList<String> lines = new ArrayList<String>();
	ArrayList<Shape> shapes = new ArrayList<Shape>();
	String text = "";
	String result = "";
	public Slide() {}
	public void createShapes() {
		while(lines.size() > 1) {
//			Variables that will be used to parse out required text
			int beg = 0;
			int end = 0;
			Shape s = new Shape();
//			Setting the Values to those variables
			for(int i = 0; i < lines.size(); i ++) {
				if (lines.get(i).contains("<xml-fragment"))
					beg = i;
				if(lines.get(i).contains("</xml-fragment>") && i > beg) {
					end = i;
					break;
				}
			}
//			Adds the text to a Shape
			for(int i = beg; i < end + 1; i ++) {
				s.readIn(lines.get(i));
			}
//			Adds Shape to ArrayList
			shapes.add(s);
//			Deletes lines that were already used, prevents repetition
			while(!lines.get(0).contains("</xml-fragment>"))
				lines.remove(lines.get(0));
			lines.remove(0);
		}
	}
	public void readIn(String s) {
		lines.add(s);
	}
	public void createResults() {
		createShapes();
		for(int i = 0; i < shapes.size(); i ++) {
			result += "Shape " + (i + 1) + "\n";
			result += "_________________ \n";
			shapes.get(i).createOutPuts();
			shapes.get(i).createResults();
			result += shapes.get(i).getResults();
			result += "___________________________________________________\n";
		}
	}
	public String getResults() {
		return result;
	}
}
