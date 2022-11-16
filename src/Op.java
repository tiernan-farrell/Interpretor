public class Op {
    private static Tokenizer t; 
    private Id id; 
    private Exp e; 
    public Op () { 
        t = Singleton.instance();
    }

    public void parseOp (){ 
        // check for error first and quit 
        if (t.getToken() != 31 && t.getToken() != 32 && t.getToken() != 20) { 
            System.out.println("ERROR: Expected an int, id or parens for another expression, not received");
            System.exit(0);
        }
        // Case for int 
        if (t.getToken() == 31) {
            // parse int 
        }
        else if (t.getToken() == 32) { 
            // parse ID
        } else {
            // parse exp 
            t.skipToken();
            e = new Exp(); 
            e.parseExp();
            t.skipToken();
        }
    }
}
