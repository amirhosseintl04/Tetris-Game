import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean cont = false;
        boolean loost = false;
        while (!cont){
            SetBoard table = new SetBoard();
            BuildBlock block = new BuildBlock();
            Lose lost = new Lose();
            Move move = new Move();
            Score scr = new Score();
            PrintMat chap = new PrintMat();
            while (Score.emtiaz< 1000 && scr.getEmtiaz() >= 0) {
                block.buil(table.matrix);
                Move.e= Move.y = 0;
                Move.mov = Move.stuck = false;
                chap.clearConsole();
                chap.outPut(table.matrix);
                int[] cord_i = BuildBlock.getCord_i(table.matrix);
                int[] cord_j = BuildBlock.getCord_j(table.matrix);
                move.move(table.matrix, cord_i, cord_j);
                scr.increaseScore(table.matrix);
                scr.decreaseScore(table.matrix);
            }
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 12; j++) {
                    table.matrix[i][j] = " ";
                }
            }
            chap.clearConsole();
            chap.outPut(table.matrix);
            if (scr.getEmtiaz() >= 1000) {
                System.out.println("YOU WON!");
                System.out.println("Your score: " + scr.getEmtiaz());
            }
            if (scr.getEmtiaz() < 0) {
                System.out.println("YOU LOST!");
            }
            System.out.print("Do you want to continue? (YES/NO) ");
            String conti = input.next().toUpperCase();
            Score.emtiaz = Move.e= Move.y = 0;
            if (conti.equalsIgnoreCase("NO")){
                cont = true;
            }
        }
    }
}
