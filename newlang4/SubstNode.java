package newlang4;
import java.util.*;

public class SubstNode extends Node{

  static final Set<LexicalType> firstSet = EnumSet.of(
      LexicalType.NAME
      );

  Environment env;
  LexicalUnit first;
  Node variable;
  Node exprHandler;
  LexicalUnit expr;

  private SubstNode(LexicalUnit first, Environment env) {
    super(first, env);
  }

  public static Node getHandler(LexicalUnit unit, Environment env){
    return new SubstNode(unit, env);
  }

  public static boolean isFirst(LexicalUnit unit){
    return firstSet.contains(unit.getType());
  }

  @Override
  public boolean parse() throws Exception{
    System.out.println("subst");
    if(Variable.isFirst(super.first)){
      variable = Variable.getHandler(super.first, super.env);
    }
    LexicalUnit eq = super.env.getInput().get();

    if(eq.getType() != LexicalType.EQ) {
      super.env.getInput().unget(eq);
      return false;
    }

    expr = super.env.getInput().get();
    super.env.getInput().unget(expr);
    if(ExprNode.isFirst(expr)){
      exprHandler = ExprNode.getHandler(super.first, super.env);
      if(!exprHandler.parse()) return false;
    }else return false;
    return true;
  }

  @Override
  public String toString() {
    return super.first.value.getSValue() + "[" + this.exprHandler.toString() + "];";
    // return variable.to_string() + "[" + this.exprHandler.toString() + "];";
  }

  @Override
  public Value eval(){
    try{
      Value v = exprHandler.eval();
      variable.setValue(v);
    }catch(Exception e){
      e.printStackTrace();
    }
    super.env.var_table.put(this.variable.toString(), (Variable)variable);
    return null;
  }
}
