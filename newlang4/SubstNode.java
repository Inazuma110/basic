package newlang4;
import java.util.*;

public class SubstNode extends Node{

  static final Set<LexicalType> firstSet = EnumSet.of(
      LexicalType.NAME
      );

  Environment env;
  LexicalUnit first;
  Node handler;
  LexicalUnit expr;

  private SubstNode(LexicalUnit first, Environment env) {
    super(first, env);
    // this.env = env;
    // this.first = first;
  }

  public static Node getHandler(LexicalUnit unit, Environment env){
    return new SubstNode(unit, env);
  }

  public static boolean isFirst(LexicalUnit unit){
    return firstSet.contains(unit.getType());
  }

  // @Override
  public boolean parse() throws Exception{
    // LexicalUnit first = env.getInput().get();
    // env.getInput().unget(first);
    System.out.println("subst");
    if(Variable.isFirst(super.first)){
      handler = Variable.getHandler(super.first, super.env);
    }
    LexicalUnit eq = super.env.getInput().get();

    if(eq.getType() != LexicalType.EQ) {
      super.env.getInput().unget(eq);
      return false;
    }

    expr = super.env.getInput().get();
    super.env.getInput().unget(expr);
    if(ExprNode.isFirst(expr)){
      handler = ExprNode.getHandler(super.first, super.env);
      if(!handler.parse()) return false;
    }else return false;

    return true;
  }

  @Override
  public String toString() {
    return super.first.value.getSValue() + "[" + this.handler.toString() + "];";
    // return handler.toString();
  }

}
