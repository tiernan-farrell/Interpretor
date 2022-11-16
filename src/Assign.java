public class Assign extends Stmt{
    private static Tokenizer t; 
    Id id; 
    Exp exp;

    public Assign () { 
        t = Singleton.instance();
    }

    public void printSt() { 
        id.prettyPrint();
        System.out.print(" = ");
        exp.prettyPrint();
    }

    public void execSt() {
        id.assignValue(exp.evalExp());
    }

    public void parseSt() { 
        id = Id.parseId();
        t.skipToken();
        // ensure = token 
        if (t.getToken() != 14) {
            System.out.println("ERROR: Expected an '=' between assignment did not receive");
            System.exit(0);
        } 
        t.skipToken();
        exp = new Exp();
        exp.parseExp();
        // Ensure there is a semicolon 
        if (t.getToken() != 12) { 
            System.out.println("ERROR: Missing semicolon on assignment");
            System.exit(0);
        } 
        // skip past semicolon token 
        t.skipToken();
    }
}
