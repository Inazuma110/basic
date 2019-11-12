package newlang3;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.PushbackReader;
import java.util.HashMap;
import java.util.Map;

public class LexicalAnalyzerImpl implements LexicalAnalyzer{
  FileReader fr;
  PushbackReader reader;
  File file;
  private final Map<String, LexicalType> symbolMap = new HashMap<String, LexicalType>(){
    {
      put("=", LexicalType.EQ);
      put("<", LexicalType.LT);
      put(">", LexicalType.GT);
      put("<=", LexicalType.LE);
      put(">=", LexicalType.GT);
      put("!=", LexicalType.NE);
      put(".", LexicalType.DOT);
      put("+", LexicalType.ADD);
      put("-", LexicalType.SUB);
      put("/", LexicalType.DIV);
      put("*", LexicalType.MUL);
      put(",", LexicalType.COMMA);
    }
  };

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
      ci = reader.read();
    }catch(Exception e){
      e.printStackTrace();
    }
    char c = (char)ci;

    if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
      return stringGet(c);
    }
    if(c >= '0' && c <= '9'){
      return intGet(c);
    }
    if(c == '"'){
      return literalGet(c);
    }

    return specialGet(c);
  }

  private LexicalUnit intGet(char c1){
    String target = "";
    target += c1;

    while(true){
      int ci = 0;
      try{
        ci = reader.read();
      }catch(Exception e){
        e.printStackTrace();
      }
      if(ci < 0) break;
      char c = (char) ci;
      if(c >= '0' && c <= '9'){
        target += c;
        continue;
      }
      if(c == '.'){
        target += '.';
        return doubleGet(target);
      }

      if(ci > 0){
        try{
          reader.unread(ci);
        }catch(Exception e){
          e.printStackTrace();
        }
      }
      break;
    }
    Value int_val = new ValueImpl(target, ValueType.INTEGER);
    LexicalUnit unit = new LexicalUnit(LexicalType.INTVAL, int_val);
    return unit;
  }

  private LexicalUnit doubleGet(String target){
    while(true){
      int ci = 0;
      try{
        ci = reader.read();
      }catch(Exception e){
        e.printStackTrace();
      }
      if(ci < 0) break;
      char c = (char) ci;
      if(c >= '0' && c <= '9'){
        target += c;
        continue;
      }

      if(ci > 0){
        try{
          reader.unread(ci);
        }catch(Exception e){
          e.printStackTrace();
        }
      }
      break;
    }
    Value double_val = new ValueImpl(target, ValueType.DOUBLE);
    LexicalUnit unit = new LexicalUnit(LexicalType.DOUBLEVAL, double_val);
    return unit;
  }

  private LexicalUnit stringGet(char c1){
    String target = "";
    target += c1;
    while(true){
      int ci = 0;
      try{
        ci = reader.read();
      }catch(Exception e){
        e.printStackTrace();
      }
      if(ci < 0) break;
      char c = (char) ci;
      if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
        target += c;
        continue;
      }
      if(ci > 0){
        try{
          reader.unread(ci);
        }catch(Exception e){
          e.printStackTrace();
        }
      }
      break;
    }
    // 予約語の判定
    Value val = new ValueImpl(target, ValueType.STRING);
    LexicalUnit unit;
    try{
      unit = new LexicalUnit(LexicalType.valueOf(target));
    }catch(Exception e){
      unit = new LexicalUnit(LexicalType.NAME, val);
    }
    return unit;
  }

  public LexicalUnit literalGet(char c1){
    String target = "";
    while(true){
      int ci = 0;
      try{
        ci = reader.read();
      }catch(Exception e){
        e.printStackTrace();
      }
      if(ci < 0) break;
      char c = (char) ci;
      if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
        target += c;
        continue;
      }
      if(c == '"') break;
      if(ci > 0){
        try{
          reader.unread(ci);
        }catch(Exception e){
          e.printStackTrace();
        }
      }
      break;
    }
    Value val = new ValueImpl(target, ValueType.STRING);
    LexicalUnit unit = new LexicalUnit(LexicalType.LITERAL, val);
    return unit;
  }

  private LexicalUnit specialGet(char c1){
    if(c1 == '\n') return new LexicalUnit(LexicalType.NL);
    if(c1 == ' ' || c1 == '\t') return new LexicalUnit(LexicalType.SPACE);

    String target = "";
    target += c1;
    while(true){
      int ci = 0;
      try{
        ci = reader.read();
      }catch(Exception e){
        e.printStackTrace();
      }
      if(ci < 0) break;
      char c = (char) ci;
      if(c == ' ' || c == '\n' || c == '\t') {
        try{
          reader.unread(ci);
        }catch(Exception e){
          e.printStackTrace();
        }
        break;
      }
      target += c;
    }
    if(symbolMap.get(target) != null) return new LexicalUnit(symbolMap.get(target));
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
