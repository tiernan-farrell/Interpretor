public class In {
    private static Tokenizer t; 
    private IdList list;


    public In () { 
        t = Singleton.instance();
    }

    public void parseSt() { 
        // ensure read token 
        if (t.getToken() != 10) { 
            System.out.println("ERROR: Expected 'read' token not received");
            System.exit(0);   
        }
        t.skipToken();
        // parse IdList
        list = new IdList();
        list.parseSSIdList();
        t.skipToken();
        // Check for ending ; 
        if (t.getToken() != 12) {
            System.out.println("ERROR: Missing semicolon after read statement");
            System.exit(0);
        }
        // skip ; 
        t.skipToken(); 
    }

    public void printSt() { 
        System.out.print("read ");
        list.prettyPrint();

    }

    public void execSt() {
        // here we want to read values from the data file one 
        // line at a time and assign them to the val of the id
        list.execReadIdList();
    }


}
