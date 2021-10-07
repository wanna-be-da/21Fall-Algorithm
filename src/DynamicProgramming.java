

public class DynamicProgramming {
    public static void main(String[] args){
        int[][] matrix = {{6,7,12,5},{5,3,11,18},{7,17,3,3},{8,10,14,9}};
        int[][] weight = {{6,7,12,-5,5,3,11,3},{-8,10,14,9,7,13,8,5},{11,12,7,4,8,-2,9,4}};
        System.out.println(pebbleSum(weight, 2));
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
                c[m][n] = matrix[m-1][n-1] + max2(c[m-1][n], c[m][n-1]);
            }
        }
        return c[i][j];
    }

    private static int pebble(int[][] w, int i, int p){
        int[][] c = new int[w[0].length + 1][4];
        //put c[0][0] ... c[0][3], pattern 0,1,2,3
        c[0][0] = w[0][0];
        c[0][1] = w[1][0];
        c[0][2] = w[2][0];
        c[0][3] = w[0][0] + w[2][0];

        //put things
        for (int m = 1; m<=w.length; m++){
            int [] prev_arr = {c[m-1][0], c[m-1][1], c[m-1][2], c[m-1][3]};
            int prev_max = max(prev_arr);
            c[m][0] = w[0][m] + prev_max;
            c[m][1] = w[1][m] + prev_max;
            c[m][2] = w[2][m] + prev_max;
            c[m][3] = w[0][m] + w[2][m] + prev_max;
        }

        return c[i-1][p-1];
    }

    public static int pebbleSum(int[][] w, int n){
        int [] pebbles = {pebble(w, n, 1), pebble(w, n, 2), pebble(w, n, 3), pebble(w, n, 4)};
        return max(pebbles);
    }

    private static int max(int[] arr) {
        int result = arr[0];
        for (int i=1;i<arr.length;i++){
            if(arr[i]>result){
                result = arr[i];
            }
        }
        return result;
    }

    private static int max2(int i, int j) {
        if (i > j) {
            return i;
        } else {
            return j;
        }
    }


}
