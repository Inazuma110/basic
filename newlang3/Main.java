package newlang3;
import newlang3.LexicalAnalyzer;

public class Main {
  public static void main(String args[]) {
    String fname = "Main.bas";
    if(args.length > 0) fname = args[0];
    LexicalAnalyzer analyzer = new LexicalAnalyzerImpl(fname);

    while(true){
      LexicalUnit unit;
      try{
        unit = analyzer.get();
      }catch(Exception e){
        e.printStackTrace();
        return;
      }
      System.out.println(unit);
      if(unit.getType() == LexicalType.EOF) break;
    }
  }
}
