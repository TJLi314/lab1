public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            runParseMode("test_inputs/t2.i");
            return;
        }

        String flag = args[0];

        switch (flag) {
            case "-h":
                printHelp();
                break;

            case "-s":
                if (args.length < 2) {
                    System.err.println("Error: Missing filename for -s option.");
                    printHelp();
                } else {
                    runScanMode(args[1]);
                }
                break;

            case "-p":
                if (args.length < 2) {
                    runParseMode(null);
                } else {
                    runParseMode(args[1]);
                }
                break;

            case "-r":
                if (args.length < 2) {
                    System.err.println("Error: Missing filename for -r option.");
                    printHelp();
                } else {
                    runIRMode(args[1]);
                }
                break;

            default:
                System.err.printf("Error: Unknown option '%s'%n", flag);
                printHelp();
        }
    }

    private static void printHelp() {
        System.out.println("Usage: 412fe [option] <filename>");
        System.out.println("Options:");
        System.out.println("  -h          Print this help message");
        System.out.println("  -s <name>   Scan file <name> and print tokens");
        System.out.println("  -p <name>   Parse file <name> and report success or errors (default mode)");
        System.out.println("  -r <name>   Parse file <name> and print intermediate representation");
    }

    private static void runScanMode(String filename) {
        
    }

    private static void runParseMode(String filename) {
        try {
            InterRep ir = Parser.parse(filename);
            if (ir != null) {
                System.out.println("Parsed Intermediate Representation:");
                InterRep current = ir;
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

    private static void runIRMode(String filename) {
        
    }
}
