import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Singleton {
    private static Tokenizer instance; 
    private static Scanner dataInstance; 

    public Singleton(String fname, String dataFile) { 
        instance = new Tokenizer(fname);
        File f = new File(dataFile);
        try { 
            dataInstance = new Scanner(f);
        } catch (FileNotFoundException e) { 
            System.out.println("ERROR: reading data file: " + dataFile);
            System.exit(0);
        }
    }

    public static Tokenizer instance() { 
        return instance;
    }

    public static Scanner dataInstance() { 
        return dataInstance;
    }
}
