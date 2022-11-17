import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class Tokenizer { 

    Scanner in; 
    String line;
    ArrayList<String> tokenStrings; 
    ArrayList<Integer> tokens;
    ArrayList<Character> charBuffer; 
    
    private static final Map<String, Integer> wordsAndSymbols;
    private static final ArrayList<Character> specCharSet1;
    private static final ArrayList<Character> specCharSet2; 
    final String[] reservedWords = {"program", "begin", "end", "int", "if", "then", "else", "while", "loop", "read", "write"};
    
    static {
        specCharSet1 = new ArrayList<Character>();
        specCharSet1.add(';'); 
        specCharSet1.add(','); 
        specCharSet1.add('['); 
        specCharSet1.add( ']');
        specCharSet1.add( '(');
        specCharSet1.add( ')');
        specCharSet1.add( '+');
        specCharSet1.add( '-');
        specCharSet1.add( '*');

        specCharSet2 = new ArrayList<Character>();
        specCharSet2.add('=');
        specCharSet2.add('!');
        specCharSet2.add('<');
        specCharSet2.add('>');

        wordsAndSymbols = new HashMap<String, Integer>();
        wordsAndSymbols.put("program", 1);
        wordsAndSymbols.put("begin", 2);
        wordsAndSymbols.put("end", 3);
        wordsAndSymbols.put("int", 4);
        wordsAndSymbols.put("if", 5);
        wordsAndSymbols.put("then", 6);
        wordsAndSymbols.put("else", 7);
        wordsAndSymbols.put("while", 8);
        wordsAndSymbols.put("loop", 9);
        wordsAndSymbols.put("read", 10);
        wordsAndSymbols.put("write", 11);
        wordsAndSymbols.put(";", 12);
        wordsAndSymbols.put(",", 13);
        wordsAndSymbols.put("=", 14);
        wordsAndSymbols.put("!", 15);
        wordsAndSymbols.put("[", 16);
        wordsAndSymbols.put("]", 17);
        wordsAndSymbols.put("&&", 18);
        wordsAndSymbols.put("||", 19);
        wordsAndSymbols.put("(", 20);
        wordsAndSymbols.put(")", 21);
        wordsAndSymbols.put("+", 22);
        wordsAndSymbols.put("-", 23);
        wordsAndSymbols.put("*", 24);
        wordsAndSymbols.put("!=", 25);
        wordsAndSymbols.put("==", 26);
        wordsAndSymbols.put("<", 27);
        wordsAndSymbols.put(">", 28);
        wordsAndSymbols.put("<=", 29);
        wordsAndSymbols.put(">=", 30);
        wordsAndSymbols.put("EOF", 33);
    }

    /**
     * Constructor for the Tokenizer object
     * @param fname
     *          the file name of the program to be tokenized
     * 
     * Initializes tokenStrings, tokens, charBuffer, scanner in
     * Reads and tokenizes the first line of the file if it is found
     */
    public Tokenizer(String fname) { 
        // Read lines into lines
        tokenStrings = new ArrayList<String>();
        tokens = new ArrayList<Integer>();
        charBuffer = new ArrayList<Character>();
        try  {     
            File inputFile = new File(fname);
            in = new Scanner(inputFile);
            parseLine();
            
        } catch (FileNotFoundException e) { 
            System.out.println("\nERROR: error reading input file");
            System.exit(0);
        }
        
    }



    private void parseLine() { 
        if (in.hasNextLine()) { 
            line = in.nextLine();
            // System.out.println("Line: '" + line + "'");
            for (int i = 0; i < line.length(); i++) { 
                charBuffer.add(line.charAt(i));
            }
            // System.out.println("Char List: " + charBuffer);
            tokenize();
        } else {
            tokens.add(0, 33);
            in.close();
        }
    }


    /**
     * tokenize() is called once the character buffer for a line is filled 
     * tokenize() fills tokenStrings from the current character buffer
     * it then calls getTokenIntRepresentation() to fill tokens
     */
    private void tokenize() {

        String currToken = "";
        while (!charBuffer.isEmpty()) {
            //tokenStrings.add(String.valueOf(charBuffer.remove(0)));
            char curr = charBuffer.remove(0);

            // Strip leading whitespace on new line
            if (tokenStrings.size() == 0 && currToken == "") {
                while(Character.isWhitespace(curr)) { 
                    curr = charBuffer.remove(0);
                } 
            }

            currToken += curr;
            // If first character in token check if it is a one character token first

            // Check for special characters that are only one character
            if (specCharSet1.contains(Character.valueOf(curr))) {
                tokenStrings.add(currToken);
            } 
            // Check for those than you can be greedy with 
            else if (specCharSet2.contains(Character.valueOf(curr))) {
                // If the char is in special set 2 that means it is tokenized as a sole character
                // unless the following character in the line is an = 
                if(charBuffer.size() > 0 && charBuffer.get(0) == '=') { 
                    currToken += charBuffer.remove(0);
                }
                tokenStrings.add(currToken);
            }
            // Check for ||  
            else if (curr == '|') { 
                if(charBuffer.size() > 0 && charBuffer.get(0) == '|') { 
                    currToken += charBuffer.remove(0);
                    tokenStrings.add(currToken);
                } else { 
                    System.out.println("\nERROR on Token '|' on line : " + line);
                    System.exit(0);
                }
            } 
            // Check for &&
            else if (curr == '&') { 
                if(charBuffer.size() > 0 && charBuffer.get(0) == '&') { 
                    currToken += charBuffer.remove(0);
                    tokenStrings.add(currToken);
                } else { 
                    System.out.println("\nERROR on Token '&' on line : " + line);
                    System.exit(0);
                }
            } 
            // Check for integers 
            else if (Character.isDigit(curr))  { 
                // If lowercase letter grab whole word before line end or whitespace 
                while (!charBuffer.isEmpty() && Character.isDigit(charBuffer.get(0))) { 
                    currToken += charBuffer.remove(0);
                }
                tokenStrings.add(currToken);
    
            } 
            // Check for keywords 
            else if (Character.isLowerCase(curr))  { 
                // If lowercase letter grab whole word before line end or whitespace or end of statement by semicolon
                while (!charBuffer.isEmpty() && !Character.isWhitespace(charBuffer.get(0)) && charBuffer.get(0) != ';') { 
                    currToken += charBuffer.remove(0);
                }
                // Check if the word is in the reserved word set and add it 
                if (Arrays.asList(reservedWords).contains(currToken)) { 
                    tokenStrings.add(currToken);
                } else { 
                    // If it isn't print error message
                    System.out.println("\nERROR Reading token: " + currToken + " from line: " + line);
                    System.exit(0);
                }
            } 
            // Check for Identifiers 
            else if(Character.isUpperCase(curr)) { 
                // While there is a next character that is not a special symbol or space 
                while(!charBuffer.isEmpty() && !Character.isWhitespace(charBuffer.get(0)) && !specCharSet1.contains(charBuffer.get(0)) &&  !specCharSet2.contains(charBuffer.get(0))) { 
                    char next = charBuffer.get(0);
                    // Legal token continue building id 
                    if (Character.isUpperCase(next) || Character.isDigit(next)) { 
                        currToken += next; 
                        charBuffer.remove(0);
                    } 
                    // Illegal token print error
                    else { 
                        System.out.println("\nERROR on token " + currToken + charBuffer.get(0) + " on line: " + line);
                        System.exit(0);
                    }
                }
                tokenStrings.add(currToken);
            }
            currToken = "";
        }
        // we have all of the tokens as strings generate integer values from each string
        // System.out.println("TokenStrings: " + tokenStrings);
        getTokenIntRepresentation();
        // System.out.println("Tokens: " + tokens);
    }


    /**
     * Maps the token strings to their corresponding int values
     */
    private void getTokenIntRepresentation()  {
        for (int i = 0; i < tokenStrings.size(); i++) { 
            String token = tokenStrings.get(i);
            // Check if the keyword or special character a
            if (wordsAndSymbols.keySet().contains(token)) { 
                tokens.add(wordsAndSymbols.get(token));
            } else {
                // If the current token starts with a number then it is an integer 
                if (Character.isDigit(token.charAt(0))) { 
                    tokens.add(31);
                } 
                // If it starts with an uppercase letter it is an ID
                else if (Character.isUpperCase(token.charAt(0))) { 
                    tokens.add(32); 
                } 
            }
        }
    }

    /**
     * gets the integer corresponding to the current token 
     * @return the int value of the token
     */
    public int getToken() {
        return tokens.get(0);
    }

    /**
     * Removes the first token to move on to the next in the list
     * if no tokens, it calls parseLine to fill a new buffer and 
     * tokenize the next line 
     */
    public void skipToken() {
        tokens.remove(0);
        tokenStrings.remove(0);
        while (tokens.isEmpty()) { 
            parseLine();    
        }
    }

    /**
     * gets the actual integer number that the token corresponds to 
     * @return integer number of the token
     */
    public int intVal() {
        // Ensure that the token is an integer 
        if (getToken() == 31) { 
            return Integer.parseInt(tokenStrings.get(0));
        }
        // If not print error
        else { 
            System.out.println("\nERROR current token is not an integer, no int value\n");
            return 0; 
        }
    }

    /**
     * Gets the name of the current token if it is an identifier 
     * @return name of identifier or empty string if not identifier
     */
    public String idName() { 
        
        if (getToken() == 32) { 
            return tokenStrings.get(0);
        }
        else { 
            System.out.println("\nERROR current token is not an identier, no string value\n");
            return ""; 
        }
    }
    public static void main(String[] args) {
        // First Get name of file
        Scanner sysIn = new Scanner(System.in);
        System.out.println("Enter the name of the core file: ");
        String name = sysIn.nextLine();
        sysIn.close();
        // Init Tokenizer with file name and loop getting token and skipping until EOF
        Tokenizer t = new Tokenizer(name);
        while (t.getToken() != 33) { 
            System.out.print(t.getToken() + " ");
            t.skipToken();
        }
        // Add the 33 that doesn't get printed from the loop 
        System.out.print(33);
    }
}