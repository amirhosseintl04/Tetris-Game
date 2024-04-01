import java.util.Arrays;
import java.util.Random;
public class BuildBlock {
    public static int rrr;
    public static int nnn;
    public void buil(String[][] matrix) {
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 12; j++) {
                matrix[i][j] = " ";
            }
        }
        int n, r;
        do {
            n = rand.nextInt(20);
            r = rand.nextInt(n + 1);
        } while ((r + n) > 6);

        for (int i = 1; i <= n + 1; i++) {
            matrix[r][i] = "*";
        }
        for (int i = 0; i <= r + 1; i++) {
            matrix[i][r + 1] = "*";
        }
        rrr = r;
        nnn = n;
    }
    public static int[] getCord_i(String[][] matrix) {
        int len = reN()+2+reR();
        int[] cord_i = new int[len];
        int h = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 12; j++) {
                if (matrix[i][j].equals("*")) {
                    cord_i[h] = i;
                    ++h;
                }
            }
        }
        for (int i = h; i < len ; i++) {
            cord_i[i]= 100;
        }
        return Arrays.copyOfRange(cord_i, 0, len);
    }

    public static int[] getCord_j(String[][] matrix) {
        int len = reN()+2+reR();
        int[] cord_j = new int[len];
        int h = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j < 12; j++) {
                if (matrix[i][j].equals("*")) {
                    cord_j[h] = j;
                    ++h;
                }
            }
        }
        return Arrays.copyOfRange(cord_j, 0, len);
    }
    public static int reN(){return nnn;}
    public static int reR(){return rrr;}
}
