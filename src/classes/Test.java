package classes;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.File;
import java.io.FileInputStream;

public class Test {
	public static void main(String[] args) throws Exception {
		try{
			
			System.setIn(new FileInputStream(new File("input.txt")));
			ANTLRInputStream input = new ANTLRInputStream(System.in); 
			Java8Lexer lexer= new Java8Lexer(input);
			// Identificar al analizador léxico como fuente de tokens para el sintactico
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			// Crear el objeto correspondiente al analizador sintáctico que se alimenta apartir del buffer de tokens
			Java8Parser parser = new Java8Parser(tokens);
			ParseTree tree = parser.compilationUnit(); // begin parsing at init rule
			Visitor<Object> loader = new Visitor<Object>();
			loader.visit(tree);
			System.out.println(tree.toStringTree(parser)); // print LISP-style tree
		} catch (Exception e){
			System.err.println("Error (Test): " + e);
		}
	}
}