public class Comp {
    private static Tokenizer t; 
    private Op o1;
    private Op o2; 
    private CompOp cOp; 


    public Comp() { 
        t = Singleton.instance();
    }

    public void parseComp() { 
        // Check first token being ( 
        if (t.getToken() != 20) { 
            System.out.println("ERROR: Expected '(' before comparison not received");
            System.exit(0);
        } 
        t.skipToken();
        // Next parse first op 
        o1 = new Op();
        o1.parseOp();

        // Now parse comp op 
        cOp = new CompOp(); 
        cOp.parseCompOp();
        // Now parse op2 
        o2 = new Op();
        o2.parseOp();

        // ensure closing parens
        if (t.getToken() != 21) { 
            System.out.println("ERROR: No closing parentheses on compare");
            System.exit(0);
        }
        // skip closing paren 
        t.skipToken();
    } 

}
