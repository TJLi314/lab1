public class Parser {
    
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner("test.txt");
            InterRep head = new InterRep();
            InterRep current = head;

            Token token = scanner.getNextToken();
            while (token != null) {
                switch (token.getType()) {
                    case LOAD, STORE -> {
                        TokenType memOp = token.getType();

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.REG) {
                            System.err.println("Syntax error: Expected REGISTER after MEMOP");
                            return;
                        } 
                        int memSR1 = token.getValue();

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.INTO) {
                            System.err.println("Syntax error: Expected INTO after REGISTER");
                            return;
                        }

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.REG) {
                            System.err.println("Syntax error: Expected REGISTER after INTO");
                            return;
                        } 
                        int memSR3 = token.getValue();

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.EOL) {
                            System.err.println("Syntax error: Expected EOL after REGISTER");
                            return;
                        }

                        InterRep newMemOpNode = new InterRep(
                            memOp,
                            new InterRepBlock(memSR1),
                            new InterRepBlock(),
                            new InterRepBlock(memSR3)
                        );

                        current.setNext(newMemOpNode);
                        newMemOpNode.setPrev(current);
                        current = newMemOpNode;
                    }
                    case LOADI -> {
                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.CONST) {
                            System.err.println("Syntax error: Expected CONST after LOADI");
                            return;
                        } 
                        int loadiConst = token.getValue();

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.INTO) {
                            System.err.println("Syntax error: Expected INTO after CONST");
                            return;
                        }

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.REG) {
                            System.err.println("Syntax error: Expected REGISTER after INTO");
                            return;
                        } 
                        int loadiSR3 = token.getValue();

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.EOL) {
                            System.err.println("Syntax error: Expected EOL after REGISTER");
                            return;
                        }

                        InterRep loadiNode = new InterRep(
                            TokenType.LOADI,
                            new InterRepBlock(loadiConst),
                            new InterRepBlock(),
                            new InterRepBlock(loadiSR3)
                        );

                        current.setNext(loadiNode);
                        loadiNode.setPrev(current);
                        current = loadiNode;
                    }
                    case ADD, SUB, MULT, LSHIFT, RSHIFT -> {
                        TokenType arithOp = token.getType();

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.REG) {
                            System.err.println("Syntax error: Expected REGISTER after ARITHOP");
                            return;
                        } 
                        int arithSR1 = token.getValue();

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.COMMA) {
                            System.err.println("Syntax error: Expected COMMA after REGISTER");
                            return;
                        }

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.REG) {
                            System.err.println("Syntax error: Expected REGISTER after COMMA");
                            return;
                        } 
                        int arithSR2 = token.getValue();

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.INTO) {
                            System.err.println("Syntax error: Expected INTO after REGISTER");
                            return;
                        }

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.REG) {
                            System.err.println("Syntax error: Expected REGISTER after INTO");
                            return;
                        }
                        int arithSR3 = token.getValue();

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.EOL) {
                            System.err.println("Syntax error: Expected EOL after REGISTER");
                            return;
                        }

                        InterRep arithNode = new InterRep(
                            arithOp,
                            new InterRepBlock(arithSR1),
                            new InterRepBlock(arithSR2),
                            new InterRepBlock(arithSR3)
                        );

                        current.setNext(arithNode);
                        arithNode.setPrev(current);
                        current = arithNode;
                    }
                    case OUTPUT -> {
                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.CONST) {
                            System.err.println("Syntax error: Expected CONSTANT after OUTPUT");
                            return;
                        } 
                        int outputConst = token.getValue();

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.EOL) {
                            System.err.println("Syntax error: Expected EOL after CONSTANT");
                            return;
                        }

                        InterRep outputNode = new InterRep(
                            TokenType.OUTPUT,
                            new InterRepBlock(outputConst),
                            new InterRepBlock(),
                            new InterRepBlock()
                        );

                        current.setNext(outputNode);
                        outputNode.setPrev(current);
                        current = outputNode;
                    }
                    case NOP -> {
                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.EOL) {
                            System.err.println("Syntax error: Expected EOL after NOP");
                            return;
                        }

                        InterRep nopNode = new InterRep(
                            TokenType.NOP,
                            new InterRepBlock(),
                            new InterRepBlock(),
                            new InterRepBlock()
                        );

                        current.setNext(nopNode);
                        nopNode.setPrev(current);
                        current = nopNode;
                    }
                    case EOL -> {
                        // Ignore EOL tokens
                    }
                    case EOF -> {
                        token = null; // End the while loop
                    }
                    default -> {
                        System.err.println("Syntax error: Unexpected token " + token.getType());
                        return;
                    }
                }

                token = scanner.getNextToken();
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}