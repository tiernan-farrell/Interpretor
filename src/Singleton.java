public class Singleton {
    private static Tokenizer instance; 


    public Singleton(String fname) { 
        instance = new Tokenizer(fname);
    }

    public static Tokenizer instance() { 
        return instance;
    }
}
