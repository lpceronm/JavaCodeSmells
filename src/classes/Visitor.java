package classes;

import java.util.List;

import classes.Java8Parser.*;

public class Visitor<T> extends Java8BaseVisitor<T>{
	
	@Override
	public T visitCompilationUnit(CompilationUnitContext ctx) {
		// TODO Auto-generated method stub
		List<TypeDeclarationContext> types = ctx.typeDeclaration(); 
		
		for(TypeDeclarationContext t: types  ){
			visit(t);
		}
		
		return null;
	}
	
	@Override
	public T visitTypeDeclaration(TypeDeclarationContext ctx) {
		visitChildren(ctx);
		return null;
	}
	
	
}

