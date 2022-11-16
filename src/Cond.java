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

        // if legal check alternative
        if (token == 20) { 
            // This case is <Comp>
            com = new Comp(); 
            com.parseComp();
        } else if (token == 15) { 
            // this is not cond
            // skip ! token 
            t.skipToken();
            // parse cond
            c1 = new Cond();
            c1.parseCond();
        } else  { 
            // this is either <cond> && <cond> | <cond> or <cond>
            // skip [ token 
            t.skipToken();
            // parse first cond
            c1 = new Cond();
            c1.parseCond();
            // check that next token is either && or 'or'
            if (t.getToken()!= 18 && t.getToken() != 19) { 
                System.out.println("ERROR: expected '&&' or '||' between conditions but did not receive it");
                System.exit(0);   
            }
            t.skipToken();
            // parse second condition
            c2 = new Cond();
            c2.parseCond();
            // ensure the ending ] is there 
            if (t.getToken() != 17) { 
                System.out.println("ERROR: Missing ']' on condition");
                System.exit(0);
            } 
            // skip ]
            t.skipToken();
        }
    }
    

    public void prettyPrint() { 
        
    }

    
    public boolean evalCond() { 
        return true; 
    }
}
