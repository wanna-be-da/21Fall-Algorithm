import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

class Solution1 {

    static int V; //number of Vertices
    static int E; //number of Edges
    static int[][][] shortestPath; //Shortest path Graph
    static int M = 3000000;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("input1.txt"));
        StringTokenizer stk;
        PrintWriter pw = new PrintWriter("output1.txt");

        for (int test_case = 1; test_case <= 10; test_case++) {

            stk = new StringTokenizer(br.readLine());
            V = Integer.parseInt(stk.nextToken());
            E = Integer.parseInt(stk.nextToken());
            shortestPath = new int[V+1][V+1][V+1];

            //initialize big number
            for (int i=1; i<=V; i++){
                for (int j=1; j<=V; j++) {
                    if (i == j) {
                        shortestPath[i][j][0] = 0;
                    } else {
                        shortestPath[i][j][0] = M;
                    }
                }
            }

            stk = new StringTokenizer(br.readLine());
            for (int i=1; i<=E; i++){
                shortestPath[Integer.parseInt(stk.nextToken())][Integer.parseInt(stk.nextToken())][0] = Integer.parseInt(stk.nextToken());
            }

            ////////////////////////Setting finished////////////////////////


            for (int k=1; k<=V; k++){
                for (int i=1; i<=V; i++){
                    for (int j=1; j<=V; j++){
                        if (i == j){
                            shortestPath[i][j][k] = 0;
                            continue;
                        }
                        if (shortestPath[i][k][k-1]!=M && shortestPath[k][j][k-1]!=M){
                            shortestPath[i][j][k] = Integer.min(shortestPath[i][j][k-1], shortestPath[i][k][k-1] + shortestPath[k][j][k-1]);
                        } else {
                            shortestPath[i][j][k] = shortestPath[i][j][k-1];
                        }
                    }
                }
            }

            answer = 0;
            for (int i=1; i<=V; i++){
                for (int j=1; j<=V; j++){
                    if (shortestPath[i][j][V]!=M){
                        answer += shortestPath[i][j][V];
                    }
                }
            }
            pw.println("#" + test_case + " " + answer);
            pw.flush();
        }

        br.close();
        pw.close();
    }
}