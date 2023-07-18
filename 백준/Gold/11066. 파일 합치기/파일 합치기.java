import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int T=0; T<t; T++) {
            int k = Integer.parseInt(br.readLine());
            int[] sum = new int[k+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1; i<=k; i++) {
                int cd = Integer.parseInt(st.nextToken());
                sum[i] = sum[i-1] + cd;
            }
            int[][] dp = new int[k+1][k+1];    // dp[i][j] = i부터 j까지 합쳤을 때 비용
            for(int i=1; i<=k; i++) {    // i장을 합친다
                for(int j=1; j+i<=k; j++) {    // j번째 부터 합치기 -> i장을 합칠꺼니까 j+i<=k
                    int to = j + i;
                    dp[j][to] = Integer.MAX_VALUE;
                    for(int d=j; d<to; d++) {    // j부터 j+i까지 합치는데 구간을 나눴을 때 최솟값이 존재하는 지 체크
                        dp[j][to] = Math.min(dp[j][to], dp[j][d] + dp[d+1][to] + sum[to] - sum[j-1]);   // 누적합을 더하는 이유는 최종적으로 j ~ j+i까지의 비용합
                    }
                }
            }
            System.out.println(dp[1][k]);
        }
        br.close();
    }
}