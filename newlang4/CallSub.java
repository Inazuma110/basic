package newlang4;
import java.util.*;

public class CallSub extends Node{

  static final Set<LexicalType> firstSet = EnumSet.of(
      LexicalType.NAME
      );

  public LexicalUnit first;
  public Environment env;

  private CallSub(LexicalUnit first, Environment env) {
    this.env = env;
    this.first = first;
  }

  public static Node getHandler(LexicalUnit unit, Environment env){
    return null;
  }

  public static boolean isFirst(LexicalUnit unit){
    return firstSet.contains(unit.getType());
  }

  // @Override
  public boolean parse() throws Exception{
    LexicalUnit first = env.getInput().get();

    while(true){
      if(Stmt.isFirst(first)){
        Node handler = Stmt.getHandler(first, env);
        handler.parse();
      }else if(Block.isFirst(first)){
        Node handler = Block.getHandler(first, env);
        handler.parse();
      }else break;
    }
    return false;
  }
}
