package newlang3;

public class ValueImpl implements Value{
  private String sVal;

  public ValueImpl(String s, ValueType t){
    // super(s, t);
    this.sVal = s;

  }

  @Override
  public double getDValue(){

    return 0.0;
  }

  @Override
  public boolean getBValue(){
    return false;
  }

  public ValueType getType(){
    return null;
  }

  @Override
  public String getSValue(){
    return sVal;
  }

  @Override
  public int getIValue(){
    return -1;

  }
}
