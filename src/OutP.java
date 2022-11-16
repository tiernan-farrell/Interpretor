public class OutP {
    private static Tokenizer t; 
    private IdList list;

    public OutP() { 
        t = Singleton.instance();
    }
    
    public void parseSt() { 
        // ensure write token 
        if (t.getToken() != 11) { 
            System.out.println("ERROR: Expected 'write' token not received");
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
