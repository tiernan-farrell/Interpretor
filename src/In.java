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
        list.parseIdList();
    }
    public void printSt() { 
        
    }

    public void execSt() {

    }


}