import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[][] dp = new int[30][30];
        for(int i=1; i<30; i++){
            dp[1][i] = i;
            dp[i][i] = 1;
        }
        for(int i=2; i<30; i++) {
            for (int j = i+1; j < 30; j++) {
                dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
            }
        }
        for(int i=0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
            System.out.println(dp[n][m]);
        }
    }
}