package Jflextest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HTMLBuldier {
	StringBuilder body;
	public HTMLBuldier() {
		body = new StringBuilder();
		this.startHTML();
	}
	
	public void startHTML() {
		body.append("<html>\n");
		body.append("<head>\n");
		body.append("<title>Miniproyecto jflex</title>\n");
		body.append("</head>\n");
		
		body.append("<body>\n");
	}
	
	public void addElement(String str, Token t) {
//		System.out.format("%s: %s\n", str, t);
		String element;
		
		switch(t) {
		case NAME:
			element = String.format("<p><b>%s</b></p>\n", str);
			body.append(element);
			break;
		case TELEPHONE:
			element = String.format("<p style=\"color:Green;\">%s</p>\n", str);
			body.append(element);
			break;
 
		case CELLPHONE:
			element = String.format("<p style=\"color:Red;\">%s</p>\n", str);
			body.append(element);
 			break;
 			
		case URL:
			element = String.format("<p><a href=\"%s\">%s</a></p>\n", str, str);
			body.append(element);
			break;
			
		case EMAIL:
			element = String.format("<p><a href=\"mailto:%s\">%s</a></p>\n", str, str);
			body.append(element);
			break;
			
		case VIDEO: 
			str = str.replace("watch?v=", "embed/");
			element = String.format("<iframe width=\"420\" height=\"315\"src=\"%s\"></iframe>\n", str);
			body.append(element);
			break;
			
		case ERROR:
		default: break;
		
		}		
	}
	
	public void closeHTML() {
		body.append("</body>\n");
		body.append("</html>");
	}
	
	public String writeHTML() {
		return body.toString();		
	}
	
	public void createDocument() throws IOException {
		this.closeHTML();
		String html = this.writeHTML();
		
		File file = new File("src/Jflextest/file.html");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));		
		bw.write(html);
		bw.close();		
	}
	
	

}
