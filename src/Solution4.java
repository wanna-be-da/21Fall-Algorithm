import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

/*
   1. Compile the file with the following command. The class file named Solution4 would be created when you compile the source code.
       javac Solution4.java -encoding UTF8

   2. Run the compiled program with the following command. Output file(output.txt) should be created after the program is finished
       java Solution4

   - The encoding of the source code should be UTF8
   - You can use the 'time' command to measure your algorithm.
       time java Solution4
   - You can also halt the program with the 'timeout' command.
       timeout 0.5 java Solution4
       timeout 1 java Solution4
 */

class Solution4 {
	static final int max_n = 1000;

	static int n, H;
	static int[] h = new int[max_n], d = new int[max_n-1];
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
			/*
			   n is the number of blocks, and H is the max tower height
			   read each height of the block to h[0], h[1], ... , h[n-1]
			   read the heights of the holes to d[0], d[1], ... , d[n-2]
			 */
			stk = new StringTokenizer(br.readLine());
			n = Integer.parseInt(stk.nextToken()); H = Integer.parseInt(stk.nextToken());
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				h[i] = Integer.parseInt(stk.nextToken());
			}
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < n-1; i++) {
				d[i] = Integer.parseInt(stk.nextToken());
			}


			/////////////////////////////////////////////////////////////////////////////////////////////
			int[] prev_sol  = new int[H+1];
			int[] prev_prev_cum =  new int[H+1];

			for (int i=h[0]; i<=H; i++){ //1단계
				prev_prev_cum[i] = 1;
			}
			for (int i=h[1]; i<=H; i++){ //2단계
				prev_sol[i] = 1 + prev_prev_cum[i-h[1]+d[0]];
			}
			for (int j=2; j<n; j++){ //3단계 ~
				int[] temp = new int[H+1];
				for (int i=h[j]; i<=H; i++){
					temp[i]++;
				}
				for (int i=h[j]-d[j-1]+1; i<=H; i++){
					temp[i] += (prev_sol[i-h[j]+d[j-1]] % 1000000);
				}
				for (int i=h[j]+1;i <=H; i++){
					temp[i] += (prev_prev_cum[i-h[j]] % 1000000);
				}
				for (int i=1; i<=H; i++){
					prev_prev_cum[i] += (prev_sol[i] % 1000000);
				}
				for (int i=1; i<=H; i++){
					prev_sol[i] = (temp[i] % 1000000);
				}
			}

//			for (int i=1; i<n; i++){
//				for (int j=h[i]; j<=H; j++){
//					sol[i][j] = 1 + sol[i-1][j-h[i]+d[i-1]];
//					for (int k=i-2; k>=0; k--){
//						sol[i][j] += sol[k][j-h[i]];
//					}
//				}
//			}
//
//			int ans = sol[n-1][H];

			/////////////////////////////////////////////////////////////////////////////////////////////
			Answer = (prev_sol[H] + prev_prev_cum[H]) % 1000000;


			// Print the answer to output.txt
			pw.println("#" + test_case + " " + Answer);
			// To ensure that your answer is printed safely, please flush the string buffer while running the program
			pw.flush();
		}

		br.close();
		pw.close();
	}
}

