public class Token {
    private final TokenType type;
    private final Integer value;
    private final int lineNumber;

    public Token(TokenType type, int lineNumber) {
        this.type = type;
        this.value = null;
        this.lineNumber = lineNumber;
    }

    public Token(TokenType type, Integer value, int lineNumber) {
        this.type = type;
        this.value = value;
        this.lineNumber = lineNumber;
    }

    public TokenType getType() {
        return type;
    }

    public Integer getValue() {
        return value;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    @Override
    public String toString() {
        String s = Integer.toString(this.lineNumber) + ": < " + this.type.toString() + ", \"";
        if (this.type == TokenType.CONST) {
            s += this.value;
        } else if (this.type == TokenType.REG) {
            s += "r" + this.value;
        } else {
            s += this.type.getLexeme();
        }
        s += "\" >";
        return s;
    }
}
