import java.io.*;

public class Scanner {
    private final BufferedReader reader;
    private int lookaheadChar = -1; 

    public Scanner(String filename) throws IOException {
        this.reader = new BufferedReader(new FileReader(filename));
    }

    private int getNextChar() throws IOException {
        if (this.lookaheadChar != -1) {
            int temp = this.lookaheadChar;
            this.lookaheadChar = -1;  
            return temp;
        }
        return reader.read();
    }

    public void close() throws IOException {
        this.reader.close();
    }

    public Token getNextToken() throws IOException {
        int prevState = 0;
        int state = 0;
        String curInt = "";
        char firstLetter = '\0';
        int ch;

        while ((ch = getNextChar()) != -1) {
            state = TransitionTable.transitionTable[state][ch];
            if (state == -1) {
                // error state, check if previous state was accepting
                if (TransitionTable.acceptingStates.contains(prevState)) {
                    // save offending character for next token
                    this.lookaheadChar = ch;

                    if (prevState == 18) {
                        if (firstLetter == 'l') prevState = 19;
                        else if (firstLetter == 'r') prevState = 20;
                    }

                    if (prevState == 38) {
                        // System.out.println("First letter for CONST state " + firstLetter);
                        if (firstLetter == 'r')
                            prevState = 39;
                    }

                    Token returnToken = new Token(
                        TokenType.fromCode(prevState), 
                        prevState == 38 || prevState == 39 ? Integer.parseInt(curInt) : null
                    );

                    // System.out.println("Current integer: " + curInt);

                    // reset state machine
                    state = 0;
                    prevState = 0;
                    curInt = "";
                    firstLetter = '\0';

                    return returnToken;
                } else {
                    System.err.println("Lexical error at character: " + (char) ch);
                    return null;
                }
            }

            if (ch >= '0' && ch <= '9') {
                curInt += (char) ch;
            }

            firstLetter = firstLetter == '\0' || firstLetter == ' ' ? (char) ch : firstLetter;
            prevState = state;
        }

        // Reached EOF, check if last token was accepting
        if (TransitionTable.acceptingStates.contains(prevState)) {
            if (prevState == 18) {
                if (firstLetter == 'l') prevState = 19;
                else if (firstLetter == 'r') prevState = 20;
            }
            if (prevState == 38) {
                if (firstLetter == 'r') prevState = 39;
            }

            return new Token(
                TokenType.fromCode(prevState),
                prevState == 38 || prevState == 39 ? Integer.parseInt(curInt) : null
            );
        } else if (!curInt.isEmpty() || firstLetter != '\0') {
            // EOF reached but last token incomplete
            System.err.println("Lexical error at EOF: incomplete token starting with '" + firstLetter + "'");
        }

        return new Token(
            TokenType.EOF,
            null
        );
    }
}