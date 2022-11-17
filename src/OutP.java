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
        list.parseSSIdList();
        t.skipToken();
        // check for ending ; 
        if (t.getToken() != 12) { 
            System.out.println("ERROR: Missing semicolon on write statement");
            System.exit(0);
        } 
        // skip ; 
        t.skipToken();
    }


    public void printSt() { 
        System.out.print("write ");
        list.prettyPrint();
    }

    public void execSt() {
        // Here we just want to print the ids and their corresponding values
        list.execWriteIdList();
    }


}
