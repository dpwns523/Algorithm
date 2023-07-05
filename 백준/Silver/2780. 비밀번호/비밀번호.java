import java.util.*;
import java.io.*;
public class Main {
    /*
        선택한 번호의 인접한 번호들을 따라간다.
        길이가 2인 비밀번호를 찾으면
        1번을 선택햇을 때 2,4로 찾아간다
        길이가 3인 비밀번호를 찾으면
        1번을 선택했을 때 2,4로 찾아가 2는 1,3,4 / 4는 1, 5, 7로 이동한다
        -> 이는 길이가 2인 비밀번호를 찾았을 때 2로 시작, 4로 시작했을 때를 더하면 된다
        따라서 dp[3][1] = dp[2][2] + dp[2][4]로 표현할 수 있다.
     */
    public static final int DIVIDE = 1234567;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            int n = Integer.parseInt(br.readLine());
            int[][] dp = new int[n+1][10];
            Arrays.fill(dp[1], 1);
            for(int i=2; i<=n; i++) {
                dp[i][0] = (dp[i-1][7]) % DIVIDE;
                dp[i][1] = (dp[i-1][2] + dp[i-1][4]) % DIVIDE;
                dp[i][2] = (dp[i-1][1] + dp[i-1][3] + dp[i-1][5]) % DIVIDE;
                dp[i][3] = (dp[i-1][2] + dp[i-1][6]) % DIVIDE;
                dp[i][4] = (dp[i-1][1] + dp[i-1][5] + dp[i-1][7]) % DIVIDE;
                dp[i][5] = (dp[i-1][2] + dp[i-1][4] + dp[i-1][6] + dp[i-1][8]) % DIVIDE;
                dp[i][6] = (dp[i-1][3] + dp[i-1][5] + dp[i-1][9]) % DIVIDE;
                dp[i][7] = (dp[i-1][0] + dp[i-1][4] + dp[i-1][8]) % DIVIDE;
                dp[i][8] = (dp[i-1][5] + dp[i-1][7] + dp[i-1][9]) % DIVIDE;
                dp[i][9] = (dp[i-1][6] + dp[i-1][8]) % DIVIDE;
            }
            System.out.println(Arrays.stream(dp[n]).sum() % DIVIDE);
        }
        br.close();
    }
}