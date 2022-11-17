import java.util.ArrayList;
import java.util.Scanner;

public class IdList {
    private static Tokenizer t;
    private static Scanner in; 
    private Id id; 
    private IdList list; 

    public IdList() { 
        t = Singleton.instance();
        in = Singleton.dataInstance();
    }

    
    public void test() { 
        System.out.println("IdList: " + t.getToken());
        t.skipToken();
    }

    public void parseDeclIdList() { 
        // Ensure token is id 
        if (t.getToken() != 32) { 
            System.out.println("ERROR: Expected a valid id, recieved invalid input");
            System.exit(0);   
        }
        // parse Id
        id = Id.parseId();
        // Check if next token is comma meaning more Ids
        if (t.getToken() == 13) { 
            t.skipToken();
            list = new IdList();
            list.parseDeclIdList();
        }

    }

    public void parseSSIdList() { 
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
            list.parseDeclIdList();
        }

    }

    public void execWriteIdList() { 
        System.out.println(id.name + " = " + id.val);
        if (list != null) { 
            list.execWriteIdList();
        }
    }

    public void execReadIdList() { 
        // some data reading here
        // check for invalid entry 
        Int val = new Int();
        if (!in.hasNextLine()) { 
            System.out.println("ERROR: Trying to read from empty data file");
            System.exit(0);
        } 
        int v = Integer.parseInt(in.nextLine());
        val.setVal(v);
        if (val.getVal() < 0) { 
            System.out.println("ERROR: Cannot use negative numbers");
            System.exit(0);
        }
        id.assignValue(val.getVal());
        if (list != null) { 
            list.execReadIdList();
        }
    }
    public void prettyPrint() { 
        if (list != null) { 

            System.out.print(id.name + ", ");
            list.prettyPrint(); 
        }
        else {
            System.out.println(id.name + ";");
        }
    }
}
