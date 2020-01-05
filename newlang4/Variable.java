package newlang4;
public class Variable extends Node {
  String var_name;
  Value v;

  /** Creates a new instance of variable */
  public Variable(String name) {
    var_name = name;
  }
  public Variable(LexicalUnit u) {
    var_name = u.getValue().getSValue();
  }

  public static boolean isFirst(LexicalUnit first) {
    if (first.getType() == LexicalType.NAME) return true;
    else return false;
  }

  public static Node getHandler(LexicalUnit unit, Environment env){
    return new Variable(unit.value.getSValue());
  }

  public void setValue(Value my_v) {
    v = my_v;
  }

  public Value getValue() {
    return v;
  }

  @Override
  public String toString() {
    return var_name;
  }

}
