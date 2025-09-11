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
        int prevState = 0;
        int state = 0;
        StringBuilder token = new StringBuilder();
        int ch;

        while ((ch = getNextChar()) != -1) {
            state = TransitionTable.transitionTable[state][ch];

            if (state == -1) {

                // error state, check if previous state was accepting
                if (TransitionTable.acceptingStates.contains(prevState)) {
                    return new Token(TokenType.fromCode(prevState), 
                    prevState == 38 || prevState == 39 ? Integer.parseInt(token.substring(1)) : null);
                }

                System.err.println("Lexical error at character: " + (char) ch);

                return null;
            }
            if (ch != ' ') {
                token.append((char) ch);
            }
        }

        if (token.length() > 0) {
            System.err.println("Incomplete token at end of file: " + token);
        }

        return null; // End of file or error
    }

}