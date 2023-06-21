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
    public static int[] d, visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());
        for(int i=0; i<=v; i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken()), n2 = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
            graph.get(n1).add(new Node(n2, w));
        }
        d = new int[v+1];
        visited = new int[v+1];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[k] = 0;    // 시작 노드
        dijkstra(k);
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=v; i++){
            if(d[i] == Integer.MAX_VALUE) sb.append("INF\n");
            else sb.append(d[i]+"\n");
        }
        System.out.print(sb);
        br.close();
    }
    public static void dijkstra(int start){
        Queue<Node> queue = new PriorityQueue<>((n1, n2) -> n1.w - n2.w);
        queue.offer(new Node(start, 0));
        while(!queue.isEmpty()){
            Node n = queue.poll();
            if(visited[n.num] == 1) continue;
            visited[n.num] = 1;
            for(Node node : graph.get(n.num)){
                if(d[node.num] > d[n.num] + node.w) d[node.num] = d[n.num] + node.w;
                // 업데이트 된 최소 비용으로 다음 노드로 간다
                queue.offer(new Node(node.num, d[node.num]));
            }
        }
    }
}
