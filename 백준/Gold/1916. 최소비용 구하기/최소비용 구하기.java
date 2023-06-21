import java.util.*;
import java.io.*;

class Main{
    static class Node{
        int num, w;
        public Node(int num, int w){
            this.num=num; this.w=w;
        }
    }
    public static List<ArrayList<Node>> graph = new ArrayList<>();
    public static int[] distance, visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        // 그래프 초기화
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
            graph.get(s).add(new Node(e, w));
        }
        distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        visited = new int[n+1];
        // 시작점과 도착점
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken());
        // 다익스트라
        distance[start] = 0;
        dijkstra(start);
        System.out.println(distance[end]);
        br.close();
    }
    public static void dijkstra(int start) {
        Queue<Node> queue = new PriorityQueue<>((n1, n2) -> n1.w - n2.w);
        queue.offer(new Node(start, 0));
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(visited[node.num] == 1) continue;    // 방문했던 노드는 pass
            visited[node.num] = 1;
            for(Node next : graph.get(node.num)) {
                if(distance[next.num] > distance[node.num] + next.w) distance[next.num] = distance[node.num] + next.w;
                queue.offer(new Node(next.num, distance[next.num]));
            }
        }
    }
}
