package newlang4;
import java.util.*;
import java.*;

public class ExprNode extends Node{
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

  public static Node getHandler(LexicalUnit unit, Environment env){
    return new ExprNode(env);
  }

  public static boolean isFirst(LexicalUnit unit){
    return firstSet.contains(unit.getType());
  }

  @Override
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
      if(unit.getType() == LexicalType.LITERAL){
        output = unit.value.getSValue();
        result.add(unit);
        return true;
      }

      if(unit.getType() == LexicalType.INTVAL || unit.getType() == LexicalType.DOUBLEVAL ||
          unit.getType() == LexicalType.NAME)
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
    Queue<LexicalUnit> tmp = new ArrayDeque<>();
    tmp.addAll(result);
    while(!result.isEmpty()){
      output += result.poll().value.getSValue() + " ";
    }
    output = output.substring(0, output.length()-1);
    result = tmp;

    return true;
  }

  @Override
  public String toString() {
    return output;
  }

  @Override
  public Value eval(){
    Queue<LexicalUnit> tmp = new ArrayDeque<>();
    tmp.addAll(result);

    LexicalUnit lu1 = result.poll();
    LexicalUnit lu2 = result.poll();
    LexicalUnit lu3 = result.poll();


    int lu1v = 0, lu2v = 0;
    if(lu1.getType() == LexicalType.NAME){
      // System.out.println(super.env.getVariable(lu1.value.getSValue()).getValue().getSValue());
      lu1v = Integer.parseInt(super.env.getVariable(lu1.value.getSValue()).getValue().getSValue());
    }else{
      lu1v = Integer.parseInt(lu1.value.getSValue());
    }

    if(lu2 == null){
      result = tmp;
      return new ValueImpl(String.valueOf(lu1v), ValueType.INTEGER);
    }

    if(lu2.getType() == LexicalType.NAME){
      lu2v = Integer.parseInt(super.env.getVariable(lu2.value.getSValue()).getValue().getSValue());
    }else{
      lu2v = Integer.parseInt(lu2.value.getSValue());
    }

    int resv = calc(lu1v, lu2v, (String)lu3.value.getSValue());

    while(!result.isEmpty()){
      LexicalUnit unit1 = result.poll();
      LexicalUnit unit2 = result.poll();
      int v1 = 0;
      if(unit1.getType() == LexicalType.NAME){
        v1 = Integer.parseInt(super.env.getVariable(unit1.value.getSValue()).getValue().getSValue());
      }else{
        v1 = Integer.parseInt(unit1.value.getSValue());
      }

      resv = calc(resv, v1, (String)lu3.value.getSValue());
    }
    result = tmp;
    return new ValueImpl(String.valueOf(resv), ValueType.INTEGER);
  }

  private int calc(int a, int b, String ope){
    if(ope.equals("+")) {
      return a + b;
    }else if(ope.equals("-")){
      return a - b;
    }else if(ope.equals("*")){
      return a * b;
    }else if(ope.equals("/")){
      return a / b;
    }else if(ope.equals("%")){
      return a % b;
    }else return -1;
  }
}
