package newlang4;

public interface Value {
  //    public Value(String s);
  //    public Value(int i);
  //    public Value(double d);
  //    public Value(boolean b);
  //    public String get_sValue();
  //    必要があれば型変換
  public String getSValue();
  public int getIValue();
  public double getDValue();
  public boolean getBValue();
  public ValueType getType();
}
