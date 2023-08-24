
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] belt = new int[N];
        int[] count = new int[d + 1];// 현재 체크중인 음식수 카운팅

        int tmp = 0;

        for (int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < k; i++) {
            if (count[belt[i]] == 0) {
                tmp++;
            }
            count[belt[i]]++;
        }

        int result=tmp;

        int max = 0;

        for (int i = 1; i < N; i++) {

            count[belt[i - 1]]--;
            if (count[belt[i - 1]] == 0) {
                tmp--;
            }

            if (count[belt[(i - 1 + k) % N]] == 0)
                tmp++;
            count[belt[(i - 1 + k) % N]]++;


            if (count[c] == 0) {// 쿠폰이 있을경우 한칸더 탐색
                result = tmp + 1;
            } else {
                result = tmp;
            }

            max = Math.max(max, result);
        } // 초기화

        System.out.println(max);
    }
}

