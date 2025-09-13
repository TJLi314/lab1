public class Main {
    public static void main(String[] args) {
        try {
            long startTime = System.nanoTime(); // start timer

            Scanner myScanner = new Scanner("test_inputs/T128k.i");

            Token token = myScanner.getNextToken();
            while (token.getType() != TokenType.EOF) {
                // System.out.println(token);
                token = myScanner.getNextToken();
            }
            System.out.println(token); // Print EOF token
            myScanner.close();

            long endTime = System.nanoTime();   // end timer
            long duration = endTime - startTime; // nanoseconds
            double seconds = duration / 1_000_000_000.0;

            System.out.printf("Execution time: %d ns (%.3f s)%n", duration, seconds);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
