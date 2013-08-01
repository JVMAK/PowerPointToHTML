import java.util.ArrayList;


public class Shape {
	
	ArrayList<String> lines = new ArrayList<String>();
	ArrayList<Output> outPuts = new ArrayList<Output>();
	String text = "";
	String result = "";
	public Shape() {}
	public void createOutPuts() {
		while(lines.size() > 1) {
//			Creates Variables to determine beginning and end of text
			int beg = 0;
			int end = 0;
			Output o = new Output();
//			Assigns those varables to their proper values
			for(int i = 0; i < lines.size(); i ++) {
				if (lines.get(i).contains("<a:p>"))
					beg = i;
				if(lines.get(i).contains("</a:p>") && i > beg) {
					end = i;
					break;
				}
			}
//			Reads in the text for an Output Object
			for(int i = beg; i < end + 1; i ++) {
				o.readIn(lines.get(i));
			}
//			Adds object to ArrayList
			outPuts.add(o);
//			Check to make sure that no repetition will occur
			while(!lines.get(0).contains("</a:p>")) {
				lines.remove(lines.get(0));
				if(lines.size() <= 0)
					break;
			}
			if(lines.size() > 0)
			lines.remove(0);
		}
	}
	public void readIn(String s) {
		lines.add(s);
	}
	public void createResults() {
//		Retrieves text from all the outputs
		for(int i = 0; i < outPuts.size(); i ++) {
			result += "Output " + (i + 1) + "\n";
			outPuts.get(i).readOut();
			result += outPuts.get(i).getLines();
			result += " \n";
		}
	}
	public String getResults() {
		return result;
	}
}
