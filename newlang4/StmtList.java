package newlang4;
import java.util.*;

public class StmtList extends Node{

  static final Set<LexicalType> firstSet = EnumSet.of(
      LexicalType.NAME, LexicalType.FOR, LexicalType.END,
      LexicalType.WHILE, LexicalType.IF, LexicalType.DO
      );

  Environment env;
  LexicalUnit first;
  Node handler;

  StmtList(LexicalUnit first, Environment env) {
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
    System.out.println("Stmtlist");
    while(true){
      if(Stmt.isFirst(first)){
        handler = Stmt.getHandler(first, env);
        return handler.parse();
      }else if(Block.isFirst(first)){
        handler = Block.getHandler(first, env);
        return handler.parse();
      }else break;
    }
    return false;
  }

  @Override
  public String toString(){
    return handler.toString();
      // if(Stmt.isFirst(first)) return Stmt.toString(first);
      // else if(Block.isFirst(first)) return Block.toString(first);
  }
}
