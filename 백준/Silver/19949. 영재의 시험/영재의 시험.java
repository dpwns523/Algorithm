import java.util.*;
import java.io.*;

class Main {
    public static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] answer = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        /*
        5지 선다형으로 찍을 수 있다
        -> 1번 문항에 대해서 5가지 선택을 해볼 수 있다.
        3개 연속 문제 답이 똑같지 않다
        -> 찍기를 한거니까 정답이었든 무관하게 같은 답으론 세 번 찍지 않는다.
        */
        for(int i=1; i<=5; i++){
            dfs(1, answer[0] == i ? 1 : 0, i, 1, answer);
        }
        System.out.println(cnt);
        br.close();
    }

    public static void dfs(int n, int correct, int prev, int sequence, int[] answer) {
        // 같은 답 3번 or 맞춰야하는 문제가 남은문제보다 많다면 종료
        if(sequence == 3 || 5 - correct > 10 - n) return;
        if(n == 10 && correct >= 5){
            cnt++;
            return;
        }

        for(int i=1; i<=5; i++) {
            dfs(n+1, answer[n] == i ? correct + 1 : correct, i, prev == i ? sequence + 1 : 1, answer);
        }

    }
}