package newlang4;
import java.util.*;

public class Block extends Node{

  static final Set<LexicalType> firstSet = EnumSet.of(
      LexicalType.IF, LexicalType.WHILE, LexicalType.DO
      );

  public LexicalUnit first;
  public Environment env;

  private Block(LexicalUnit first, Environment env) {
    this.first = first;
    this.env = env;
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
