import java.io.*;

/*
    2 x n인 사각형은
    2 x n-1인 사각형에서 높이가 2 너비가 n-1인 경우에 2 x 1은 세워서 추가 + 
    2 x n-2인 사각형에서 1 x 2 눕혀서 2개로 채우는 방식 + 2 x 2 넣기.
    d[n] = d[n-1] + 2*d[n-2]
    예외
    d[1] = 1    # 세워서 그냥 한개
    d[2] = 3    # 2 x 1 짜리 두개 + 1x2짜리 두개 + 2x2짜리 한개
 */
public class Main {
    public static final int DIVIDE = 10007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        if(n < 2) bw.write("1");
        else {
            dp[1] = 1;
            dp[2] = 3;
            for (int i = 3; i <= n; i++) {
                dp[i] = (dp[i-1] + dp[i-2]*2) % DIVIDE;
            }
            bw.write(dp[n]+"");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}