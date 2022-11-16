public class IdList {
    private static Tokenizer t; 
    private Id id; 
    private IdList list; 


    public IdList() { 
        t = Singleton.instance();
    }

    
    public void test() { 
        System.out.println("IdList: " + t.getToken());
        t.skipToken();
    }

    public void parseIdList() { 
        // Ensure token is id 
        if (t.getToken() != 32) { 
            System.out.println("ERROR: Expected a valid id, recieved invalid input");
            System.exit(0);   
        }
        // parse Id
        id = Id.parseId();
        // Check if next token is comma meaning more Ids
        if (t.getToken() == 13) { 
            list = new IdList();
            list.parseIdList();
        }

    }

    public void prettyPrint() { 
        
    }
}
