import java.util.*;
import java.io.*;
public class Main {
    /*
        트리로 연결된 마을에서 현재 마을이
        1. 우수마을로 선정됐을 때
        2. 아닐 때
        를 구분해서 진행
     */
    public static List<ArrayList<Integer>> graph = new ArrayList<>();
    public static int[] resident;
    public static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        dp = new int[n+1][2];
        resident = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int i=0; i<n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken()), n2 = Integer.parseInt(st.nextToken());
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }
        dfs(1, -1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
        br.close();
    }
    public static void dfs(int s, int prev) {
        dp[s][1] = resident[s-1];
        for(int next : graph.get(s)) {
            if(next == prev) continue;
            dfs(next, s);
            dp[s][0] += Math.max(dp[next][0], dp[next][1]);
            dp[s][1] += dp[next][0];
        }
    }
}