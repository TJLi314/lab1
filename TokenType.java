// TokenType.java
import java.util.HashMap;
import java.util.Map;

public enum TokenType {
    LOAD(11),
    STORE(5),
    LOADI(12),
    ADD(24),
    SUB(7),
    MULT(18),
    LSHIFT(19),
    RSHIFT(20),
    OUTPUT(33),
    NOP(27),
    COMMA(36),
    INTO(35),
    EOL(37),
    CONST(38),
    REG(39),
    EOF(99);

    private final int code;
    private static final Map<Integer, TokenType> codeToToken = new HashMap<>();

    static {
        for (TokenType t : TokenType.values()) {
            codeToToken.put(t.code, t);
        }
    }

    TokenType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static TokenType fromCode(int code) {
        return codeToToken.get(code);
    }
}