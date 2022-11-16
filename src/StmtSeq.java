public class StmtSeq {
    private static Tokenizer t; 
    private static Stmt s; 
    private static StmtSeq ss; 

    public StmtSeq() { 
        t = Singleton.instance();
    }

    
    public void test() { 
        System.out.println("stmt: " + t.getToken());
        t.skipToken();
    }

    public void parseStmtSeq() { 
        // Create stmt and parse stmt
        s.parseStmt();
        // Check if end is next token if not keep parsing stmts
        if (t.getToken() != 3) { 
            ss = new StmtSeq();
            ss.parseStmtSeq();
        }
    }

    public void prettyPrint() { 
        
    }

    public void execStmtSeq() { 
        s.execSt();
        if (ss != null) { 
            ss.execStmtSeq();
        }
    }
}
