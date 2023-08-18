import java.util.*;
import java.io.*;

public class Main {
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static int[] visited;
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
        visited = new int[n+1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (int i = 1; i <= n; i++) {
            Collections.sort(graph.get(i));
        }

        visited[v] = 1;
        sb.append(v).append(" ");
        dfs(v);
        sb.append("\n");
        Arrays.fill(visited, 0);
        visited[v] = 1;
        bfs(v);
        System.out.println(sb);
        br.close();
    }
    public static void dfs(int start) {
        for(int next : graph.get(start)) {
            if(visited[next] == 1) continue;
            visited[next] = 1;
            sb.append(next).append(" ");
            dfs(next);
        }
    }

    public static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        sb.append(start).append(" ");
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int next : graph.get(node)) {
                if(visited[next] == 1) continue;
                visited[next] = 1;
                sb.append(next).append(" ");
                queue.offer(next);
            }
        }
    }
}
