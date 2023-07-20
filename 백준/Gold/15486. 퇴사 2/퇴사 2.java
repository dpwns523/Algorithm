import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] work = new int[n+1][2];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken()), p = Integer.parseInt(st.nextToken());
            work[i][0] = t;
            work[i][1] = p;
        }
        int max = 0;
        int[] dp = new int[n+1];
        for(int i=0; i<=n; i++) {
            int next = i + work[i][0];  // 다음 상담을 잡을 수 있는 날 -> 0일날 1일 상담을 잡으면 1,2,3일을 상담함
            max = Math.max(max, dp[i]);     // i날 전에 끝나는 최대 상담이득 값이 존재할 수 있음
            if(next <= n) { // 오늘 상담을 잡으면 마지막 날까지의 값이 최대값이 되게 함
                dp[next] = Math.max(dp[next], max + work[i][1]);
            }
        }
        System.out.println(max);
        br.close();
    }
}