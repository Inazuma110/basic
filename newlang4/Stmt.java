package newlang4;
import java.util.*;

public class Stmt extends Node{

  static final Set<LexicalType> firstSet = EnumSet.of(
      LexicalType.FOR, LexicalType.END,LexicalType.NAME
      );

  public LexicalUnit first;
  public Environment env;
  public Node handler;

  private Stmt(LexicalUnit first, Environment env) {
    this.env = env;
    this.first = first;
  }

  public static Node getHandler(LexicalUnit unit, Environment env){
    return new Stmt(unit, env);
  }

  public static boolean isFirst(LexicalUnit unit){
    return firstSet.contains(unit.getType());
  }

  // @Override
  public boolean parse() throws Exception{

    while(true){
      LexicalUnit first = env.getInput().get();
      System.out.println("stmt");
      if(SubstNode.isFirst(first)){
        handler = SubstNode.getHandler(first, env);
        handler.parse();
      }else if(CallSub.isFirst(first)){
        handler = Stmt.getHandler(first, env);
        handler.parse();
      }
      // }else if(For.isFirst(first)){
      //   Node handler = Block.getHandler(first, env);
      //   handler.parse();
      else break;
    }
    return false;
  }

  public String toString() {
    return handler.toString();
  }
}
