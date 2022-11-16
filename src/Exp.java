public class Exp {
    public static Tokenizer t; 
    private Fac f; 
    private Exp e; 


    public Exp() { 
        t = Singleton.instance();
    }
    
    public void parseExp() { 
        // First parse the first factor 
        f = new Fac();
        f.parseFac();
        t.skipToken();
        // Now check for + or - 
        if (t.getToken() == 22 || t.getToken() == 23) { 
            e = new Exp();
            e.parseExp();
            t.skipToken();
        }
    }

    public int evalExp() { 

        return 0;
    }    

    public void prettyPrint() {

    }
}
