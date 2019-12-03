package newlang4;
import java.util.*;

class ProgramNode extends Node{
  Node stmt_list;
  // Set<Node> s = new EnumSet<>();
  Set<LexicalType> s = new EnumSet<>();

  boolean isFirst(LexicalType type);
  // paese();
}
