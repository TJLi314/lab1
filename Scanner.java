import java.io.*;

public class Scanner {
    private final BufferedReader reader;

    public Scanner(String filename) throws IOException {
        this.reader = new BufferedReader(new FileReader(filename));
    }

    private int getNextChar() throws IOException {
        return reader.read();
    }

    public void close() throws IOException {
        this.reader.close();
    }

    public Token getNextToken() throws IOException {
        int state = 0;
        StringBuilder token = new StringBuilder();
        int ch;

        while ((ch = getNextChar()) != -1) {
            token.append((char) ch);
            state = TransitionTable.transitionTable[state][ch];

            if (state == -1) {
                // Error state
                System.err.println("Lexical error at character: " + (char) ch);
                return null;
            }

            if (TransitionTable.acceptingStates.contains(state)) {
                // Accepting state
                return new Token(TokenType.fromCode(state), 
                    state == 12 ? Integer.parseInt(token.substring(1)) : null);
            }
        }

        if (token.length() > 0) {
            System.err.println("Incomplete token at end of file: " + token);
        }

        return null; // End of file or error
    }

}