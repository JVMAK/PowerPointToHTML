import java.awt.Color;
import java.util.ArrayList;


public class Output {
	ArrayList <String> lines = new ArrayList<String>();
	boolean left = true;
	String text = "";
	
	Output() {}
	public void readOut() {
		int leftCount = 0;
		for(int i = 0; i < lines.size(); i ++) {
			if(lines.get(i).contains("algn=")) {
//				CENTER Alignment
				if(lines.get(i).contains("ctr"))
					text += "CENTER_ALIGNMENT\n";
//				RIGHT Alignment
				if(lines.get(i).contains("\"r\""))
					text += "RIGHT_ALIGNMENT\n";
				left = false;
				leftCount = 1;
			}
//			If neither RIGHT nor CENTER then output as LEFT
			if(left == true && leftCount == 1) {
				text += "LEFT_ALIGNMENT\n";
				leftCount = 2;
			}
//			BOLD
			if(lines.get(i).contains("b=") && !lines.get(i).contains("b=\"0\""))
				text +="BOLD\n";
//			UNDERLINED
			if(lines.get(i).contains("u=") && !lines.get(i).contains("u=\"none\""))
				text +="UNDERLINED\n";
//			RETRIEVE COLOR VALUES
			if(lines.get(i).contains("srgbClr val=")) {
				int intValue = Integer.parseInt( lines.get(i).substring(lines.get(i).indexOf('\"') + 1, lines.get(i).indexOf('/')- 1),16);
				Color aColor = new Color( intValue );
				text += aColor.toString() + "\n";
			}
//			ITALICIZE
			if(lines.get(i).contains("i=") && !lines.get(i).contains("i=\"0\""))
				text +="ITALICIZED\n";
//			STRIKETHROUGH
			if(lines.get(i).contains("strike=") && !lines.get(i).contains("noStrike"))
				text +="STRIKETHROUGH\n";
//			DETERMINE TAB OVER AND HOW MANY
			if(lines.get(i).contains("lvl=")) {
				String d = lines.get(i).substring(lines.get(i).indexOf("lvl="));
				if(!d.substring(d.indexOf("\"") + 1, d.indexOf("\"") + 2).equals("0"))
				text += d.substring(d.indexOf("\"") + 1, d.indexOf("\"") + 2) +" LEVEL OVER\n";
			}
//			FONT SIZE
			if(lines.get(i).contains("sz="))
				text += "FONT_SIZE " + lines.get(i).substring(lines.get(i).indexOf("sz=") + 4, lines.get(i).indexOf("sz=") + 6) +"\n";
//			FONT TYPE
			if(lines.get(i).contains("latin typeface")) {
				if(!lines.get(i).contains("+mn-lt"))
					text += lines.get(i).substring(lines.get(i).indexOf("\"") + 1, lines.get(i).indexOf("/") - 1) +" FONT\n";
			}
//			ACTUAL TEXT
			if(lines.get(i).contains("<a:t>")) {
				String s = lines.get(i).substring(lines.get(i).indexOf('>'));
				text += s.substring(s.indexOf('>') + 1, s.indexOf('<')) + "\n";
				text += "\n";
				text += "\n";
				break;
			}

		}
	}
	public void readIn(String s) {
		lines.add(s);
	}
	public ArrayList <String> getArrayList() {
		return lines;
	}
	public String getLines() {
		return text;
	}

}
