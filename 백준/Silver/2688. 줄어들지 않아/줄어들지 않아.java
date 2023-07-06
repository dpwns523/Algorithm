import java.util.*;
import java.io.*;
public class Main {
    /*
        자신보다 작거나 같은 숫자만 앞에 올 수 있다
        1자리 수의 줄어들지 않는 수는 모두 1가지 자기 자신

        2자리 수의 줄어들지 않는 수는
        두 번째 올 수가 0이면 앞에 0밖에 못온다, 1이면 0과 1이 올 수 있다 2이면 0과1과2가 가능하다
        따라서 한 자리부터 n자리까지 각 자리에 0~9까지 올 수 있는 수를 표현하면
        이전 자리에 올 수 있는 수들의 경우의 수를 모두 합하면 된다
        dp[i(i번째 자리)][j(올 수 j)] += dp[i-1][0~j]
     */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        long[][] dp = new long[65][10];
        Arrays.fill(dp[1], 1);

        for(int i=2; i<=64; i++) {
            for(int j=0; j<10; j++) {
                for(int k=0; k<=j; k++) {
                    dp[i][j] += dp[i-1][k];
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<T; t++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(Arrays.stream(dp[n]).sum()).append("\n");
        }
        System.out.print(sb);
        br.close();
    }
}