import java.util.*;
import java.io.*;
public class Main {
    /*
        6, 41 -> 6일 째의 41개를 주었다.
        D[5] = 3 * D[2] + 2 * D[1]
        D[6] = D[5] + D[4]
             = 2 * D[4] + D[3]
             = 2 * (D[3] + D[2]) + D[3]
             = 3 * D[3] + 2 * D[2]
             = 3 * (D[2] + D[1]) + 2 * D[2]
             = 5 * D[2] + 3 * D[1]
         D[7] = D[6] + D[5]
              = 2 * D[5] + D[4]
              = 3 * D[4] + 2 * D[3]
              = 5 * D[3] + 3 * D[2]
              = 8 * D[2] + 5 * D[1]
        D[8] = 13 * D[2] + 8 * D[1]
        2 3 5 8 13 21 34 55
        1 2 3 5 8 13 21
        피보나치 수열을 이루지만 
     */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        int[] dp = new int[d+1];
        int init = 1;
        while(dp[d] != k) {
            dp[1] = init;
            for(int i=init+1; i<k; i++) {
                dp[2] = i;
                for(int j=3; j<=d; j++) {
                    dp[j] = dp[j-1] + dp[j-2];
                }
                if(dp[d] >= k) break;
            }
            init++;
        }
        System.out.println(dp[1] +"\n"+dp[2]);
        br.close();
    }
}