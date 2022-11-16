public class DeclSeq {
    private static Tokenizer t; 
    private Decl dec; 
    private DeclSeq seq;
    
    public DeclSeq() { 
        t = Singleton.instance();
    }

    
    public void test() { 
        System.out.println("dec: " + t.getToken());
        t.skipToken();
    }

    public void parseDeclSeq() { 
        // init decl and parse that 
        dec = new Decl();
        dec.parseDecl();
        // After skipping token we need to see if there is another declSeq to parse
        if (t.getToken() == 4) { 
            seq = new DeclSeq();
            seq.parseDeclSeq();
        }
    }

    public void prettyPrint() { 
        dec.prettyPrint();
    }

    public void execDecSeq() { 
        
    }

}
