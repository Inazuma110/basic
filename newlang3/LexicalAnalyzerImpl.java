package newlang3;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.PushbackReader;

public class LexicalAnalyzerImpl implements LexicalAnalyzer{
  FileReader fr;
  PushbackReader reader;
  File file;
  public LexicalAnalyzerImpl(String fname){
    try{
      this.file = new File(fname);
      this.fr = new FileReader(fname);
      this.reader = new PushbackReader(fr);
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }
  }

  @Override
  public LexicalUnit get() throws Exception{
    int ci = 0;
    try{
      ci = fr.read();
    }catch(Exception e){
      e.printStackTrace();
    }
    char c = (char)ci;
    while(c == ' ' || c == '\t'){
      ci = fr.read();
      c = (char)ci;
      if(ci < 0) return new LexicalUnit(LexicalType.EOF);
    }
    if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
      return stringGet(c);
    }

    // if(c >= '0' && c <= '9'){
    //   return intGet(c);
    // }
    // if(c == '"'){
    //   return literalGet();
    // }
    //
    return specialGet();
  }

  private LexicalUnit intGget(char c1){
    String target = "";
    target += c1;

    while(true){
      int ci = 0;
      try{
        ci = fr.read();
      }catch(Exception e){
        e.printStackTrace();
      }
      if(ci < 0) break;
      char c = (char) ci;
      if(c >= '0' && c <= '9'){
        target += c;
        continue;
      }
      try{
        reader.unread(ci);
      }catch(Exception e){
        e.printStackTrace();
      }
      break;
    }
    System.out.println(target);
    // TODO 予約語かの判定
    Value int_val = new ValueImpl(target, ValueType.INTEGER);
    LexicalUnit unit = new LexicalUnit(LexicalType.INTVAL, int_val);
    return unit;
  }

  private LexicalUnit stringGet(char c1){
    String target = "";
    target += c1;
    while(true){
      int ci = 0;
      try{
        ci = fr.read();
      }catch(Exception e){
        e.printStackTrace();
      }
      if(ci < 0) break;
      char c = (char) ci;
      if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
        target += c;
        continue;
      }
      try{
        reader.unread(ci);
      }catch(Exception e){
        e.printStackTrace();
      }
      break;
    }
    // TODO 予約語かの判定
    Value val = new ValueImpl(target, ValueType.STRING);
    LexicalUnit unit = new LexicalUnit(LexicalType.NAME, val);
    return unit;
  }

  private LexicalUnit specialGet(){
    return new LexicalUnit(LexicalType.EOF);
  }


  @Override
  public boolean except(LexicalType type) throws Exception{
    return false;
  }

  @Override
  public void unget(LexicalUnit token) throws Exception{
  }

  @Override
  public String toString(){
    return null;
  }

}
