package newlang4;

public class Node {
  NodeType type;
  Environment env;
  Node handler;
  LexicalUnit first;
  /** Creates a new instance of Node */
  public Node() {
  }
  public Node(LexicalUnit first, Environment env) {
    this.first = first;
    this.env = env;
  }
  public Node(NodeType my_type) {
    type = my_type;
  }
  public Node(Environment my_env) {
    env = my_env;
  }
  public NodeType getType() {
    return type;
  }
  public boolean parse() throws Exception {
    return true;
  }
  public Value getValue() throws Exception {
    return null;
  }
  public String toString() {
    if (type == NodeType.END) return "END";
    else return "Node";
  }

  public Value eval(){
    return null;
  }
  public void setValue(Value my_v){
  }
}
