package newlang4;
import java.util.*;

public class Program extends Node{

  static final Set<LexicalType> firstSet = EnumSet.of(
      LexicalType.NAME, LexicalType.FOR, LexicalType.END,
      LexicalType.WHILE, LexicalType.IF, LexicalType.DO
      );

  private Program() {
  }

  public static Node getHandler(LexicalUnit unit, Environment env){
    return new Program();
  }

  public static boolean isFirst(LexicalUnit unit){
    return firstSet.contains(unit.getType());
  }

  // @Override
  public boolean parse() throws Exception{
    if(StmtList.isFirst(first)){
      Node handler = StmtList.getHandler(first, env);
      handler.parse();
    }
  }
}
