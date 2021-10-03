import java.util.Arrays;

public class Sorting {
    public static void main(String[] args){
        int[] arr = {1,5,6,24,2,5,1,5,5};
        //int[] sorted_arr1 = selection_sort(arr, arr.length);
        int[] sorted_arr = insertion_sort(arr, arr.length);
        //int[] sorted_arr = bubble_sort(arr, arr.length);
        System.out.println(Arrays.toString(sorted_arr));
    }

    public static int[] selection_sort(int[] A, int n){
        for (int i=n-1;i>0;i--){
            int max_value = A[0];
            int max_index = 0;
            for (int j=1;j<=i;j++){
                if (A[j]>max_value){
                    max_value = A[j];
                    max_index = j;
                }
            }
            int tmp = A[i];
            A[i] = A[max_index];
            A[max_index] = tmp;
        }
        return A;
    }

    public static int[] insertion_sort(int[] A, int n){
        for (int i=1;i<=n-1;i++){
            for (int j=0;j<i;j++){
                if(A[i]<A[j]) {
                    int temp = A[i];
                    for (int k = i - 1; k >= j; k--) {
                        A[k + 1] = A[k];
                    }
                    A[j] = temp;
                    break;
                }
            }
        }
        return A;
    }

    public static int[] bubble_sort(int[] A, int n){
        if (n==1){
            return A;
        }
        for (int i=0;i<n-1;i++){
            if(A[i]>A[i+1]){
                int temp = A[i];
                A[i] = A[i+1];
                A[i+1] = temp;
            }
        }
        A = bubble_sort(A, n-1);
        return A;
    }
}