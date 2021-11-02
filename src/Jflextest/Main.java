package Jflextest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
	
	public static void main(String[] args){
		try {
			NewLexer lexer = new NewLexer(readInput());
			HTMLBuldier htmlBuilder = new HTMLBuldier();
			
			processInput(lexer, htmlBuilder);
			htmlBuilder.createDocument();
			System.out.println("The HTML document was created");
		} 
		catch(IOException e) {
			System.out.println("Oh no, something went wrong :(");
		}
	}
	
	public static InputStream readInput() throws IOException {
		//read the input file a convert it to InputStream
		byte[] inputFile = Files.readAllBytes(Paths.get("src/Jflextest/input.txt"));
		return new ByteArrayInputStream(inputFile);
	}
	public static void processInput(NewLexer lexer, HTMLBuldier htmlBuilder) throws IOException {
		while(true) {
			Token token = lexer.yylex();
			if(token==null) return;
			htmlBuilder.addElement(lexer.lexeme, token);
		}
	}
}
