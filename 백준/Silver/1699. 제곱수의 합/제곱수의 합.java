import java.util.*;
import java.io.*;

class Main {
    /*
        제곱수로 표현이 되면 항은 1개
        최대로 표현가능한 제곱수를 만들고 그 최대 제곱수를 뺀 항은 재사용할 수 있다
        dp[n] = Math.min(dp[n], dp[n - i^2] + 1
        만약 제곱 수 한 번에 표현될 수 있으므로 i는 1부터 i^2이 n보다 작거나 같을 떄 까지 반복하여 최소항을 찾는다.
     */
    public static int MAX_VALUE = 100001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        Arrays.fill(dp, MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2; i<=n; i++) {
            for(int j=1; j*j<=i; j++) {
                dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
            }
        }
        System.out.println(dp[n]);
        br.close();
    }
}