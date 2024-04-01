public class SetBoard {
    static String[][] matrix = new String[21][12];
    public SetBoard(){
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 12; j++) {
                matrix[i][j]= " ";
            }}
        for (int i = 0; i < 12; i++) {
            matrix[20][i]= "#";
        }
        for (int i = 5; i < 21; i++) {
            matrix[i][0]= "|";
            matrix[i][11]="|";
        }
    }
}
