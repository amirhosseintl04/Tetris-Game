public class Score {
    public static int emtiaz = 0 ;
    public int isStar(String[][] matrix) {
        for (int i = 6; i < 20; i++) {
            if (fullStar(matrix, i)) {
                return i;
            }
        }
        return -1;
    }
    private boolean fullStar(String[][] matrix, int i) {
        for (int j = 1; j < 11; j++) {
            if (!matrix[i][j].equals("*")) {
                return false;
            }
        }
        return true;
    }
    public void increaseScore(String[][] matrix){
        int row = isStar(matrix);
        if (row != -1) {
            emtiaz += 100;
            for (int o = 1; o < 11; o++) {
                matrix[row][o] = " ";
            }
            for (int i = row - 1; i >= 5; i--) {
                for (int j = 1; j < 11; j++) {
                    matrix[i + 1][j] = matrix[i][j];
                    matrix[i][j] = " ";
                }
            }
        }
    }
    public void decreaseScore(String[][] matrix){
        Lose lose = new Lose();
        if (lose.loseGame(matrix) != -2){
            emtiaz-=10;
            int soton = lose.loseGame(matrix);
            for (int i = 5;i < 20; i++) {
                matrix[i][soton]= " ";
            }
            for ( int j = 20 ; j >= 5; j--) {
                for (int k = soton-1; k < soton+2; k++) {
                    if (matrix[j][k].equals("*")) {
                        matrix[j][k] = " ";
                        int temp= j;
                        while(matrix[++temp][k].equals(" ")){
                        }
                        matrix[--temp][k]= "*" ;
                    }
                }
            }
        }
    }

    public int getEmtiaz(){
        return emtiaz;
    }
}
