import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Tris {

    private static final ConcurrentHashMap<Integer, Character> trisMap = new ConcurrentHashMap<>();

    private static final int[][] condizioniDiVittoria = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };

    private static void fillMap(){
        for(int i = 0; i<9; i++)
            trisMap.put(i, '-');
    }

    private static void clearscreen(){
        for(int i=0;i<20;i++){
            System.out.println("");
        }
    }

    private static void printMap(ConcurrentHashMap<Integer, Character> map){
        clearscreen();
        for(int i = 0; i < map.size(); i++){
            if((i+1) % 3 == 0) {
                System.out.print(map.get(i) + "\n");
            } else {
                System.out.print(map.get(i) + "   ");
            }
        }
    }

    private static char[] getGameBoard(){
        char[] gameBoard = new char[9];
        for (int i = 0; i < 9; i++) {
            gameBoard[i] = trisMap.get(i);
        }

        return gameBoard;
    }

    private static boolean isGameBoardEmpty(char[] gameBoard){
        for (int i = 0; i < 9; i++) {
            if (gameBoard[i] == '-') {
                return false;
            }
        }
        return true;
    }

    private static boolean isFinished(char c) {
        char[] gameBoard = getGameBoard();

        if(isGameBoardEmpty(gameBoard))
            return false;

        for (int[] combinazione : condizioniDiVittoria) {
            if (gameBoard[combinazione[0]] != '-' &&
                    gameBoard[combinazione[0]] == gameBoard[combinazione[1]] &&
                    gameBoard[combinazione[0]] == gameBoard[combinazione[2]]) {
                System.out.println("Tris! ha vinto il giocatore col simbolo: '" + c + "'");
                return true;
            }
        }

        System.out.println("Pareggio!");
        return true;
    }

    public static void main(String[] args) {
        fillMap();
        printMap(trisMap);

        Scanner scn = new Scanner(System.in);
        int n;
        boolean finish;
        char toSet = 'X';
        do {
            do {
                System.out.print("\nInserisci un numero: ");
                n = scn.nextInt();
            } while (!trisMap.get(n-1).equals('-'));

            trisMap.put(n-1, toSet);

            finish = isFinished(toSet); //check if finished
            
            //change player
            if(toSet == 'X'){
                toSet = 'O';
            } else toSet = 'X';
            
            printMap(trisMap);
            
        } while(!finish);
    }
}
