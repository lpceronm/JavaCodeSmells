package classes;

import java.util.List;

import classes.Java8Parser.*;

public class Visitor<T> extends Java8BaseVisitor<T>{
	
	@Override
	public T visitCompilationUnit(CompilationUnitContext ctx) {
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
		int classLong = ctx.getStop().getLine()-ctx.getStart().getLine()-1;
		System.out.printf("Longitud Clase: %d \n",classLong );
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
		visitMethodHeader(ctx.methodHeader());
		return null;
	}
	
	@Override
	public T visitMethodBody(MethodBodyContext ctx) {
		
		int tmp = ctx.getStop().getLine() - ctx.getStart().getLine();
		int TotalLines =(ctx.block().blockStatements()!= null) ? tmp: 1;
		System.out.printf("Lineas totales en el metodo %d \n",TotalLines-1);	
		return null;
	}
	
	@Override
	public T visitMethodHeader(MethodHeaderContext ctx) {
		visitMethodDeclarator(ctx.methodDeclarator());
		return null;
	}
	
	@Override
	public T visitMethodDeclarator(MethodDeclaratorContext ctx) {
		int numParams = 0;
		if(ctx.formalParameterList()!=null){
			numParams = (int) visitFormalParameterList(ctx.formalParameterList());
			System.out.printf("Cantidad de params %d \n",numParams);
		}else{
			System.out.printf("Cantidad de params %d \n",numParams);	
		}
		
		return null;
	}
	
	@Override
	public T visitFormalParameterList(FormalParameterListContext ctx) {
		int num = 1 ; 
		
		if (ctx.formalParameters() != null)
			num+= ctx.formalParameters().formalParameter().size();	
		
		T numParams = (T) new Integer(num);
		return numParams;
	}
}

