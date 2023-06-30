import java.util.*;
import java.io.*;
public class Main {
    /*
        123 4 56 7 89 일 때 vip가 4, 7
        4이전의 가능한 경우의 수 * 5부터 7이전의 가능한 경우의 수 * 8부터 n+1이전(n까지)의 가능한 경우의 수로 답을 구할 수 있다.
     */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[] dp = new int[41];
        dp[0] = 1;    // vip가 1일 경우
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        int res = 1, lastVip = 1;
        for(int i=0; i<m; i++) {
            int vip = Integer.parseInt(br.readLine());
            res *= dp[vip - lastVip];    // vip가 3이면 dp[2](2개로 가능한 경우가지), 다음 vip가 6이면 dp[2](4부터 6이전까지) 값 6 - 3 - 1
            lastVip = vip + 1;    // vip 다음 부터 count
        }
        res *= dp[n + 1 - lastVip]; 
        System.out.println(res);
        br.close();

    }
}