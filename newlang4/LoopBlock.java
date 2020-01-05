package newlang4;
import java.util.*;
import java.*;

public class LoopBlock extends Node{

  static final Set<LexicalType> firstSet = EnumSet.of(
      LexicalType.DO, LexicalType.WHILE
      );

  LexicalUnit whileOrDo;
  LexicalUnit whileOrUntil;
  Node cond1;
  Node cond2;
  LexicalUnit ope;
  Node stmtList;

  public LoopBlock(LexicalUnit unit, Environment env) {
    super(unit, env);
  }

  public static Node getHandler(LexicalUnit unit, Environment env){
    return new LoopBlock(unit, env);
  }

  public static boolean isFirst(LexicalUnit unit){
    return firstSet.contains(unit.getType());
  }

  // @Override
  public boolean parse() throws Exception{
    System.out.println("Loop Block");
    whileOrDo = super.first;
    if(whileOrDo.getType() == LexicalType.DO){
      whileOrUntil = super.env.getInput().get();
      LexicalUnit f1 = super.env.getInput().get();
      super.env.getInput().unget(f1);
      if(ExprNode.isFirst(f1)){
        cond1 = ExprNode.getHandler(f1, super.env);
        if(!cond1.parse()) return false;
      }else return false;

      ope = super.env.getInput().get();
      if(!(ope.getType() == LexicalType.EQ || ope.getType() == LexicalType.GT ||
            ope.getType() == LexicalType.LT || ope.getType() == LexicalType.GE ||
            ope.getType() == LexicalType.LE || ope.getType() == LexicalType.NE))
        return false;

      LexicalUnit f2 = super.env.getInput().get();
      super.env.getInput().unget(f2);
      if(ExprNode.isFirst(f2)){
        cond2 = ExprNode.getHandler(super.first, super.env);
        if(!cond2.parse()) return false;
      }else return false;

      if(super.env.getInput().get().getType() != LexicalType.NL) return false;

      LexicalUnit stmtListUnit = super.env.getInput().get();
      if(StmtList.isFirst(stmtListUnit)){
        super.env.getInput().unget(stmtListUnit);
        stmtList = StmtList.getHandler(stmtListUnit, super.env);
        if(!stmtList.parse()) return false;
      }else return false;

      if(super.env.getInput().get().getType() != LexicalType.LOOP) return false;
      if(super.env.getInput().get().getType() != LexicalType.NL) return false;
      // System.out.println(111);
      // System.out.println(super.env.getInput().get().getType());
      return true;

    }

    return true;
  }

  @Override
  public String toString() {
    return "LOOP " + whileOrDo.toString() + "[" +
      "[" + cond1.toString() + " " + ope + " " + cond2.toString() + "]" +
      stmtList.toString() + "]";
  // whileOrUntil;
  }
}
