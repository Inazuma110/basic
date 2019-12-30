package newlang4;
import java.util.*;

public class Program extends Node{
  Node stmt_list;
  Environment env;
  public LexicalUnit first;

  static final Set<LexicalType> firstSet = EnumSet.of(
      LexicalType.NAME, LexicalType.FOR, LexicalType.END,
      LexicalType.WHILE, LexicalType.IF, LexicalType.DO
      );

  private Program(LexicalUnit first, Environment env) {
    this.env = env;
  }

  public static Node getHandler(LexicalUnit first, Environment env){
    return new Program(first, env);
  }

  public static boolean isFirst(LexicalUnit unit){
    return firstSet.contains(unit.getType());
  }

  // @Override
  public boolean parse() throws Exception{
    LexicalUnit first = env.getInput().get();
    // System.out.println(first);
    if(StmtList.isFirst(first)){
      stmt_list = StmtList.getHandler(first, env);
      return stmt_list.parse();
      // handler.parse();
      // return true;
    }
    return false;
  }

  // public String to_string(){
  //   return stmt_list.to_string();
  // }
}
