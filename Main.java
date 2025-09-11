public class Main {
    public static void main(String[] args) {
        try {
            Scanner myScanner = new Scanner("test.txt");

            for (int i = 0; i < 22; i++) {
                System.out.print(myScanner.getNextToken());
            }
            System.out.println();

            myScanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(TransitionTable.transitionTable[0]['s']);
        System.out.println(TransitionTable.acceptingStates);
        System.out.println(TokenType.fromCode(11));

        TokenType t = TokenType.ADD;
        int code = t.getCode();            
        System.out.println(code);

        for (char c = '0'; c <= '9'; c++) {
            System.out.println(c);
        }
    }
}
