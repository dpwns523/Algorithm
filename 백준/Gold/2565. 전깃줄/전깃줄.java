import java.util.*;
import java.io.*;
public class Main {
    static class Pair {
        int l, r;
        public Pair(int l, int r) {
            this.l=l;
            this.r=r;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Pair[] pairs = new Pair[n];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken());
            pairs[i] = new Pair(l, r);
        }
        Arrays.sort(pairs, (p1, p2) -> p1.l == p2.l ? p1.r-p2.r : p1.l-p2.l);
        int[] dp = new int[n];    // 각 전깃줄을 연결했을 때 최대로 연결가능한 개수를 기록
        Arrays.fill(dp, 1);
        for(int i=0; i<n; i++) {
            for(int j=0; j<i; j++) {
                if(pairs[i].r > pairs[j].r) dp[i] = Math.max(dp[i], dp[j]+1);
            }
        }
        System.out.println(n - Arrays.stream(dp).max().getAsInt());
        br.close();
    }
}