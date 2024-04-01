import javax.print.DocFlavor;
import java.util.Objects;

public class Lose {
    public int loseGame(String[][] matrix){
        for (int i = 0; i < 12; i++) {
                if (fullStar(matrix, i)) {
                    return i;
                }
            }
            return -2;
    }
    private boolean fullStar(String[][] matrix, int i) {
        if (!matrix[5][i].equals("*")) {
            return false;
        }
        return true;
    }
}