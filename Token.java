public class Token {
    private final TokenType type;
    private final Integer value;

    public Token(TokenType type) {
        this.type = type;
        this.value = null;
    }

    public Token(TokenType type, int value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (value != null) {
            return type + "(" + value + ")";
        } else {
            return type.toString();
        }
    }
}
