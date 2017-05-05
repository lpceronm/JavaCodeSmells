package classes;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.File;

public class Test {
	public static void main(String[] args) throws Exception {
		try{
			//File f = new File(args[0]);
			// crear un analizador léxico que se alimenta apartir de la entrada (archivo  o consola)
			Java8Lexer lexer;
			if (args.length>0)
				lexer = new Java8Lexer(new ANTLRFileStream(args[0]));
			else
				lexer = new Java8Lexer(new ANTLRInputStream(System.in));
			// Identificar al analizador léxico como fuente de tokens para el sintactico
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			// Crear el objeto correspondiente al analizador sintáctico que se alimenta apartir del buffer de tokens
			Java8Parser parser = new Java8Parser(tokens);
			ParseTree tree = parser.compilationUnit(); // begin parsing at init rule
			System.out.println(tree.toStringTree(parser)); // print LISP-style tree
		} catch (Exception e){
			System.err.println("Error (Test): " + e);
		}
	}
}