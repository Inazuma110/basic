# メモ
to_stringを作って子供が全部表示する(親は子に投げる)

Program
StmtList
Subst: 代入文 stmtの一つ
変数 = 式

CallSub: 関数呼び出し
関数名(式, ...)

Block:
IfBlock
IF 条件式 THEN
StmtList 下請け
ELSE
StmtList 下請け
ENDIF

LoopBlock
DO UNTIL Cond
stmtList 下請け
LOOP

Expr: 式
Cond: 条件式
