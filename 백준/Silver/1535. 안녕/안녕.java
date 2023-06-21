import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n][101];
        int[] L = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] J = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int[] d : dp){
            Arrays.fill(d, -1);
        }

        dp[0][100] = 0;
        if(100 - L[0]> 0) dp[0][100-L[0]] = J[0];
        for(int i=1; i<n; i++) {
            for(int j=0; j<=100; j++) {
                if(dp[i-1][j] >= 0) {    // 이전까지 체력
                    if(j-L[i] > 0) dp[i][j-L[i]] = Math.max(dp[i][j-L[i]], dp[i-1][j] + J[i]);
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j]);    // 인사 안하거나 체력이 안깎인 인사
                }
            }
        }
        System.out.println(Arrays.stream(dp[n-1]).max().getAsInt());
        br.close();
    }
}