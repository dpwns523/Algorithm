import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];
        long[][][] dp = new long[n][n][3];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dp[0][1][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] == 1) continue;
                dp[i][j][0] += j>0 ? dp[i][j-1][0] + dp[i][j-1][1] : 0;
                if(i>0 && j>0 && map[i-1][j] != 1 && map[i][j-1] != 1) {
                    dp[i][j][1] += dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                }
                dp[i][j][2] += i>0 ? dp[i-1][j][2] + dp[i-1][j][1] : 0;
            }
        }
        System.out.println(Arrays.stream(dp[n-1][n-1]).sum());
        br.close();
    }
}