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
	
	@Override
	public T visitClassDeclaration(ClassDeclarationContext ctx) {
		visitNormalClassDeclaration(ctx.normalClassDeclaration());
		return null;
	}
	
	@Override
	public T visitNormalClassDeclaration(NormalClassDeclarationContext ctx) {
		visitClassBody(ctx.classBody());
		return null;
	}

	@Override
	public T visitClassBody(ClassBodyContext ctx) {
		List<ClassBodyDeclarationContext> bodyDecl = ctx.classBodyDeclaration();
		for(ClassBodyDeclarationContext bd: bodyDecl){
			visitClassBodyDeclaration(bd);
		}
		return null ;
	}
	
	@Override
	public T visitClassBodyDeclaration(ClassBodyDeclarationContext ctx) {
		visitChildren(ctx);
		
		return null;
	}
	
	@Override
	public T visitClassMemberDeclaration(ClassMemberDeclarationContext ctx) {
		visitChildren(ctx);
		return null;
	}
	
	@Override
	public T visitMethodDeclaration(MethodDeclarationContext ctx) {
		visitMethodBody(ctx.methodBody());
		return null;
	}
	
	@Override
	public T visitMethodBody(MethodBodyContext ctx) {
		String a = ctx.getText();
		
		int b = ctx.getStart().getLine();
		int c = ctx.getStop().getLine();
		int TotalLines = c - b;
		System.out.println(TotalLines+1);
		System.out.println(b);
		System.out.println(c);
		
		return null;
	}
	
	
}

