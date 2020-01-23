package newlang4;
import java.util.*;

public class StmtList extends Node{

  static final Set<LexicalType> firstSet = EnumSet.of(
      LexicalType.NAME, LexicalType.FOR, LexicalType.END,
      LexicalType.WHILE, LexicalType.IF, LexicalType.DO
      );

  List<Node> children = new ArrayList<>();
  String output = "";

  StmtList(LexicalUnit first, Environment env) {
    super(first, env);
  }

  public static Node getHandler(LexicalUnit unit, Environment env){
    return new StmtList(unit, env);
  }

  public static boolean isFirst(LexicalUnit unit){
    return firstSet.contains(unit.getType());
  }

  @Override
  public boolean parse() throws Exception{
    while(true){
      first = super.env.getInput().get();
      if(first.getType() == LexicalType.NL) continue;
      System.out.println("Stmtlist");

      if(Stmt.isFirst(first)){
        handler = Stmt.getHandler(first, env);
        if(!handler.parse()) return false;
      }else if(Block.isFirst(first)){
        handler = Block.getHandler(first, env);
        if(!handler.parse()) return false;
      }else if(first.getType() != LexicalType.EOF){
        super.env.getInput().unget(first);
        return true;
      }else if(first.getType() == LexicalType.EOF) break;
      children.add(handler);
    }

    return true;
  }

  @Override
  public String toString(){
    for(Node c : children){
      output += c.toString() + " ";
    }
    output = output.substring(0, output.length()-1);
    return output;
  }

  @Override
  public Value eval(){
    for(Node c : children){
      c.eval();
    }
    return null;
  }

}
