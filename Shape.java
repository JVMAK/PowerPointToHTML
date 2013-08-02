import java.util.ArrayList;


public class Shape {
	
	ArrayList<String> lines = new ArrayList<String>();
	ArrayList<Output> outPuts = new ArrayList<Output>();
	String text = "";
	String result = "";
	int id, x, y, sizex, sizey;
	public Shape() {
		id = 0;
		x = -1;
		y = -1;
		sizex = -1;
		sizey = -1;
	}
	public void createOutPuts() {
		retrieveId();
		if( id == 4) {
			int count = 0;
			int para = 0;
			for(int i = 0; i< lines.size(); i ++) {
				if(lines.get(i).contains("<a:gridCol"))
						count++;
				if(lines.get(i).contains("<a:p>"))
					para++;
			}
			para = para/count;
			result += "Table w/" + count + " Columns ; " + para + " Rows\n";
		}
		retrieveSize();
		if(x > 0 && y > 0 && sizex > 0 && sizey > 0) {
			result += "X-Coordinate (Pixel): " + x + "; Y-Coordinate (Pixel): " + y + "\n";
			result += "X-Size (Pixel): " + sizex + "; Y-Size (Pixel): " + sizey + "\n";
		}
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
	public void retrieveId() {
		for(int i = 0; i < lines.size(); i ++) {
			String s = lines.get(i);
			if(s.contains("id=")) {
				String d = s.substring(s.indexOf("id="));
				id = Integer.parseInt(d.substring(d.indexOf("\"") + 1, d.indexOf("\"")  + 2));
				break;
			}
		}
	}
	public void retrieveSize() {
		for(int i = 0; i < lines.size(); i ++) {
			String s = lines.get(i);
			if(s.contains("x=") && s.contains("y=")) {
				String b = s.substring(s.indexOf("x=") + 3);
				String c = s.substring(s.indexOf("y=") + 3);
				x = Integer.parseInt(b.substring(0, b.indexOf("\""))); 
				y = Integer.parseInt(c.substring(0, b.indexOf("\"")));
			}
			if(s.contains("cx=") && s.contains("cy=")) {
				String b = s.substring(s.indexOf("x=") + 3);
				String c = s.substring(s.indexOf("y=") + 3);
				sizex = Integer.parseInt(b.substring(0, b.indexOf("\""))); 
				sizey = Integer.parseInt(c.substring(0, b.indexOf("\"")));
			}
		}
	}
	public void createResults() {
//		Retrieves text from all the outputs
		for(int i = 0; i < outPuts.size(); i ++) {
			outPuts.get(i).readOut();
			result += outPuts.get(i).getLines();
			result += " \n";
		}
	}
	public String getResults() {
		return result;
	}
}
