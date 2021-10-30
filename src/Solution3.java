import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

/*
   1. Compile the file with the following command. The class file named Solution3 would be created when you compile the source code.
       javac Solution3.java -encoding UTF8

   2. Run the compiled program with the following command. Output file(output.txt) should be created after the program is finished
       java Solution3

   - The encoding of the source code should be UTF8
   - You can use the 'time' command to measure your algorithm.
       time java Solution3
   - You can also halt the program with the 'timeout' command.
       timeout 0.5 java Solution3
       timeout 1 java Solution3
 */

class Solution3 {
	static final int max_n = 1000000;

	static int n;
	static int[][] A = new int[3][max_n];
	static int Answer;

	public static void main(String[] args) throws Exception {
		/*
		   Read the input from input.txt
		   Write your answer to output.txt
		 */
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		StringTokenizer stk;
		PrintWriter pw = new PrintWriter("output.txt");

		for (int test_case = 1; test_case <= 10; test_case++) {
			
			stk = new StringTokenizer(br.readLine());
			n = Integer.parseInt(stk.nextToken());
			for (int i = 0; i < 3; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					A[i][j] = Integer.parseInt(stk.nextToken());
				}
			}


			/////////////////////////////////////////////////////////////////////////////////////////////
			int[][] case1 = new int[n][3];
			case1[0][0] = A[0][0] - A[2][0];
			case1[0][1] = A[2][0] - A[1][0];
			case1[0][2] = A[1][0] - A[0][0];

			for (int i = 1; i<n; i++) {
				if (case1[i - 1][0] >= case1[i - 1][1]) {
					if (case1[i - 1][1] >= case1[i - 1][2]) { //a>b>c
						case1[i][0] = A[0][i] - A[2][i] + case1[i - 1][1];
						case1[i][1] = A[2][i] - A[1][i] + case1[i - 1][0];
						case1[i][2] = A[1][i] - A[0][i] + case1[i - 1][0];
					} else {
						if (case1[i - 1][0] >= case1[i - 1][2]) { //a>c>b
							case1[i][0] = A[0][i] - A[2][i] + case1[i - 1][2];
							case1[i][1] = A[2][i] - A[1][i] + case1[i - 1][0];
							case1[i][2] = A[1][i] - A[0][i] + case1[i - 1][0];
						} else { //c>a>b
							case1[i][0] = A[0][i] - A[2][i] + case1[i - 1][2];
							case1[i][1] = A[2][i] - A[1][i] + case1[i - 1][2];
							case1[i][2] = A[1][i] - A[0][i] + case1[i - 1][0];
						}
					}
				} else { //b>a
					if (case1[i - 1][0] >= case1[i - 1][2]) { //b>a>c
						case1[i][0] = A[0][i] - A[2][i] + case1[i - 1][1];
						case1[i][1] = A[2][i] - A[1][i] + case1[i - 1][0];
						case1[i][2] = A[1][i] - A[0][i] + case1[i - 1][1];
					} else {
						if (case1[i - 1][1] >= case1[i - 1][2]) { // b>c>a
							case1[i][0] = A[0][i] - A[2][i] + case1[i - 1][1];
							case1[i][1] = A[2][i] - A[1][i] + case1[i - 1][2];
							case1[i][2] = A[1][i] - A[0][i] + case1[i - 1][1];
						} else { //c>b>a
							case1[i][0] = A[0][i] - A[2][i] + case1[i - 1][2];
							case1[i][1] = A[2][i] - A[1][i] + case1[i - 1][2];
							case1[i][2] = A[1][i] - A[0][i] + case1[i - 1][1];
						}
					}
				}
			}

			int[][] case2 = new int[n][3];
			case2[0][0] = A[0][0] - A[1][0];
			case2[0][1] = A[1][0] - A[2][0];
			case2[0][2] = A[2][0] - A[0][0];

			for (int i = 1; i<n; i++) {
				if (case2[i - 1][0] >= case2[i - 1][1]) {
					if (case2[i - 1][1] >= case2[i - 1][2]) { //a>b>c
						case2[i][0] = A[0][i] - A[1][i] + case2[i - 1][1];
						case2[i][1] = A[1][i] - A[2][i] + case2[i - 1][0];
						case2[i][2] = A[2][i] - A[0][i] + case2[i - 1][0];
					} else {
						if (case2[i - 1][0] >= case2[i - 1][2]) { //a>c>b
							case2[i][0] = A[0][i] - A[1][i] + case2[i - 1][2];
							case2[i][1] = A[1][i] - A[2][i] + case2[i - 1][0];
							case2[i][2] = A[2][i] - A[0][i] + case2[i - 1][0];
						} else { //c>a>b
							case2[i][0] = A[0][i] - A[1][i] + case2[i - 1][2];
							case2[i][1] = A[1][i] - A[2][i] + case2[i - 1][2];
							case2[i][2] = A[2][i] - A[0][i] + case2[i - 1][0];
						}
					}
				} else { //b>a
					if (case2[i - 1][0] >= case2[i - 1][2]) { //b>a>c
						case2[i][0] = A[0][i] - A[1][i] + case2[i - 1][1];
						case2[i][1] = A[1][i] - A[2][i] + case2[i - 1][0];
						case2[i][2] = A[2][i] - A[0][i] + case2[i - 1][1];
					} else {
						if (case2[i - 1][1] >= case2[i - 1][2]) { // b>c>a
							case2[i][0] = A[0][i] - A[1][i] + case2[i - 1][1];
							case2[i][1] = A[1][i] - A[2][i] + case2[i - 1][2];
							case2[i][2] = A[2][i] - A[0][i] + case2[i - 1][1];
						} else { //c>b>a
							case2[i][0] = A[0][i] - A[1][i] + case2[i - 1][2];
							case2[i][1] = A[1][i] - A[2][i] + case2[i - 1][2];
							case2[i][2] = A[2][i] - A[0][i] + case2[i - 1][1];
						}
					}
				}
			}

			int ans1 = case1[n-1][0];
			int ans2 = case2[n-1][0];
			for (int i = 1; i<3; i++){
				if (case1[n-1][i] > ans1){
					ans1 = case1[n-1][i];
				}
				if (case2[n-1][i] > ans2){
					ans2 = case2[n-1][i];
				}
			}
			if (ans2 > ans1){
				ans1 = ans2;
			}
			/////////////////////////////////////////////////////////////////////////////////////////////
			Answer = ans1;


			// Print the answer to output.txt
			pw.println("#" + test_case + " " + Answer);
			// To ensure that your answer is printed safely, please flush the string buffer while running the program
			pw.flush();
		}

		br.close();
		pw.close();
	}
}

