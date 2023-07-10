import java.util.*;
import java.io.*;
public class Main {
    /*
        앞 숫자와 뒤 숫자를 합쳤을 때 26이하면 합쳐서 문자를 만들 수 있음
        두 문자를 합쳐서 문자를 만들 수 있다면 D[i-2]를 더해야 함 (두 문자를 합쳤을 때 앞에 나올 수 있는 가지 수)
        그런데 합친 두 문자를 제외하고 한 문자도 남지 않는다면 1개만 더하면 됨 -> 앞에 가능한 조합이 없으니까
        0은 문자에 포함되지 않는다
     */
    public static final int DIVIDE = 1000000;
    public static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] n = br.readLine().toCharArray();
        dp = new int[n.length];
        dp[0] = n[0] - '0' != 0 ? 1 : 0;
        for(int i=1; i<n.length; i++) {
            int prev = 10 * (n[i-1] - '0'), curr = n[i] - '0';
            dp[i] = curr != 0 ? dp[i-1] : 0;    // 0이면 문자가 아님
            if(prev + curr >= 10 && prev + curr <= 26) { // 두 숫자를 더하면 10 ~ 26 이어야 함
                dp[i] += i>=2 ? dp[i-2] : 1;
            }
            dp[i] %= DIVIDE;
        }
        System.out.println(dp[n.length-1]);
        br.close();
    }
}