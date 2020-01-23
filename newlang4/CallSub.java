package newlang4;
import java.util.*;

public class CallSub extends Node{

  static final Set<LexicalType> firstSet = EnumSet.of(
      LexicalType.NAME
      );

  LexicalUnit funcName;
  List<Node> exprs = new ArrayList<>();
  String output = "";

  private CallSub(LexicalUnit first, Environment env) {
    super(first, env);
  }

  public static Node getHandler(LexicalUnit unit, Environment env){
    return new CallSub(unit, env);
  }

  public static boolean isFirst(LexicalUnit unit){
    return firstSet.contains(unit.getType());
  }

  @Override
  public boolean parse() throws Exception{
    System.out.println("CallSub");
    funcName = super.first;
    if(super.env.getInput().get().getType() != LexicalType.LP) return false;

    LexicalUnit exprFirst = super.env.getInput().get();
    super.env.getInput().unget(exprFirst);

    while(true){
      if(ExprNode.isFirst(exprFirst)){
        handler = ExprNode.getHandler(exprFirst, super.env);
        if(!handler.parse()) return false;
        exprs.add(handler);
        LexicalUnit rpOrComma = super.env.getInput().get();
        if(rpOrComma.getType() == LexicalType.RP) break;
        else if(rpOrComma.getType() == LexicalType.COMMA) continue;
        else return false;
      }else return false;
    }
    return true;
  }

  @Override
  public String toString() {
    output += funcName.value.getSValue() + "[";
    for(Node e : exprs) output += e.toString() + " ";
    output = output.substring(0, output.length()-1);
    output += "];";
    return output;
  }

  @Override
  public Value eval(){
    if(super.env.library.get(funcName.value.getSValue()) == null) return null;
    Function f = super.env.getFunction(funcName.value.getSValue());
    return f.eval(exprs);
  }
}
