import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Move {
    PrintMat chap = new PrintMat();
    Score scr = new Score();
    static int e = 0;
    static boolean stuck = false;
    int counter = 0;
    static int y = 0;
    Scanner input = new Scanner(System.in);
    static boolean mov;

    public void move(String[][] matrix, int[] cord_i, int[] cord_j) {

        mov = true;
        while (mov) {
            switch (input.next().toUpperCase()) {
                case "A"://goLeft
//                    if (checkLastDown(matrix, cord_i, cord_j)) {
                        moveLeft(matrix, cord_i, cord_j);
                        moveDown(matrix, cord_i, cord_j);
//                    }
//                    else if (!checkLastDown(matrix, cord_i, cord_j)) {
//                        moveLeft(matrix, cord_i, cord_j);
//                    }
                    if (stuck) {
                        mov = stuck = false;
                    }
                    if (Score.emtiaz>= 1000 || Score.emtiaz < 0){
                        mov = false;
                    }
                    chap.clearConsole();
                    chap.outPut(matrix);
                    break;
                case "D"://goRight
//                    if (checkLastDown(matrix, cord_i, cord_j)) {
                        moveRight(matrix, cord_i, cord_j);
                        moveDown(matrix, cord_i, cord_j);
//                    }
//                    else if (!checkLastDown(matrix, cord_i, cord_j)) {
//                        moveRight(matrix, cord_i, cord_j);
//                    }
                    if (stuck) {
                        mov = stuck = false;
                    }
                    if (Score.emtiaz>= 1000 || Score.emtiaz < 0){
                        mov = false;
                    }
                    chap.clearConsole();
                    chap.outPut(matrix);
                    break;
                case "S"://goDown
                    moveDown(matrix, cord_i, cord_j);
                    if (stuck) {
                        mov = stuck = false;
                    }
                    if (Score.emtiaz>= 1000 || Score.emtiaz < 0){
                        mov = false;
                    }
                    chap.clearConsole();
                    chap.outPut(matrix);
                    break;
                case "W"://rotateBlock
                    if (canRotate(matrix, cord_i, cord_j)) {
                        rotateBlock(matrix, cord_i, cord_j);
                        moveDown(matrix, cord_i, cord_j);
                    }else {
                        newBlock(matrix, cord_i, cord_j);
                    }
                    if (Score.emtiaz>= 1000 || Score.emtiaz < 0){
                        mov = false;
                    }
                    chap.clearConsole();
                    chap.outPut(matrix);
                    break;
                case "Q":
                    mov = stuck = false;
                    break;
                default:
                    System.out.println("wrong!");
            }
        }
    }

    private void lastBlock(String[][] matrix, int[] cord_i, int[] cord_j) {
        int len = BuildBlock.reN() + BuildBlock.reR() + 2;
        for (int i = 0; i < len; i++) {
            if (cord_i[i] != 100 && cord_j[i] != 100)
                matrix[cord_i[i] + e][cord_j[i] + y] = " ";
        }
    }
    private void newBlock(String[][] matrix, int[] cord_i, int[] cord_j) {
        int len = BuildBlock.reN() + BuildBlock.reR() + 2;
        for (int i = 0; i < len; i++) {
            if (cord_i[i] != 100 && cord_j[i] != 100)
                matrix[cord_i[i] + e][cord_j[i] + y] = "*";
        }
    }

    private void moveLeft(String[][] matrix, int[] cord_i, int[] cord_j){
        lastBlock(matrix, cord_i, cord_j);
        y--;
        if (!check(matrix, cord_i, cord_j)) {
            y++;
        }
        if (!canLeft(matrix, cord_i, cord_j)) {
            y++;
            mov = false;
            scr.decreaseScore(matrix);
            scr.increaseScore(matrix);
        }
        if (y<0){
            y++;
        }
        newBlock(matrix, cord_i, cord_j);

    }

    private void moveRight(String[][] matrix, int[] cord_i, int[] cord_j){
        lastBlock(matrix, cord_i, cord_j);
        y++;
        if (!check(matrix, cord_i, cord_j)) {
            y--;
        }
        if (!canRight(matrix, cord_i, cord_j)) {
            y--;
            scr.decreaseScore(matrix);
            scr.increaseScore(matrix);
            mov = false;
        }
        newBlock(matrix, cord_i, cord_j);
    }

    private void moveDown(String[][] matrix, int[] cord_i, int[] cord_j){
        lastBlock(matrix, cord_i, cord_j);
        e++;
        if (!checkDown(matrix, cord_i, cord_j)) {
            e--;
            stuck = true;
        }
        if (canDown(matrix, cord_i, cord_j)) {
            e--;
            mov = false;
            scr.decreaseScore(matrix);
            scr.increaseScore(matrix);
        }
        newBlock(matrix, cord_i, cord_j);
    }

    private void rotateBlock(String[][] matrix, int[] cord_i, int[] cord_j) {
        int len = BuildBlock.reN() + BuildBlock.reR() + 2;
        lastBlock(matrix, cord_i, cord_j);
        int center_i = 0;
        int center_j = 0;
        int validCount = 0;

        for (int i = 0; i < len; i++) {
            if (cord_i[i] != 100 && cord_j[i] != 100) {
                center_i += cord_i[i];
                center_j += cord_j[i];
                validCount++;
            }
        }
        if (validCount > 0) {
            center_i /= validCount;
            center_j /= validCount;
            for (int i = 0; i < len; i++) {
                if (cord_i[i] != 100 && cord_j[i] != 100) {
                    int delta_i = cord_i[i] - center_i;
                    int delta_j = cord_j[i] - center_j;
                    int newRow = center_i - delta_j;
                    int newCol = center_j + delta_i;
                    cord_i[i] = newRow;
                    cord_j[i] = newCol;
                }
            }
            newBlock(matrix, cord_i, cord_j);
        }
    }

    public boolean check(String[][] matrix, int[] cord_i, int[] cord_j) {
        int len = BuildBlock.reN() + BuildBlock.reR() + 2;
        boolean check = true;
        for (int i = 0; i < len; i++) {
            if (cord_i[i] != 100 && cord_j[i] != 100) {
                if (matrix[cord_i[i] + e][cord_j[i] + y].equals("|")) {
                    check = false;
                }
                if ((cord_j[i] + y) > 10) {
                    check = false;
                }
            }
        }
        return check;
    }

    public boolean checkDown(String[][] matrix, int[] cord_i, int[] cord_j) { //yo can not move anymore
        int len = BuildBlock.reN() + BuildBlock.reR() + 2;
        boolean check = true;
        for (int i = 0; i < len; i++) {
            if (cord_i[i] != 100 && cord_j[i] != 100)
                if (matrix[cord_i[i] + e][cord_j[i] + y].equals("#")) {
                    check = false;
                }
        }
        return check;
    }

    public boolean checkLastDown(String[][] matrix, int[] cord_i, int[] cord_j) { //when still you can move right and left
        int len = BuildBlock.reN() + BuildBlock.reR() + 2;
        boolean checklast = true;
        for (int i = 0; i < len; i++) {
            if (cord_i[i] != 100 && cord_j[i] != 100)
                if (matrix[cord_i[i] + e + 1][cord_j[i] + y].equals("#")) {
                    checklast = false;
                }
        }
        return checklast;
    }

    public boolean canDown(String[][] matrix, int[] cord_i, int[] cord_j) {
        int len = BuildBlock.reN() + BuildBlock.reR() + 2;
        for (int i = 0; i < len; i++) {
            if (cord_i[i] != 100 && cord_j[i] != 100) {
                int nextRow = cord_i[i] + e;
                int nextCol = cord_j[i] + y;
                if (nextRow >= matrix.length || matrix[nextRow][nextCol].equals(" ") || matrix[nextRow][nextCol].equals("|")) {
                    continue;
                } else if (matrix[nextRow][nextCol].equals("*")) {
                    return true;
                } else if (matrix[nextRow][nextCol].equals("|")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canLeft(String[][] matrix, int[] cord_i, int[] cord_j) {
        int len = BuildBlock.reN() + BuildBlock.reR() + 2;
        for (int i = 0; i < len; i++) {
            if (cord_i[i] != 100 && cord_j[i] != 100) {
                int nextRow = (cord_i[i] + e);
                int nextCol = (cord_j[i] + y);
                if (nextCol < 0 || matrix[nextRow][nextCol].equals(" ") || matrix[nextRow][nextCol].equals("|")) {
                    continue;
                } else if ( matrix[nextRow][nextCol].equals("*")) {
                    return false;
                }else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean canRight(String[][] matrix, int[] cord_i, int[] cord_j) {
        int len = BuildBlock.reN() + BuildBlock.reR() + 2;
        for (int i = 0; i < len; i++) {
            if (cord_i[i] != 100 && cord_j[i] != 100) {
                int nextRow = cord_i[i] + e;
                int nextCol = cord_j[i] + y;
                if (nextCol >= matrix[0].length || matrix[nextRow][nextCol].equals(" ") || matrix[nextRow][nextCol].equals("|")) {
                    continue;
                } else if (matrix[nextRow][nextCol].equals("*")) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean canRotate(String[][] matrix, int[] cord_i, int[] cord_j){
        int len = BuildBlock.reN() + BuildBlock.reR() + 2;
        int center_i = 0;
        int center_j = 0;
        int validCount = 0;
        lastBlock(matrix, cord_i, cord_j);
        for (int i = 0; i < len; i++) {
            if (cord_i[i] != 100 && cord_j[i] != 100) {
                center_i += cord_i[i];
                center_j += cord_j[i];
                validCount++;
            }
        }
        if (validCount > 0) {
            center_i /= validCount;
            center_j /= validCount;
            for (int i = 0; i < len; i++) {
                if (cord_i[i] != 100 && cord_j[i] != 100) {
                    int delta_i = cord_i[i] - center_i;
                    int delta_j = cord_j[i] - center_j;
                    int newRow = center_i - delta_j + e;
                    int newCol = center_j + delta_i + y;
                    if (newRow <= 0 || newRow >= 21|| newCol <= 0|| newCol >= 12) {
                        return false;
                    }
                    if (matrix[newRow][newCol].equals("|") || matrix[newRow][newCol].equals("*")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}