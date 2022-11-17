public class Decl {
    private static Tokenizer t; 
    private IdList idList; 

    public Decl() { 
        t = Singleton.instance();
    }

    public void test() { 
        System.out.println("decl: " + t.getToken());
        t.skipToken();
    }

    public void parseDecl() { 
        // Ensure int is present frst 
        if (t.getToken() != 4)  {
            System.out.println("ERROR: declaration invalid 'int' not present before declaration sequence");
            System.exit(0);
        }
        t.skipToken();
        // Create ID list node and parse that 
        idList = new IdList();
        idList.parseDeclIdList();
        // ensure tehre is a semicolon after the idList
        if (t.getToken() != 12)  {
            System.out.println("ERROR: expected semicolon after id list not present");
            System.exit(0);
        }
        // skip past semicolon 
        t.skipToken();
    }

    public void prettyPrint() { 
        System.out.print("int ");
        idList.prettyPrint();
    }

    public void execDec() {
        
    }
}

