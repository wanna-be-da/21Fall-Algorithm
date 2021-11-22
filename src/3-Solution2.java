import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashSet;

class Solution2 {

    static int V_num; //number of Vertices
    static int E_num; //number of Edges
    static Maxheap d; //cost
    static HashSet<Integer> V; //set of vertices
    static HashSet<Integer> S; //set of Tree
    static int answer;
    static int from;
    static int to;
    static int weight;

    public static class Edge {
        private int destination;
        private int weight;
        Edge next;
        public Edge (int new_destination, int new_weight){
            destination = new_destination;
            weight = new_weight;
            next = null;
        }
    }

    public static class LinkedEdge {
        private Edge head;
        private Edge last;
        public LinkedEdge() {
            head = new Edge(0, 0);
            last = head;
        }
        public void append(int destination, int weight) {
            Edge newEdge = new Edge(destination, weight);
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

    public static class ValueNode {
        private int value;
        private int node;
        public ValueNode (int v, int n){
            value = v;
            node = n;
        }
    }

    public static class Maxheap {
        private ValueNode[] Heap;
        private int[] node2index;
        private int numItem;
        public Maxheap (int n, int start) {
            Heap = new ValueNode[n+1];
            node2index = new int[n+1];
            numItem = n;

            for (int i=1; i<=n; i++){
                Heap[i] = new ValueNode(0, i);
                node2index[i] = i;
            }

            Heap[start].value = 1;
            ValueNode temp = Heap[1];
            Heap[1] = Heap[start];
            Heap[start] = temp;
            node2index[1] = start;
            node2index[start] = 1;
        }
        public int[] extractMax () {
            ValueNode max = Heap[1];
            Heap[1] = Heap[numItem];
            Heap[numItem] = max;

            int temp = node2index[Heap[1].node];
            node2index[Heap[1].node] = node2index[Heap[numItem].node];
            node2index[Heap[numItem].node] = temp;

            numItem--;
            percolateDown(1);
            return new int[] {max.value, max.node};
        }

        public void percolateDown (int index) {
            int child = 2 * index;
            int right_child = 2 * index + 1;
            if (child <= numItem){
                if (right_child <= numItem && Heap[right_child].value > Heap[child].value){
                    child = right_child;
                }
                if (Heap[index].value < Heap[child].value){
                    ValueNode temp = Heap[index];
                    Heap[index] = Heap[child];
                    Heap[child] = temp;

                    int index_temp = node2index[Heap[index].node];
                    node2index[Heap[index].node] = node2index[Heap[child].node];
                    node2index[Heap[child].node] = index_temp;

                    percolateDown(child);
                }
            }
        }
        public void percolateUp (int index) {
            int parent = index / 2;
            if (parent >= 1 && Heap[index].value > Heap[parent].value){
                ValueNode temp = Heap[index];
                Heap[index] = Heap[parent];
                Heap[parent] = temp;

                int index_temp = node2index[Heap[index].node];
                node2index[Heap[index].node] = node2index[Heap[parent].node];
                node2index[Heap[parent].node] = index_temp;

                percolateUp(parent);
            }
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("input2.txt"));
        StringTokenizer stk;
        PrintWriter pw = new PrintWriter("output2.txt");

        for (int test_case = 1; test_case <= 10; test_case++) {

            answer = 0;
            stk = new StringTokenizer(br.readLine());
            V_num = Integer.parseInt(stk.nextToken());
            E_num = Integer.parseInt(stk.nextToken());
            Vertex[] Graph;
            Graph = new Vertex[V_num+1];
            V = new HashSet<Integer>();
            S = new HashSet<Integer>();
            for (int i=1; i<=V_num; i++){
                V.add(i);
                Graph[i] = new Vertex(i);
            }

            stk = new StringTokenizer(br.readLine());
            for (int i=1; i<=E_num; i++){
                from = Integer.parseInt(stk.nextToken());
                to = Integer.parseInt(stk.nextToken());
                weight = Integer.parseInt(stk.nextToken());
                Graph[from].edges.append(to, weight);
                Graph[to].edges.append(from, weight);
            }

            ////////////////////////Setting finished////////////////////////

            int start = (int)(Math.random()* V_num) + 1; //start index
            Maxheap d = new Maxheap(V_num, start);
            while (V.size()>0){
                int[] valuenode = d.extractMax();
                S.add(valuenode[1]);
                V.remove(valuenode[1]);
                answer += valuenode[0];
                Edge curEdge = Graph[valuenode[1]].edges.head.next;
                while (curEdge != null){
                    if (V.contains(curEdge.destination) && curEdge.weight>d.Heap[d.node2index[curEdge.destination]].value){
                        d.Heap[d.node2index[curEdge.destination]].value = curEdge.weight;
                        d.percolateUp(d.node2index[curEdge.destination]);
                    }
                    curEdge = curEdge.next;
                }
            }
            answer--;

            pw.println("#" + test_case + " " + answer);
            pw.flush();
        }

        br.close();
        pw.close();
    }
}