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
    // switch (t) {
    //   case INTEGER:
    //     intVal = (int) sVal;
    //     break;
    //   case DOUBLE:
    //     doubleVal = (double) sVal;
    //     break;
    //   case BOOL:
    //     boolVal = (boolean) sVal;
    //     break;
    // }
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
