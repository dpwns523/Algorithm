import java.io.*;
import java.util.*;

public class Main {
    public static int[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> lower = new ArrayList<>();
        ArrayList<ArrayList<Integer>> higher = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            lower.add(new ArrayList<>());
            higher.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());

            higher.get(a).add(b);
            lower.get(b).add(a);
        }
        visited = new int[n+1];
        visited[0] = 1;
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            visited[i] = 1;
            dfs(i, lower);
            dfs(i, higher);
            if(Arrays.stream(visited).allMatch(v -> v==1)) cnt++;
            Arrays.fill(visited, 1, n+1, 0);
        }
        System.out.println(cnt);
        br.close();
    }

    public static void dfs(int start, ArrayList<ArrayList<Integer>> graph) {
        for(int next : graph.get(start)) {
            if(visited[next] == 1) continue;
            visited[next] = 1;
            dfs(next, graph);
        }
    }
}