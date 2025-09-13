public class Main {
    public static void main(String[] args) {
        try {
            Scanner myScanner = new Scanner("test_inputs/t2.i");

            Token token = myScanner.getNextToken();
            while (token.getType() != TokenType.EOF) {
                System.out.println(token);
                token = myScanner.getNextToken();
            }
            System.out.println(token); // Print EOF token
            myScanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
