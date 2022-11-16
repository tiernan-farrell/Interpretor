public class CompOp {
    private static Tokenizer t; 

    public CompOp() { 
        t = Singleton.instance();
    }

    public void parseCompOp (){ 
        if (t.getToken() != 25 && t.getToken() != 26 && t.getToken() != 27 && t.getToken() != 28 && t.getToken() != 29 && t.getToken() != 30) { 
            System.out.println("ERROR: invalid comparator operator provided");
            System.exit(0);
        }
        t.skipToken();
    }
}
