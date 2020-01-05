package newlang4;

public enum LexicalType {
  // 文字列
  LITERAL,
  // 数値
  INTVAL,
  DOUBLEVAL,
  // 変数名
  NAME,
  IF,
  THEN,
  ELSE,
  ELSEIF,
  ENDIF,
  FOR,
  FORALL,
  NEXT,
  EQ,
  LT,
  GT,
  LE,
  GE,
  NE,
  FUNC,
  //
  DIM,
  //
  AS,
  END,
  // new line
  NL,
  DOT,
  DO,
  WHILE,
  UNTIL,
  ADD,
  SUB,
  MUL,
  DIV,
  LP,
  RP,
  COMMA,
  LOOP,
  TO,
  WEND,
  EOF,
  SPACE
}
