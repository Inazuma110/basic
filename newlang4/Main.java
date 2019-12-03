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
    Node		program;

    System.out.println("basic parser");
    // fin = new FileInputStream("./test.bas");
    lex = new LexicalAnalyzerImpl("test.bas");
    env = new Environment(lex);
    first = lex.get();

    if(Program.isFirst(first)){
    //   Node handler = Program.getHandler(first, env);
    //   handler.parse();
    //   System.out.println(program);
    // }else{
    //   System.out.println("syntax error");
    // }
    }

    // program = Program.isMatch(env, first);
    // if (program != null && program.Parse()) {
    //   System.out.println(program);
    //   System.out.println("value = " + program.getValue());
    // }
    // else System.out.println("syntax error");
  }
}
