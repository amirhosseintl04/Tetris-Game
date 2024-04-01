public class PrintMat {
    public void outPut(String[][] matrix){
        Score scr = new Score();
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 12; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("SCORE: "+scr.getEmtiaz());
    }
    public void clearConsole() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 12; j++) {
                System.out.println();
            }
        }
    }

}
