import java.util.ArrayList;

import javax.swing.plaf.synth.SynthScrollBarUI;

public class Id {
    private static Tokenizer t = Singleton.instance(); 
    String name; 
    int val;
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
    
        // System.out.println("\n" + name + " in ids? ");
        // for (int j = 0; j < Id.ids.size(); j++) { 
        //     System.out.print(Id.ids.get(j).name + ": " + Id.ids.get(j).val + ", ");
        // }
        for (int i = 0; i < ids.size(); i++) { 
            // System.out.println("name: " + name + Id.ids.get(i).name);
            if (name.equals(Id.ids.get(i).name)) { 
                return ids.get(i);
            } 
        }
        // If not, create id, add to list, skip token and return id
        Id newId = new Id(name);
        ids.add(newId);
        
        idCount++;
        t.skipToken();
        return newId; 
    }

    public int getVal() { 
        if (Integer.valueOf(val) == null)  {
            System.out.println("ERROR: trying to get value for unitilialized ID: " + name);
            System.exit(0);
        }
        return ids.get(ids.indexOf(this)).val;
    }

    public void prettyPrint() { 
        System.out.print(name);
    }

    public void assignValue(int i) { 
        ids.get(ids.indexOf(this)).val = i;
    }
    
    public String getName() { 
        return name; 
    }
}
