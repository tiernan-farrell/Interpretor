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

    public void prettyPrint() {
        System.out.print("(");
        o1.prettyPrint();
        cOp.prettyPrint();
        o2.prettyPrint();
        System.out.print(")");
    }

    public boolean evalComp() { 
        // First get val of ops
        int op1Val = o1.evalOp();
        int op2Val = o2.evalOp();
        // Find comparator 
        String comp = cOp.execCompOp();

        switch (comp) { 
            case "!=": 
                return op1Val != op2Val;
            case "==": 
                return op1Val == op2Val;
            case "<": 
                return op1Val < op2Val;
            case ">": 
                return op1Val > op2Val;
            case "<=": 
                return op1Val <= op2Val;
            case ">=": 
                return op1Val >= op2Val;
        }
        // If we reach here there is an error 
        System.out.println(comp + " is not valid");
        System.exit(0);
        return false;
    }

}
