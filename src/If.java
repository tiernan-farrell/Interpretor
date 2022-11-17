public class If {
    private static Tokenizer t; 
    private Cond c;
    private StmtSeq ss1; 
    private StmtSeq ss2; 
    

    public If () { 
        t = Singleton.instance();
    }
    
    public void parseSt() { 
        // Ensure if is first token 
        if (t.getToken() != 5) { 
            System.out.println("ERROR: expected 'if' token not received");
            System.exit(0);
        }
        t.skipToken();
        // parse the condition 
        c = new Cond();
        c.parseCond();
        // check for then 
        if (t.getToken() != 6) { 
            System.out.println("ERROR: expected 'then' token not received");
            System.exit(0);
        }
        // skip 'then' token 
        t.skipToken();
        // parse SS1
        ss1 = new StmtSeq();
        ss1.parseStmtSeq();
        // Check for else 
        if (t.getToken() == 7) { 
            t.skipToken();
            ss2 = new StmtSeq();
            ss2.parseStmtSeq();
        }

        // check for end 
        if (t.getToken() != 3) { 
            System.out.println("ERROR: expected 'end' token not received");
            System.exit(0);
        }
        // skip 'end' token 
        t.skipToken();
        if (t.getToken() != 12) { 
            System.out.println("ERROR: Missing semicolon on end of if statement");
            System.exit(0);
        } 
        // skip semicolon token 
        t.skipToken();
    }

    public void printSt() { 
        System.out.print("if ");
        c.prettyPrint();
        System.out.print(" then\n");
        StmtSeq.tabs += "\t";
        ss1.prettyPrint();
        // Check for else 
        StmtSeq.tabs = StmtSeq.tabs.substring(0, StmtSeq.tabs.length()-1);
        if (ss2 != null) { 
            System.out.print("\n" + StmtSeq.tabs + "else\n");
            StmtSeq.tabs += "\t";
            ss2.prettyPrint();
            StmtSeq.tabs = StmtSeq.tabs.substring(0, StmtSeq.tabs.length()-1);

        }            

        System.out.println("\n" + StmtSeq.tabs + "end;");
    }

    public void execSt() {
        if (c.evalCond()) { 
            ss1.execStmtSeq();
            return;
        }
        if (ss2 != null) { 
            ss2.execStmtSeq();
        }
    }


}
