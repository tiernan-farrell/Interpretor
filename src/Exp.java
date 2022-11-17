public class Exp {
    public static Tokenizer t; 
    private Fac f; 
    private Exp e; 
    private String op;

    public Exp() { 
        t = Singleton.instance();
    }
    
    public void parseExp() { 
        // First parse the first factor 
        f = new Fac();
        f.parseFac();
        // Now check for + or - 
        if (t.getToken() == 22 || t.getToken() == 23) {
            if (t.getToken() == 22) { 
                op = "+";
            } else {
                op = "-";
            }
            t.skipToken();
            e = new Exp();
            e.parseExp();
        }
    }

    public int evalExp() { 
        // first eval fac 
        int res = f.evalFac();
        // Check for + -
        if (op != null) {
            if (op == "+") { 
                res += e.evalExp();
            } else { 
                res -= e.evalExp();
            }
        }

        return res;
    }    

    public void prettyPrint() {
        f.prettyPrint();
        if (e != null) { 
            System.out.print(" " + op + " ");
            e.prettyPrint();
        }
    }
}
