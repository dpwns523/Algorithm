import java.util.*;
import java.io.*;

class Main{
    public static List<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
        }
        int[] degree = new int[n+1], cost = new int[n+1], result = new int[n+1];
        for(int i=1; i<=n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            int next;
            while ((next = Integer.parseInt(st.nextToken())) != -1) {
                graph.get(next).add(i);
                degree[i]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1; i<=n; i++) {
            if (degree[i] == 0) queue.offer(i);
        }
        while(!queue.isEmpty()) {
            int a = queue.poll();
            for(int next : graph.get(a)) {
                result[next] = Math.max(result[a] + cost[a], result[next]);  // 누적값 중 오래걸리는 값으로
                if(--degree[next] == 0) queue.offer(next);
            }
        }
        for(int i=1; i<=n; i++){
            System.out.println(result[i] + cost[i]);
        }
        br.close();
    }
}
