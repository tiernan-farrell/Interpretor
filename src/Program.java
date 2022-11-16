public class Program {
    private static Tokenizer t; 
    private DeclSeq decseq; 
    private StmtSeq stmtseq;

    public Program() { 
        t = Singleton.instance();
    }
    
    public void test() { 
        System.out.println("Prog: " + t.getToken());
        t.skipToken();
    }

    public void parseProgram() { 
        // check for program token first 
        if (t.getToken() != 1) { 
            System.out.println("ERROR: First token is not 'program'");
            System.exit(0);
        }
        t.skipToken();
        //parse the decl seq
        decseq = new DeclSeq();
        decseq.parseDeclSeq();
        // // check for begin token 
        if (t.getToken() != 2) { 
            System.out.println("ERROR: invalid syntax, missing 'begin' keyword");
            System.exit(0);
        }
        // parse stmt seq
        stmtseq = new StmtSeq();
        stmtseq.parseStmtSeq();
        // // check for end token 
        if (t.getToken() != 3) { 
            System.out.println("ERROR: invalid syntax, missing 'end' keyword");
            System.exit(0);
        }
    }


    public void prettyPrint() { 
        // If we made it to the printing we assume that the tokens are valid 
        // First print program 
        System.out.println("program ");

        // print decl seq
        decseq.prettyPrint();
        // print begin  
        System.out.println("begin");

        // print stmt seq
        stmtseq.prettyPrint();
        // print end 
        System.out.println("end");
    }

    public void execProg(){ 
        // exec stmt seq
        decseq.execDecSeq();
        stmtseq.execStmtSeq();
    }
}
