import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;

class Solution3 {

    static int V; //number of Vertices
    static int E; //number of Edges
    static int M = 3000000;
    static double version1StartAt;
    static double version1FinishAt;
    static double version2StartAt;
    static double version2FinishAt;
    static Edge[] EdgeSet;
    static Vertex[] Graph;

    public static class Edge {
        int from;
        int to;
        int weight;
        public Edge (int m, int n, int w){
            from = m;
            to = n;
            weight = w;
        }
    }

    public static class Edge2 {
        int to;
        int weight;
        Edge2 next;
        public Edge2 (int n, int w){
            to = n;
            weight = w;
            next = null;
        }
    }

    public static class LinkedEdge {
        private Edge2 head;
        private Edge2 last;
        public LinkedEdge() {
            head = new Edge2(0, 0);
            last = head;
        }
        public void append(int to, int weight) {
            Edge2 newEdge = new Edge2(to, weight);
            last.next = newEdge;
            last = newEdge;
        }
    }
    public static class Vertex {
        private int name;
        private LinkedEdge edges;
        public Vertex (int newname){
            name = newname;
            edges = new LinkedEdge();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("input3.txt"));
        StringTokenizer stk;
        PrintWriter pw = new PrintWriter("output3.txt");

        for (int test_case = 1; test_case <= 10; test_case++) {
            stk = new StringTokenizer(br.readLine());
            V = Integer.parseInt(stk.nextToken());
            E = Integer.parseInt(stk.nextToken());
            EdgeSet = new Edge[E];
            Graph = new Vertex[V+1];
            for (int i=1; i<=V; i++){
                Graph[i] = new Vertex(i);
            }

            stk = new StringTokenizer(br.readLine());
            for (int i=1; i<=E; i++){
                int from = Integer.parseInt(stk.nextToken());
                int to = Integer.parseInt(stk.nextToken());
                int weight = Integer.parseInt(stk.nextToken());
                EdgeSet[i-1] = new Edge(from, to, weight);

                Graph[from].edges.append(to, weight);
            }

            ////////////////////////Setting finished////////////////////////

            ///version1
            version1StartAt = System.currentTimeMillis();
            int[] d1 = new int[V+1];
            for (int i=2; i<=V; i++){
                d1[i] = M;
            }
            for (int i=1; i<=V-1; i++){
                for (int j=0; j<E; j++){
                    Edge temp = EdgeSet[j];
                    if(d1[temp.from] + temp.weight < d1[temp.to]){
                        d1[temp.to] = d1[temp.from] + temp.weight;
                    }
                }
            }
            version1FinishAt = System.currentTimeMillis();

            ///version2
            version2StartAt = System.currentTimeMillis();
            int[] d2 = new int[V+1];
            for (int i=2; i<=V; i++){
                d2[i] = M;
            }

            //번갈아가며 사용
            HashSet<Integer> updated_temp1 = new HashSet<Integer>();
            HashSet<Integer> updated_temp2 = new HashSet<Integer>();
            updated_temp1.add(1);

            for (int i=1; i<=V-1; i++){
                if (i % 2 == 1) {
                    Iterator iter = updated_temp1.iterator();
                    updated_temp2.clear();
                    while (iter.hasNext()) {
                        int from = (int) iter.next();
                        Edge2 curEdge = Graph[from].edges.head.next;
                        while (curEdge!=null){
                            if (d2[from] + curEdge.weight < d2[curEdge.to]) {
                                d2[curEdge.to] = d2[from] + curEdge.weight;
                                updated_temp2.add(curEdge.to);
                            }
                            curEdge = curEdge.next;
                        }
                    }
                } else {
                    Iterator iter = updated_temp2.iterator();
                    updated_temp1.clear();
                    while (iter.hasNext()) {
                        int from = (int) iter.next();
                        Edge2 curEdge = Graph[from].edges.head.next;
                        while (curEdge!=null){
                            if (d2[from] + curEdge.weight < d2[curEdge.to]) {
                                d2[curEdge.to] = d2[from] + curEdge.weight;
                                updated_temp1.add(curEdge.to);
                            }
                            curEdge = curEdge.next;
                        }
                    }
                }
            }
            version2FinishAt = System.currentTimeMillis();

            pw.println("#" + test_case);
            for (int i = 1; i<d1.length; i++){
                if (i==d1.length-1){
                    pw.println(d1[i]);
                    break;
                }
                pw.print(d1[i] + " ");
            }

            pw.println(version1FinishAt-version1StartAt);
            for (int i = 1; i<d2.length; i++){
                if (i==d2.length-1){
                    pw.println(d2[i]);
                    break;
                }
                pw.print(d2[i] + " ");
            }
            pw.println(version2FinishAt-version2StartAt);
            pw.flush();
        }

        br.close();
        pw.close();
    }
}