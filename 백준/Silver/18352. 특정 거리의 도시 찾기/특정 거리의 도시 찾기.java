import java.util.*;
import java.io.*;

class Main {
    static class Node {
        int num, cost;
        public Node(int num, int cost){
            this.num=num; this.cost=cost;
        }
    }
    public static List<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken()), x = Integer.parseInt(st.nextToken());
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
            graph.get(s).add(e);
        }
        int[] visited = new int[n+1];
        List<Integer> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, 0));
        visited[x] = 1;
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int cost = node.cost + 1;    // 가게 될 비용
            for(int next : graph.get(node.num)) {
                if (visited[next] == 0) {   // 방문한 적이 있다면 최단 거리가 아님
                    if (cost == k) result.add(next);
                    else queue.offer(new Node(next, cost));
                    visited[next] = 1;
                }
            }
        }
        if (result.isEmpty()) System.out.println("-1");
        else{
            Collections.sort(result);
            result.stream().forEach(r -> System.out.println(r));
        }
        br.close();
    }
}