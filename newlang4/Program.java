/**
 * <program> ::= <stmt_list>
 */

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

  Program(LexicalUnit first, Environment env) {
    super(first, env);
  }

  public static Node getHandler(LexicalUnit first, Environment env){
    return new Program(first, env);
  }

  public static boolean isFirst(LexicalUnit unit){
    return firstSet.contains(unit.getType());
  }

  @Override
  public boolean parse() throws Exception{
    System.out.println("Program");
    if(StmtList.isFirst(super.first)){
      stmt_list = StmtList.getHandler(super.first, super.env);
      return stmt_list.parse();
    }
    return false;
  }

  public String toString(){
    return stmt_list.toString();
  }

  @Override
  public Value eval(){
    return stmt_list.eval();
  }
}
