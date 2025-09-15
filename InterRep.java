public class InterRep {
    private final TokenType opCode;
    private final InterRepBlock arg1;
    private final InterRepBlock arg2;
    private final InterRepBlock arg3;
    private InterRep prev;
    private InterRep next;

    public InterRep() {
        this.opCode = null;
        this.arg1 = null;
        this.arg2 = null;
        this.arg3 = null;
        this.prev = null;
        this.next = null;
    }

    public InterRep(TokenType opCode, InterRepBlock arg1, InterRepBlock arg2, InterRepBlock arg3) {
        this.opCode = opCode;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
        this.prev = null;
        this.next = null;
    }

    public TokenType getOpCode() {
        return this.opCode;
    }

    public InterRepBlock getArg1() {
        return this.arg1;
    }

    public InterRepBlock getArg2() {
        return this.arg2;
    }

    public InterRepBlock getArg3() {
        return this.arg3;
    }

    public InterRep getPrev() {
        return this.prev;
    }

    public InterRep getNext() {
        return this.next;
    }

    public void setPrev(InterRep prev) {
        this.prev = prev;
    }

    public void setNext(InterRep next) {
        this.next = next;
    }

    @Override
    public String toString() {
        if (this.opCode == TokenType.LOADI || this.opCode == TokenType.OUTPUT) {
            return opCode.getLexeme() + "\t[ val " + arg1.getSR() + " ], " + arg2 +  ", " + arg3;
        };

        return opCode.getLexeme() + "\t" + arg1 + ", " + arg2 +  ", " + arg3;
    }
}