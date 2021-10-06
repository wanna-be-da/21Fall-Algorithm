

public class DynamicProgramming {
    public static void main(String[] args){
        int[][] matrix = {{6,7,12,5},{5,3,11,18},{7,17,3,3},{8,10,14,9}};
        System.out.println(matrixPath(matrix, 1, 3));
    }

    public static int matrixPath(int[][] matrix, int i, int j){
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] c = new int[matrix.length+1][matrix[0].length+1];
        for (int m=0;m<=row;m++){
            c[m][0] = 0;
        }
        for (int n=0;n<=col;n++){
            c[0][n] = 0;
        }
        for (int m=1;m<=row;m++){
            for (int n=1;n<=col;n++){
                c[m][n] = matrix[m-1][n-1] + max(c[m-1][n], c[m][n-1]);
            }
        }
        return c[i][j];
    }

    private static int max(int i, int j) {
        if(i>j){
            return i;
        } else {
            return j;
        }
    }
}
