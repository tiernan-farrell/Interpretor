public class Loop {
    private static Tokenizer t; 
    private Cond c; 
    private StmtSeq ss; 


    public Loop () { 
        t = Singleton.instance();
    }
    
    public void parseSt() { 
        // ensure while token 
        if (t.getToken() != 8) { 
            System.out.println("ERROR: Expected a 'while' token, recieved invalid input");
            System.exit(0);   
        }
        t.skipToken();
        // parse Cond
        c = new Cond(); 
        c.parseCond();
        // ensure loop token 
        if (t.getToken() != 9) { 
            System.out.println("ERROR: Expected a 'loop' token, recieved invalid input");
            System.exit(0);   
        }
        // parse ss
        ss = new StmtSeq();
        ss.parseStmtSeq();
        // ensure end
        if (t.getToken() != 3) { 
            System.out.println("ERROR: Expected a 'end' token after loop not receieved");
            System.exit(0);   
        }
        // skip 'end' token 
        t.skipToken();
        
        // Check for ; 
        if (t.getToken() != 12) { 
            System.out.println("ERROR: Missing semicolon on assignment");
            System.exit(0);
        } 
        // skip ;
        t.skipToken();



    }


    public void printSt() { 
        System.out.print("while ");
        c.prettyPrint();
        System.out.print(" loop\n");
        ss.prettyPrint();
        System.out.println("end");
    }

    public void execSt() {
        while (c.evalCond()) { 
            ss.execStmtSeq();
        }
    }
}
