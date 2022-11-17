public abstract class Stmt { 
    private Assign sAssign;
    private If sIf;
    private Loop sLoop;
    private OutP sOut;
    private In sIn;
    private static Tokenizer t; 

    public Stmt() { 
        t = Singleton.instance();
    }

    abstract void printSt();
    abstract void execSt();
    abstract void parseSt();


    // public void parseStmt() {  
    //     switch (t.getToken()) { 
    //         // Assign 
    //         case 32: 
    //             sAssign = new Assign();
    //             sAssign.parseSt();
    //             break;
    //         // If 
    //         case 5: 
    //             sIf = new If();
    //             sIf.parseSt();
    //             break;
    //         // Loop 
    //         case 9: 
    //             sLoop = new Loop();
    //             sLoop.parseSt();
    //             break;
    //         // out
    //         case 11: 
    //             sOut = new OutP();
    //             sOut.parseSt();
    //             break;
    //         // Out 
    //         case 10: 
    //             sIn = new In();
    //             sIn.parseSt();
    //             break;
    //     }
    // }
}
