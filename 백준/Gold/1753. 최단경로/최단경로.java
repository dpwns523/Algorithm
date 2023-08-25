import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int num, weight;
        public Node(int num, int weight) {
            this.num=num;
            this.weight=weight;
        }
    }
    public static int[] distance;
    public static List<ArrayList<Node>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v,w));
        }
        distance = new int[V+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        dijkstra(start);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            sb.append(distance[i] == Integer.MAX_VALUE ? "INF" : distance[i]).append("\n");
        }
        System.out.print(sb);
        br.close();
    }
    public static void dijkstra(int start) {
        Queue<Node> queue = new PriorityQueue<>((Node n1, Node n2) -> n1.weight - n2.weight);
        int[] visited = new int[distance.length];
        queue.offer(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if(visited[node.num] == 1) continue;
            visited[node.num] = 1;
            for (Node next : graph.get(node.num)) {
                if(distance[next.num] > distance[node.num] + next.weight) {
                    distance[next.num] = distance[node.num] + next.weight;
                    queue.offer(new Node(next.num, distance[next.num]));
                }
            }
        }
    }
}
