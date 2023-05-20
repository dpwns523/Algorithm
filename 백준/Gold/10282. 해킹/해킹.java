import java.util.*;
import java.io.*;

class Main{
    static class Node{
        int num, s;
        public Node(int num, int s){
            this.num=num; this.s=s;
        }
    }
    public static List<ArrayList<Node>> graph;
    public static int n, d, c;
    public static int[] distance, visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++){
            graph = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            distance = new int[n+1];
            visited = new int[n+1];
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[c] = 0;
            for(int j=0; j<=n; j++){
                graph.add(new ArrayList<>());
            }
            for(int j=0; j<d; j++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), s = Integer.parseInt(st.nextToken());
                graph.get(b).add(new Node(a, s));
            }
            dijkstra(c);
            System.out.println(
                    Arrays.stream(distance).filter(d -> d != Integer.MAX_VALUE).count() +" "
                            + Arrays.stream(distance).filter(d -> d != Integer.MAX_VALUE).max().getAsInt()
            );
        }
        br.close();
    }
    public static void dijkstra(int start){
        Queue<Node> queue = new PriorityQueue<>((n1, n2) -> n1.s - n2.s);
        queue.offer(new Node(start, 0));
        while(!queue.isEmpty()){
            Node root = queue.poll();
            visited[root.num] = 1;
            for(Node node : graph.get(root.num)){
                if(visited[node.num] == 0) {
                    if (distance[node.num] > root.s + node.s) {
                        distance[node.num] = root.s + node.s;
                        queue.offer(new Node(node.num, distance[node.num]));
                    }
                }
            }
        }
    }
}
