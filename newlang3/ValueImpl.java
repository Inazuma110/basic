package newlang3;

public class ValueImpl implements Value{
  private String sVal;
  private int intVal;
  private double doubleVal;
  private boolean boolVal;
  private ValueType type;


  public ValueImpl(String s, ValueType t){
    // super(s, t);
    type = t;
    this.sVal = s;
    switch (t) {
      case ValueType.INTEGER:
        intVal = (int) s;
        break;
      case ValueType.DOUBLE:
        doubleVal = (double) s;
        break;
      case ValueType.BOOL:
        boolVal = (boolean) s;
        break;
    }
  }

  @Override
  public double getDValue(){
    return doubleVal;
  }

  @Override
  public boolean getBValue(){
    return boolVal;
  }

  public ValueType getType(){
    return this.type;
  }

  @Override
  public String getSValue(){
    return sVal;
  }

  @Override
  public int getIValue(){
    return intVal;
  }
}
