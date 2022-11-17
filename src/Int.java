public class Int {
    private static Tokenizer t; 
    private int val; 

    public Int() { 
        t = Singleton.instance();
    }

    public void parseInt() { 
        // Ensure token is int 
        if (t.getToken() != 31) { 
            System.out.println("ERROR: Expected integer token not received");
            System.exit(0);
        }
        val = t.intVal();
        if (val < 0) { 
            System.out.println("ERROR: Negative integer values not allowed");
            System.exit(0);
        }
        t.skipToken();
    }

    public int getVal() { 
        return val;
    }
    public void setVal(int i) { 
        val = i;
    } 
    public static Int getIntVal(int i) { 
        Int num = new Int();
        num.setVal(i);
        return num; 
    }

    public void prettyPrint() { 
        System.out.print(val);
    }


}
