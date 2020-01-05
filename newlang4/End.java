package newlang4;
import java.util.*;

public class End extends Node{

  static final Set<LexicalType> firstSet = EnumSet.of(
      LexicalType.END
      );


  End(LexicalUnit first, Environment env) {
    super(first, env);
  }

  public static Node getHandler(LexicalUnit unit, Environment env){
    return new End(unit, env);
  }

  public static boolean isFirst(LexicalUnit unit){
    return firstSet.contains(unit.getType());
  }

  // @Override
  public boolean parse() throws Exception{
    return true;
  }

  @Override
  public String toString(){
    return "END";
  }
}
