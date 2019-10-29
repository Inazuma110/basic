package newlang3;
import newlang3.LexicalAnalyzer;

public class Main {
  public static void main(String args[]) {
    // LexicalAnalyzer analyzer = new LexicalAnalyzer("main.bas");
    String fname = "Main.bas";
    LexicalAnalyzer analyzer = new LexicalAnalyzerImpl(fname);

    while(true){
      try{
        LexicalUnit unit = analyzer.get();
      }catch(Exception e){
        e.printStackTrace();
        return;
      }
      System.out.println(unit);
      if(analyzer.getType() == LexicalType.EOF) break;
    }
  }
}
