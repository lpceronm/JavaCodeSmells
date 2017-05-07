package classes;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Dictionary;
import java.util.HashMap;

public class Test {
	public HashMap<String,Integer> lexemes = new HashMap<String,Integer>();
	public HashMap<String,Integer> classes = new HashMap<String,Integer>();
	
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
			
			
	        Test main = new Test();

	        /*try {
	        	System.out.println("Si");
	            main.run();
	        } catch (Exception e) {
	            System.err.println(e.getMessage());
	        }*/
	        

			
			
		} catch (Exception e){
			System.err.println("Error (Test): " + e);
		}
	}
	
	

    private void run() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/words?user=root&password=root&zeroDateTimeBehavior=convertToNull");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM words.word");
        System.out.println("Printing schema for table: " + resultSet.getMetaData().getTableName(1));
        int columnCount = resultSet.getMetaData().getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            System.out.println(i + " " + resultSet.getMetaData().getColumnName(i));
        }

        System.out.println("Searching for example user.");
        boolean exampleUserFound = false;

        while (resultSet.next()) {
            String username = resultSet.getString("lexeme");
            System.out.println(username);
            if (username.equals("hagiographers")) {
                System.out.println("Example user found.");
                System.out.println("Name: " + username);
                exampleUserFound = true;

                break;
            }
        }
        System.out.println("Finalizo");

    }
	
}