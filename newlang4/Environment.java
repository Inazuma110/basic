package newlang4;
import java.util.*;

public class Environment {
  LexicalAnalyzer input;
  Hashtable<String, Variable> var_table;
  Map<String, Function> library;

  public Environment(LexicalAnalyzer my_input) {
    input = my_input;
    var_table = new Hashtable<>();
    library = new HashMap<>();
    library.put("PRINT", new PrintFunction() );
  }
  public LexicalAnalyzer getInput() {
    return input;
  }
  public Function getFunction(String fname) {
    return (Function) library.get(fname);
  }

  public Variable getVariable(String vname) {
    Variable v;
    v = (Variable) var_table.get(vname);
    if (v == null) {
      v = new Variable(vname);
      var_table.put(vname, v);
    }
    return v;
  }
}
