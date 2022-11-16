public class Interpretor {
    public static void main(String[] args) {
        if (args.length != 2) { 
            System.out.println("ERROR: Expacting exactly two command line inputs 1. Code file 2. Data file");
            System.exit(0);
        }
        String codeFile = args[0];
        String dataFile = args[1];

        // Create Tokenizer
        Singleton s = new Singleton(codeFile);

        // Create the program
        Program p = new Program();
        // parse, print, execute
        p.parseProgram();
        p.prettyPrint();
        p.execProg();
    }
}
