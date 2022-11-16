import java.util.ArrayList;

public class Id {
    private static Tokenizer t = Singleton.instance(); 
    String name; 
    Integer val;
    boolean declared;
    boolean initialized; 
    static ArrayList<Id> ids; 
    static int idCount; 


    Id(String n) { 
        name = n;
        declared = false; 
        initialized = false;
    }

    
    public void test() { 
        System.out.println("Id: " + t.getToken());
        t.skipToken();

    }

    public static Id parseId() { 
        // Ensure token is id 
        if (t.getToken() != 32) { 
            System.out.println("ERROR: Expected a valid id, recieved invalid input");
            System.exit(0);
        }
        // Get name
        String name = t.idName();
        if (ids == null) { 
            ids = new ArrayList<Id>();
        }
        // Check if in ids
        for (int i = 0; i < ids.size(); i++) { 
            if (name == ids.get(i).name) { 
                return ids.get(i);
            } 
        }
        // If not, create id, add to list, skip token and return id
        Id newId = new Id(name);
        ids.add(newId);
        t.skipToken();
        return newId; 
    }

    public int getVal() { 
        if (val == null)  {
            System.out.println("ERROR: trying to get value for unitilialized ID");
            System.exit(0);
        }

        return val.intValue();
    }

    public void prettyPrint() { 
        System.out.println(name + " = " + val);
    }

    public void assignValue(int val) { 
        val = Integer.valueOf(val);
    }

    public String getName() { 
        return name; 
    }
}
