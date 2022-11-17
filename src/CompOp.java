public class CompOp {
    private static Tokenizer t; 
    private String op; 

    public CompOp() { 
        t = Singleton.instance();
    }

    public void parseCompOp (){ 
        if (t.getToken() != 25 && t.getToken() != 26 && t.getToken() != 27 && t.getToken() != 28 && t.getToken() != 29 && t.getToken() != 30) { 
            System.out.println("ERROR: invalid comparator operator provided");
            System.exit(0);
        }
        switch (t.getToken()) { 
            case 25: 
                op = "!=";
                break;
            case 26: 
                op = "==";
                break;
            case 27: 
                op = "<";
                break;
            case 28: 
                op = ">";
                break;
            case 29: 
                op = "<=";
                break;
            case 30: 
                op = ">=";
                break;
            
        }
        t.skipToken();
    }

    public void prettyPrint() { 
        System.out.print(" " + op + " "); 
    }

    public String execCompOp() { 
        return op; 
    }
}
