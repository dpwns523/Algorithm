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
        int[] degree = new int[n+1], cost = new int[n+1];
        for(int i=1; i<=n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            int prev = Integer.parseInt(st.nextToken());
            for(int j=0; j<prev; j++){
                int pre = Integer.parseInt(st.nextToken());
                graph.get(pre).add(i);    // i작업 이전에 처리해야함
                degree[i]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        int[] result = Arrays.copyOf(cost, cost.length);
        for(int i=1; i<=n; i++) {
            if (degree[i] == 0) queue.offer(i);
        }
        while(!queue.isEmpty()) {
            int a = queue.poll();
            for(int next : graph.get(a)) {
                // a를 처리한 후 next를 처리할 수 있으며 이에 대한 최대 비용 계산(가장 오래걸리는 사전 작업)
                result[next] = Math.max(result[next], result[a] + cost[next]);
                if(--degree[next] == 0) queue.offer(next);
            }

        }
        System.out.println(Arrays.stream(result).max().getAsInt());
        br.close();
    }
}
