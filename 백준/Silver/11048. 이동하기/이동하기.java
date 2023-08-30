import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int row = Integer.parseInt(st.nextToken()), col = Integer.parseInt(st.nextToken());
        int[][] map = new int[row][col], dp = new int[row+1][col+1];

        for (int i = 0; i < row; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                dp[i][j] = map[i-1][j-1] + Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        System.out.println(dp[row][col]);
        br.close();
    }

}
