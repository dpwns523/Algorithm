import java.util.*;
import java.io.*;
public class Main {
    /*
         각 노드를 root로 했을 때 서브 노드가 몇개인지?
         -> 각 노드를 시작점으로 dfs를 진행하여 개수를 count할 수 있다.
     */
    public static List<ArrayList<Integer>> tree = new ArrayList<>();
    public static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken()), q = Integer.parseInt(st.nextToken());
        for(int i=0; i<=n; i++) {
            tree.add(new ArrayList<>());
        }
        for(int i=0; i<n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken()), n2 = Integer.parseInt(st.nextToken());
            tree.get(n1).add(n2);
            tree.get(n2).add(n1);
        }
        dp = new int[n+1];

        dfs(r, -1);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<q; i++) {
            int u = Integer.parseInt(br.readLine());
            sb.append(dp[u]+"\n");
        }
        System.out.print(sb);
        br.close();
    }
    public static void dfs(int root, int prev) {
        dp[root] = 1;

        for(int next : tree.get(root)) {
            if(next == prev) continue;
            dfs(next, root);
            dp[root] += dp[next];
        }
    }
}