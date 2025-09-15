public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            runParseMode("test_inputs/t2.i");
            return;
        }

        String flag = args[0];

        switch (flag) {
            case "-h" -> printHelp();

            case "-s" -> {
                if (args.length < 2) {
                    System.err.println("Error: Missing filename for -s option.");
                    printHelp();
                } else {
                    runScanMode(args[1]);
                }
            }

            case "-p" -> {
                if (args.length < 2) {
                    runParseMode(null);
                } else {
                    runParseMode(args[1]);
                }
            }

            case "-r" -> {
                if (args.length < 2) {
                    System.err.println("Error: Missing filename for -r option.");
                    printHelp();
                } else {
                    runIRMode(args[1]);
                }
            }

            default -> {
                System.err.printf("Error: Unknown option '%s'%n", flag);
                printHelp();
            }
        }
    }

    private static void printHelp() {
        System.out.println("Command Syntax:");
        System.out.println("\t./412fe [flags] filename\n");

        System.out.println("Required Arguments:");
        System.out.println("\tfilename is the pathname (absolute or relative) to the input file\n");

        System.out.println("Optional flags:");
        System.out.println("\t-h \tprints this message\n");

        System.out.println("At most one of the following three flags:");
        System.out.println("\t-s \tprints tokens in token stream");
        System.out.println("\t-p \tinvokes parser and reports on success or failure");
        System.out.println("\t-r \tprints human readable version of parser's IR");
        System.out.println("If none is specified, the default action is '-p'");
    }

    private static void runScanMode(String filename) {
        try {
            Scanner scanner = new Scanner(filename);
            Token token = scanner.getNextToken();
            while (token.getType() != TokenType.ENDFILE) {
                System.out.println(token);
                token = scanner.getNextToken();
            }
            System.out.println(token); // Print EOF token
            scanner.close();
        } catch (Exception e) {
            System.err.println("Error scanning file: " + e.getMessage());
        }
    }

    private static void runParseMode(String filename) {
        try {
            ParserResult result = Parser.parse(filename);
            if (result != null) {
                if (result.isSuccess()) {
                    System.out.println("Parsing succeeded. Processed " + result.getOpCount() + " operations.");
                } else {
                    System.out.println("Parse found errors.");
                }
            } else {
                System.out.println("Parsing failed.");
            }
        } catch (Exception e) {
            System.err.println("Error parsing file: " + e.getMessage());
        }
    }

    private static void runIRMode(String filename) {
        try {
            ParserResult result = Parser.parse(filename);
            if (result != null) {
                InterRep current = result.getHead();
                while (current != null) {
                    System.out.println(current);
                    current = current.getNext();
                }
            } else {
                System.out.println("Parsing failed.");
            }
        } catch (Exception e) {
            System.err.println("Error parsing file: " + e.getMessage());
        }
    }
}
