package newlang4;
import java.util.*;

public class ExprNode extends Node{
  List<Node> operand = new ArrayList<Node>();
  List<LexicalUnit> operate = new ArrayList<LexicalUnit>();

  static final Set<LexicalType> firstSet = EnumSet.of(
      LexicalType.NAME, LexicalType.INTVAL,
      LexicalType.DOUBLEVAL, LexicalType.LITERAL,
      LexicalType.LP,
      LexicalType.SUB, LexicalType.MUL,
      LexicalType.DIV, LexicalType.ADD
      );

  static Map<String, Integer> priorityMap = new HashMap<String, Integer>(){
    {
      put("*", 3);
      put("/", 3);
      put("+", 2);
      put("-", 2);
      put("=", 1);
    }
  };

  static Map<LexicalType, String> opeMap = new HashMap<LexicalType, String>(){{
    put(LexicalType.ADD, "+");
    put(LexicalType.SUB, "-");
    put(LexicalType.MUL, "*");
    put(LexicalType.DIV, "/");
  }};

  Environment env;
  LexicalUnit first;

  private ExprNode(Environment env) {
    this.env = env;
    this.first = first;
  }

  private ExprNode(Node expr1, LexicalUnit ope, Node expr2, Environment env){
    super(env);
    type = NodeType.EXPR;
    operand.add(expr1);
    operate.add(ope);
    operand.add(expr2);
  }

  public static Node getHandler(LexicalUnit unit, Environment env){
    return new ExprNode(env);
  }

  public static boolean isFirst(LexicalUnit unit){
    return firstSet.contains(unit.getType());
  }

  // @Override
  public boolean parse() throws Exception{
    LexicalUnit unit;
    Node handler = null;

    int i = 0;
    while(true){
      return false;
      // unit = env.getInput().get();
      // if(i % 2 == 0){
        // if(unit.getType() == LexicalType.LP){
    //       unit = env.getInput().get();
    //       if(ExprNode.isFirst(unit)){
    //         handler = ExprNode.getHandler(unit, env);
    //         env.getInput().unget(unit);
    //       }else{
    //         System.out.println("Error");
    //         return false;
    //       }
    //
    //       if(!handler.parse()){
    //         System.out.println("Error");
    //         return false;
    //       }
    //
    //     }else if(unit.getType() == LexicalType.NAME){
    //       LexicalUnit unit2 = env.getInput().get();
    //       env.unget(unit2);
    //       env.unget(unit1);
    //       if(unit2.getType() == LexicalType.LP) {
    //         // call func
    //       }else{
    //
    //       }
    //     }else if(unit.getType() == LexicalType.LITERAL){
    //       env.getInput().unget(unit);
    //     }else if(unit.getType() == LexicalType.INTVAL){
    //       env.getInput().unget(unit);
    //       if(Int.isFirst(unit)) {
    //         handler = Int.getHandler(unit, env);
    //       }
    //     }
    //     operand.add(handler);
    //   }else{
    //     if(ope.containsKey(unit.getType())) operators.add(unit);
    //     else{
    //       if(unit.getType() == LexicalType.NL) env.getInput().unget(unit);
    //       break;
    //     }
    //   }
      // i++;
    }
  }

  @Override
  public String toString() {
    return "aa";
    // return first.getValue();
  }
}
