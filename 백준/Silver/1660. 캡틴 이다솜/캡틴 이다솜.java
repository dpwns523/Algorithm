import java.util.*;
import java.io.*;

class Main {
    public static int MAX_VALUE = 300000;
    public static void main(String[] args) throws IOException {
        // maxTest()    // 121
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1], tet = new int[122];
        int dif = 3;
        tet[0] = 1;
        for(int i=1; i<=121; i++){
            tet[i] = tet[i-1] + dif;
            dif += i+2;
        }
        Arrays.fill(dp, MAX_VALUE);
        dp[0] = 0;  // 대포알 개수 딱 맞아떨어지는 경우
        dp[1] = 1;  // 1개일 때
        for(int i=2; i<=n; i++) {
            for(int j=0; j<=121; j++) {
                if(i < tet[j]) break;
                dp[i] = Math.min(dp[i], dp[i-tet[j]] + 1);
            }
        }
        System.out.println(dp[n]);
        br.close();

    }
    public static void maxTest() {
        int sum = 0, dif = 1;
        for(int i=0; i<MAX_VALUE; i++){
            sum += dif;
            dif += i+1;
            if(sum >= MAX_VALUE){
                System.out.println(i);
                break;
            }
        }
    }
}