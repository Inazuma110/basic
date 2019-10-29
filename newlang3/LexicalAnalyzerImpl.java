package newlang3;

public class LexicalAnalyzerImpl implements LexicalAnalyzer{
  private FileReader fr;
  public LexicalAnalyzerImpl(String fname){
    try{
      this.fr = new FileReader(fname);
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }

  }

  @Override
  public LexicalUnit get() throws Exception{
    char c = (char)fr.read();
    while(c == ' ' || c == '\t'){
      c = (char)fr.read();
    }

    if(c >= '0' && c <= '9'){
      return intGet(c);
    }
    if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
      return stringGet(c);
    }
    if(c == '"'){
      return literalGet();
    }

    return specialGet();
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
  }
}
