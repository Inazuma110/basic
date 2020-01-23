package newlang4;
import java.util.*;

public class Stmt extends Node{

  static final Set<LexicalType> firstSet = EnumSet.of(
      LexicalType.FOR, LexicalType.END,LexicalType.NAME
      );

  public LexicalUnit first;
  public Environment env;
  public Node handler;

  Stmt(LexicalUnit first, Environment env) {
    super(first, env);
  }

  public static Node getHandler(LexicalUnit unit, Environment env){
    return new Stmt(unit, env);
  }

  public static boolean isFirst(LexicalUnit unit){
    return firstSet.contains(unit.getType());
  }

  // @Override
  public boolean parse() throws Exception{
    System.out.println("stmt");

    if(SubstNode.isFirst(super.first)){
      handler = SubstNode.getHandler(super.first, super.env);
      if(!handler.parse()) {
        handler = CallSub.getHandler(super.first, super.env);
        if(!handler.parse()) return false;
      }
      return true;
    }
    // }else if(For.isFirst(first)){
    //   Node handler = Block.getHandler(first, env);
    //   handler.parse();
    if(super.first.getType() ==LexicalType.END){
      handler = End.getHandler(super.first, super.env);
      return true;
    }
    return false;
  }

  public String toString() {
    return handler.toString();
  }

  @Override
  public Value eval(){
    return handler.eval();
  }
}
