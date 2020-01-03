package newlang4;
import java.util.*;

public class SubstNode extends Node{

  static final Set<LexicalType> firstSet = EnumSet.of(
      LexicalType.NAME
      );

  Environment env;
  LexicalUnit first;
  Node handler;

  private SubstNode(LexicalUnit first, Environment env) {
    this.env = env;
    this.first = first;
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
    if(Variable.isFirst(first)){
      handler = Variable.getHandler(first, env);
    }
    LexicalUnit eq = env.getInput().get();

    if(eq.getType() != LexicalType.EQ) return false;
    //
    LexicalUnit expr = env.getInput().get();
    // // env.getInput().unget(first);
    if(ExprNode.isFirst(expr)){
      handler = ExprNode.getHandler(first, env);
      System.out.println("expr");
      handler.parse();
    }else return false;

    return true;
  }

  @Override
  public String toString() {
    return first.value.getSValue() + "[";
    // return handler.toString();
  }

}
