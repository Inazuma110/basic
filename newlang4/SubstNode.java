package newlang4;
import java.util.*;

public class StmtList extends Node{

  static final Set<LexicalType> firstSet = EnumSet.of(
      LexicalType.NAME
      );

  Environment env;
  LexicalUnit first;

  private StmtList(LexicalUnit first, Environment env) {
    this.env = env;
    this.first = first;
  }

  public static Node getHandler(LexicalUnit unit, Environment env){
    return new StmtList(unit, env);
  }

  public static boolean isFirst(LexicalUnit unit){
    return firstSet.contains(unit.getType());
  }

  // @Override
  public boolean parse() throws Exception{
    LexicalUnit first = env.getInput().get();
    env.getInput().unget(first);
    if(Variable.isFirst(first)){
      handler = Variable.getHandler(first, env);
      handler.parse();
    }
    first = env.getInput().get();
    if(first.getType() != LexicalUnit.EQ) return false;
    first = env.getInput().get();
    env.getInput().unget(first);
    if(ExprNode.isFirst(first)){
      handler = ExprNode.getHandler(first, env);
      System.out.println("expr");
      handler.parse();
    }

    return true;
  }
}
