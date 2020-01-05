package newlang4;
import java.util.*;
import java.*;

public class ExprNode extends Node{
  List<Node> operand = new ArrayList<Node>();
  List<LexicalUnit> operate = new ArrayList<LexicalUnit>();

  static final Set<LexicalType> firstSet = EnumSet.of(
      LexicalType.NAME, LexicalType.INTVAL,
      LexicalType.DOUBLEVAL, LexicalType.LITERAL,
      LexicalType.LP, LexicalType.RP,
      LexicalType.SUB, LexicalType.MUL,
      LexicalType.DIV, LexicalType.ADD
      );

  static Map<LexicalType, Integer> priorityMap = new HashMap<LexicalType, Integer>(){
    {
      put(LexicalType.DIV, 3);
      put(LexicalType.MUL, 2);
      put(LexicalType.ADD, 1);
      put(LexicalType.SUB, 1);
    }
  };

  public Stack<LexicalUnit> rpn = new Stack<>();
  public Queue<LexicalUnit> result = new ArrayDeque<>();


  public String output = "";

  private ExprNode(Environment env) {
    super(env);
  }



  // private ExprNode(Node expr1, LexicalUnit ope, Node expr2, Environment env){
  //   super(env);
  //   type = NodeType.EXPR;
  //   operand.add(expr1);
  //   operate.add(ope);
  //   operand.add(expr2);
  // }

  public static Node getHandler(LexicalUnit unit, Environment env){
    return new ExprNode(env);
  }

  public static boolean isFirst(LexicalUnit unit){
    return firstSet.contains(unit.getType());
  }

  // @Override
  public boolean parse() throws Exception{
    System.out.println("expr");
    LexicalUnit unit = env.getInput().get();
    env.getInput().unget(unit);

    while(ExprNode.isFirst(unit)){
      unit = env.getInput().get();
      if(!ExprNode.isFirst(unit)){
        super.env.getInput().unget(unit);
        break;
      }

      if(unit.getType() == LexicalType.INTVAL || unit.getType() == LexicalType.DOUBLEVAL)
      {
        result.add(unit);
      }else if(unit.getType() == LexicalType.RP){
        LexicalUnit ufs = rpn.pop();
        while(ufs.getType() != LexicalType.LP) {
          result.add(ufs);
          ufs = rpn.pop();
        }
      }else if(unit.getType() == LexicalType.LP){
        rpn.push(unit);
      }else{
        if(rpn.isEmpty() || rpn.peek().getType() == LexicalType.LP){
          rpn.push(unit);
        }
        else if(!(priorityMap.get(rpn.peek().getType()) > priorityMap.get(unit.getType())))
          rpn.push(unit);
        else{
          LexicalType t1 = rpn.peek().getType();
          LexicalType t2 = unit.getType();
          while(!(rpn.isEmpty()) && (t1 != LexicalType.LP) &&
              (priorityMap.get(t1) > priorityMap.get(t2))){
            t1 = rpn.peek().getType();
            result.add(rpn.pop());
          }
          rpn.add(unit);
        }
      }
    }

    while(!rpn.isEmpty()) result.add(rpn.pop());
    Queue<LexicalUnit> tmp = result;
    while(!result.isEmpty()){
      output += result.poll().value.getSValue() + " ";
    }
    result = tmp;

    return true;
  }

  @Override
  public String toString() {
    return output;
  }
}
