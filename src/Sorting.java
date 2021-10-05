import java.util.Arrays;

public class Sorting {
    public static void main(String[] args){
        int[] arr = {1,5,6,24,2,5,1,5,5};
        //int[] sorted_arr1 = selection_sort(arr, arr.length);
        //int[] sorted_arr = insertion_sort(arr, arr.length);
        //int[] sorted_arr = bubble_sort(arr, arr.length);
        //int[] sorted_arr = shell_sort(arr, arr.length);
        //merge_sort(arr,0, arr.length-1);
        quick_sort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
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

    public static int[] shell_sort(int[] A, int n){
        //upgraded version of insertion_sort
        int[] step ={1,3,7};
        for (int i = step.length-1; i>= 0 ; i--){
            step_insertion_sort(A, n, step[i]);
        }
        return A;
    }

    public static void step_insertion_sort(int[] A, int n, int step){
        for (int i = 0; i<step; i++){
            for (int j = i + step; j < n; j=j+step){
                //insert A[j] in right place
                for (int k = i; k<=j;k=k+step){
                    if (A[j]<A[k]){
                        //right shift and put it
                        int temp = A[j];
                        for (int m = j-step; m>=k; m = m-step){
                            A[m+step] = A[m];
                        }
                        A[k] = temp;
                        break;
                    }
                }
            }
        }
    }

    public static void merge_sort(int[] A, int p, int r){
        if (r > p) {
            int q = (r + p) / 2;
            merge_sort(A, p, q);
            merge_sort(A, q+1, r);
            merge(A, p, q, r);
        }
    }

    public static void merge(int[] A, int p, int q, int r){
        // A[p...q] merge A[q+1,...,r]
        int[] B = new int[r-p+1];
        //set 3 pointers
        int idx1 = p;
        int idx2 = q+1;
        int idx3 = 0;
        while (idx1 < q+1 && idx2 < r+1){
            if (A[idx1]<A[idx2]){
                B[idx3++] = A[idx1++];
            } else {
                B[idx3++] = A[idx2++];
            }
        }

        if (idx1 == q+1) {
            while (idx2 < r+1){
                B[idx3++] = A[idx2++];
            }
        } else if (idx2 == r+1){
            while (idx1 < q+1){
                B[idx3++] = A[idx1++];
            }
        }
        while (idx3 >0){
            A[r] = B[--idx3];
            r--;
        }
    }

    public static void quick_sort(int[] A, int p, int r){
        if (p<r) {
            int q = partition(A, p, r);
            quick_sort(A, p, q - 1);
            quick_sort(A, q + 1, r);
        }
    }

    public static int partition(int A[], int p, int r){
        int pivot = A[r];
        int i = p-1; //end of zone1
        for (int j=p;j<=r-1;j++) {
            if (A[j] < pivot) {
                int temp = A[++i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        A[r] = A[i+1];
        A[i+1] = pivot;

        return i+1;
    }

    //public static void heap_sort(int[] A)
}