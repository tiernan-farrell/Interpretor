public class Fac {
    private static Tokenizer t; 
    private Op o; 
    private Fac f; 


    public Fac() { 
        t = Singleton.instance();
    }

    public void parseFac() { 
        // first parse Fac Errors thrown down to OP 
        o = new Op();
        o.parseOp();

        // Check for multiplication 
        if (t.getToken() == 24) {
            f = new Fac();
            f.parseFac();
            t.skipToken();
        }
    }

    public void prettyPrint() { 
        o.prettyPrint();
        if (f != null) { 
            System.out.print(" * ");
            f.prettyPrint();
        }
    }

    public int evalFac() { 
        // First eval op 
        int res = o.evalOp();
        // Nect check for * 
        if (f != null) { 
            res *= f.evalFac();
        }
        return res; 

    }

}
