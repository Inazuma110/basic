package newlang4;
import java.util.*;

public class Block extends Node{

  static final Set<LexicalType> firstSet = EnumSet.of(
      LexicalType.IF, LexicalType.WHILE, LexicalType.DO
      );

  Node handler;

  Block(LexicalUnit first, Environment env) {
    super(first, env);
  }

  public static Node getHandler(LexicalUnit unit, Environment env){
    return new Block(unit, env);
  }

  public static boolean isFirst(LexicalUnit unit){
    return firstSet.contains(unit.getType());
  }

  // @Override
  public boolean parse() throws Exception{
    System.out.println("Block");
    // LexicalUnit first = suprenv.getInput().get();

    if(LoopBlock.isFirst(super.first)){
      handler = LoopBlock.getHandler(super.first, super.env);
      if(!handler.parse()) return false;
    }else if(Block.isFirst(first)){
      handler = Block.getHandler(first, env);
      if(!handler.parse()) return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return handler.toString();
  }
}
