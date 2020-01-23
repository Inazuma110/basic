package newlang4;
import java.util.*;

public class PrintFunction extends Function {
  public PrintFunction() {

  }

  @Override
  public Value eval(List<Node> arg) {
    for(Node e : arg){
      System.out.println(e);
    }
    return null;
  }
}
