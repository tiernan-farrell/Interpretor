public class Cond {
    private static Tokenizer t;
    private Comp com; 
    private Cond c1; 
    private Cond c2; 

    public Cond() { 
        t = Singleton.instance();
    }

    public void parseCond() { 
        // check first token options are: Comp - "(", Cond - "!" or "["
        int token = t.getToken();
        if (token != 20 && token != 15 && token != 16) { 
            System.out.println("ERROR: Condition syntax invalid");
            System.exit(0);   
        }
        t.skipToken();
        // if legal check alternative
        if (token == 20) { 
            // This case is <Comp>
            com = new Comp(); 
            com.parseComp();
        } else if (token == 15) { 
            // this is not cond
            c1 = new Cond();
            c1.parseCond();
        } else  { 
            // this is either <cond> && <cond> | <cond> or <cond>
            c1 = new Cond();
            c1.parseCond();
            t.skipToken();
            c2 = new Cond();
            c2.parseCond();
        }
    }
    

    public void prettyPrint() { 

    }

    
    public boolean evalCond() { 
        return true; 
    }
}
