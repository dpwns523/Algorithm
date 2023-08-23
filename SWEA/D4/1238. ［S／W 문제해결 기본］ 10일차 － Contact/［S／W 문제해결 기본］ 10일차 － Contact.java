import java.io.*;
import java.util.*;

public class Solution {
    public static int[][] map = new int[101][101];
    public static int[] visited = new int[101];
    public static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= 10; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken()), start = Integer.parseInt(st.nextToken());
            Arrays.fill(visited, 0);
            for (int i = 1; i < 101; i++) {
                Arrays.fill(map[i], 0);
            }
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n/2; i++) {
                int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
                map[a][b] = 1;
            }
            bfs(start);
            sb.append("#").append(test_case).append(" ").append(max).append("\n");
        }
        System.out.print(sb);
        br.close();
    }
    public static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = 1;
        max = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            max = queue.stream().max(Integer::compare).get();
            for (int j = 0; j < size; j++) {
                int node = queue.poll();
                for (int i = 0; i < 101; i++) {
                    if(map[node][i] == 1 && visited[i] == 0) {
                        visited[i] = 1;
                        queue.offer(i);
                    }
                }
            }
            max = queue.isEmpty() ? max : 0;
        }
    }
}
