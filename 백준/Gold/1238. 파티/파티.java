import java.util.*;
import java.io.*;

class Main{
    static class Node{
        int num, time;
        public Node(int num, int time){
            this.num=num; this.time=time;
        }
    }
    public static int[] visited, distance, cost;
    public static int n, m, x;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<ArrayList<Node>> graph = new ArrayList<>();    // x로 갈 때 경로
        List<ArrayList<Node>> reverseGraph = new ArrayList<>();    // x에서 집으로 갈 때 경로
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, t));
            reverseGraph.get(b).add(new Node(a, t));
        }
        cost = new int[n+1];
        distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[x] = 0;
        visited = new int[n+1];
        dijkstra(x, graph);
        for(int i=1; i<=n; i++){
            cost[i] = distance[i];
        }
        distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[x] = 0;
        visited = new int[n+1];
        dijkstra(x, reverseGraph);
        for(int i=1; i<=n; i++){
            cost[i] += distance[i];
        }
        System.out.println(Arrays.stream(cost).max().getAsInt()+"");

        br.close();
    }

    public static void dijkstra(int start, List<ArrayList<Node>> graph){
        Queue<Node> queue = new PriorityQueue<>((n1, n2) -> n1.time - n2.time);
        queue.offer(new Node(start, 0));
        while(!queue.isEmpty()){
            Node node = queue.poll();
            visited[node.num] = 1;
            for(Node next : graph.get(node.num)){
                if(distance[next.num] > node.time + next.time){
                    distance[next.num] = node.time + next.time;
                    queue.offer(new Node(next.num, distance[next.num]));
                }
            }
        }
    }
}
