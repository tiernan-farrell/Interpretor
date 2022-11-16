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
            int val = t.intVal();
            t.skipToken();
        }
        else if (t.getToken() == 32) { 
            // parse ID
            id = Id.parseId();
            t.skipToken();
        } else {
            // skip openign paren 
            t.skipToken();
            // parse exp 
            e = new Exp(); 
            e.parseExp();

            // check for closing paren 
            if (t.getToken() != 21) { 
                System.out.println("ERROR: Missing closing parens on expression");
                System.exit(0);
            } 
            // skip closing paren
            t.skipToken();
        }
    }
}
