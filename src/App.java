public class App {

    public static void main(String[] args) throws Exception {
        String codeFile = args[0];
        // String dataFile = args[1];
        System.out.println("Interpretor running");
        System.out.println("Code file: " + codeFile);
        // System.out.println("Data file: " + dataFile);

        // Create the "global" tokenizer through the Singleton class
        Singleton tok = new Singleton(codeFile);
        // Create new program 
        Program p = new Program();
        // Parse, print, exec prog
        p.test();
        p.parseProgram();
        p.prettyPrint();
        p.execProg();
        // p.test();

        // System.out.println(t.getToken());
        // t.skipToken();
        // System.out.println(t.getToken());
        // t.skipToken();
        // System.out.println(t.getToken());
        // t.skipToken();
        // System.out.println("ehll " + t.getToken());
    }
}
