import java.util.*;
import java.io.*;

class Main {
    /*
        최대로 마신다
        1. 연속 3잔을 모두 마실 순 없다
        2. 3잔 연속 마시는 상황에서 이 잔을 마실지, 마시지 않을 지 방법을 선택한다
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] wine = new int[n+1], dp = new int[n+1];
        for(int i=1; i<=n; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }
        dp[1] = wine[1];
        if(n>1) dp[2] = dp[1] + wine[2];
        for(int i=3; i<=n; i++) {
            dp[i] = dp[i-1];    // 이번잔을 안마심
            dp[i] = Math.max(dp[i],
                    Math.max(dp[i-2] + wine[i],    // 1번 연속 마심
                            dp[i-3] + wine[i-1] + wine[i]));    // 2번 연속 마심
        }
        System.out.println(dp[n]);
        br.close();
    }
}