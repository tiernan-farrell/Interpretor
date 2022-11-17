public class App {

    public static void main(String[] args) throws Exception {
        String codeFile = args[0];
        String dataFile = args[1];

        
        System.out.println("Code file: " + codeFile);
        System.out.println("Data file: " + dataFile);

        // Create the "global" tokenizer through the Singleton class
        Singleton tok = new Singleton(codeFile, dataFile);
        // Create new program 
        Program p = new Program();
        // Parse, print, exec prog

        p.parseProgram();
        System.out.println("\nInput Program:\n");
        p.prettyPrint();
        System.out.println("\nOutput:\n");
        p.execProg();
        // // p.test();
        // Tokenizer t = Singleton.instance();
        // while (t.getToken() != 33) { 
        //     System.out.print(t.getToken() + " ");
        //     t.skipToken();
        // }
        // System.out.println(t.getToken());
        // t.skipToken();
        // System.out.println(t.getToken());
        // t.skipToken();
        // System.out.println("ehll " + t.getToken());
    }
}
