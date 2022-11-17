public class StmtSeq {
    private static Tokenizer t; 
    private Assign sAssign;
    private If sIf;
    private Loop sLoop;
    private OutP sOut;
    private In sIn;
    private StmtSeq ss; 
    public static String tabs = "\t\t";

    public StmtSeq() { 
        t = Singleton.instance();
    }

    
    public void test() { 
        System.out.println("stmt: " + t.getToken());
        t.skipToken();
    }

    public void parseStmtSeq() { 
        // Create stmt and parse stmt
        switch (t.getToken()) { 
            // Assign 
            case 32: 
                sAssign = new Assign();
                sAssign.parseSt();
                break;
            // If 
            case 5: 
                sIf = new If();
                sIf.parseSt();
                break;
            // Loop 
            case 8: 
                sLoop = new Loop();
                sLoop.parseSt();
                break;
            // out
            case 11: 
                sOut = new OutP();
                sOut.parseSt();
                break;
            // Out 
            case 10: 
                sIn = new In();
                sIn.parseSt();
                break;
        }
        // Check if end and else is next token if not keep parsing stmts
        if (t.getToken() != 3 && t.getToken() != 7) { 
            ss = new StmtSeq();
            ss.parseStmtSeq();
        }
    }

    public void prettyPrint() { 
        if (sAssign != null) { 
            System.out.print(tabs);
            sAssign.printSt();
        }
        else if (sIf != null) { 
            System.out.print(tabs);
            sIf.printSt();
        }
        else if (sLoop != null) { 
            System.out.print(tabs);            
            sLoop.printSt();
        }
        else if (sOut != null) { 
            System.out.print(tabs);
            sOut.printSt();
        }
        else if (sIn != null) { 
            System.out.print(tabs);
            sIn.printSt();
        }
        if (ss != null) { 
            System.out.println();
            ss.prettyPrint();
        }
    }

    public void execStmtSeq() { 
        if (sAssign != null) { 
            sAssign.execSt();
        }
        else if (sIf != null) { 
            sIf.execSt();
        }
        else if (sLoop != null) { 
            sLoop.execSt();
        }
        else if (sOut != null) { 
            sOut.execSt();
        }
        else if (sIn != null) { 
            sIn.execSt();
        }
        if (ss != null) { 
            ss.execStmtSeq();
        }
    }
}
