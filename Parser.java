public class Parser {
    public static ParserResult parse(String filename) {
        try {
            Scanner scanner = new Scanner(filename);
            InterRep head = new InterRep();
            InterRep current = head;
            int opCount = 0;
            boolean parseSuccessful = true;

            Token token = scanner.getNextToken();
            while (token.getType() != TokenType.ENDFILE) {
                // System.out.println("Token: " + token);
                switch (token.getType()) {
                    case LOAD, STORE -> {
                        TokenType memOp = token.getType();

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.REG) {
                            System.err.println("Syntax error: Expected REGISTER after MEMOP");
                            parseSuccessful = false;
                            scanner.skipLine();
                            break;
                        } 
                        int memSR1 = token.getValue();

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.INTO) {
                            System.err.println("Syntax error: Expected INTO after REGISTER");
                            parseSuccessful = false;
                            scanner.skipLine();
                            break;
                        }

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.REG) {
                            System.err.println("Syntax error: Expected REGISTER after INTO");
                            parseSuccessful = false;
                            scanner.skipLine();
                            break;
                        } 
                        int memSR3 = token.getValue();

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.NEWLINE) {
                            System.err.println("Syntax error: Expected EOL after REGISTER");
                            parseSuccessful = false;
                            scanner.skipLine();
                            break;
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
                        opCount++;
                    }
                    case LOADI -> {
                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.CONST) {
                            System.err.println("Syntax error: Expected CONST after LOADI");
                            parseSuccessful = false;
                            scanner.skipLine();
                            break;
                        } 
                        int loadiConst = token.getValue();

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.INTO) {
                            System.err.println("Syntax error: Expected INTO after CONST");
                            parseSuccessful = false;
                            scanner.skipLine();
                            break;
                        }

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.REG) {
                            System.err.println("Syntax error: Expected REGISTER after INTO");
                            parseSuccessful = false;
                            scanner.skipLine();
                            break;
                        } 
                        int loadiSR3 = token.getValue();

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.NEWLINE) {
                            System.err.println("Syntax error: Expected EOL after REGISTER");
                            parseSuccessful = false;
                            scanner.skipLine();
                            break;
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
                        opCount++;
                    }
                    case ADD, SUB, MULT, LSHIFT, RSHIFT -> {
                        TokenType arithOp = token.getType();

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.REG) {
                            System.err.println("Syntax error: Expected REGISTER after ARITHOP");
                            parseSuccessful = false;
                            scanner.skipLine();
                            break;
                        } 
                        int arithSR1 = token.getValue();

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.COMMA) {
                            System.err.println("Syntax error: Expected COMMA after REGISTER");
                            parseSuccessful = false;
                            scanner.skipLine();
                            break;
                        }

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.REG) {
                            System.err.println("Syntax error: Expected REGISTER after COMMA");
                            parseSuccessful = false;
                            scanner.skipLine();
                            break;
                        } 
                        int arithSR2 = token.getValue();

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.INTO) {
                            System.err.println("Syntax error: Expected INTO after REGISTER");
                            parseSuccessful = false;
                            scanner.skipLine();
                            break;
                        }

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.REG) {
                            System.err.println("Syntax error: Expected REGISTER after INTO");
                            parseSuccessful = false;
                            scanner.skipLine();
                            break;
                        }
                        int arithSR3 = token.getValue();

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.NEWLINE) {
                            System.err.println("Syntax error: Expected EOL after REGISTER");
                            parseSuccessful = false;
                            scanner.skipLine();
                            break;
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
                        opCount++;
                    }
                    case OUTPUT -> {
                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.CONST) {
                            System.err.println("Syntax error: Expected CONSTANT after OUTPUT");
                            parseSuccessful = false;
                            scanner.skipLine();
                            break;
                        } 
                        int outputConst = token.getValue();

                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.NEWLINE) {
                            System.err.println("Syntax error: Expected EOL after CONSTANT");
                            parseSuccessful = false;
                            scanner.skipLine();
                            break;
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
                        opCount++;
                    }
                    case NOP -> {
                        token = scanner.getNextToken();
                        if (token.getType() != TokenType.NEWLINE) {
                            System.err.println("Syntax error: Expected EOL after NOP");
                            parseSuccessful = false;
                            scanner.skipLine();
                            break;
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
                        opCount++;
                    }
                    case NEWLINE -> {
                        // Ignore EOL tokens
                    }
                    case ENDFILE -> {
                        break; // End the while loop
                    }
                    default -> { // This should never happen
                        System.err.println("Syntax error: Unexpected token " + token.getType());
                        parseSuccessful = false;
                        scanner.skipLine();
                        break;
                    }
                }

                token = scanner.getNextToken();
            }

            scanner.close();

            head.getNext().setPrev(null); 
            return new ParserResult(head.getNext(), parseSuccessful, opCount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}