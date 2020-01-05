package newlang4;

import java.io.FileInputStream;

public class Main {
  /**
   * @param args
   */
  public static void main(String[] args) throws Exception {
    FileInputStream fin = null;
    LexicalAnalyzer lex;
    LexicalUnit	first;
    Environment	env;
    Node		handler = null;

    System.out.println("basic parser");
    // fin = new FileInputStream("./test.bas");
    lex = new LexicalAnalyzerImpl("test.bas");
    env = new Environment(lex);
    first = env.getInput().get();
    // first = lex.get();
    lex.unget(first);
    // while(first.getType() != LexicalType.EOF) System.out.println(first = env.getInput().get());

    if(Program.isFirst(first)){
      handler = Program.getHandler(first, env);
      if(!handler.parse()) System.out.println("error");
      System.out.println(handler);
    }else{
      System.out.println("syntax error");
    }
  }
}
