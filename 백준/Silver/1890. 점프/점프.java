import java.util.*;
import java.io.*;

class Main {
    /*
        시작점만 초기화해놓고 전체 탐색을 통해 추가되는 경로를 모두 합한다
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[n+1][n+1];
        dp[1][1] = 1;
        for(int i=1; i<=n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++) {
                int next = Integer.parseInt(st.nextToken());
                if(next == 0) continue;
                if(j + next <= n) dp[i][j+next] += dp[i][j];
                if(i + next <= n) dp[i+next][j] += dp[i][j];
            }
        }
        System.out.println(dp[n][n]);
        br.close();
    }
}