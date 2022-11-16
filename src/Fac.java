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
        t.skipToken();
        // Check for multiplication 
        if (t.getToken() == 24) {
            f = new Fac();
            f.parseFac();
            t.skipToken();
        }
    }

}
