import java.util.*;
import java.io.*;

class Main {
    /*
        1번 주사위의 윗면은 6개 모두 가능하다
        6번째 주사위올렸을 때 최댓값을 갱신하자 -> 재귀
        주사위는 마주보는 면을 기억하고 인덱스로 활용하여 접근하면 쉽게 풀 수 있다
     */
    public static int[][] dice;
    public static int[] op = {5, 3, 4, 1, 2, 0};
    public static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dice = new int[n][6];
        for(int i=0; i<n; i++) {
            dice[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for(int i=0; i<6; i++) {    // 첫번째 윗면 선택
            int currMax = 0;
            for(int j=0; j<6; j++) {
                if(i == j || op[i] == j) continue;
                currMax = Math.max(currMax, dice[0][j]);
            }
            findMax(n, 1, dice[0][i], currMax);
        }
        System.out.println(max);
        br.close();
    }
    public static void findMax(int n, int cnt, int top, int sum) {
        if(cnt == n) {
            max = Math.max(max, sum);
            return;
        }
        int next = -1, currMax = 0;
        for(int i=0; i<6; i++) {    // 마지막 top에 맞는 밑을 찾는다
            if(dice[cnt][i] == top){
                next = op[i];     // 밑의 마주보는 면이 다음 top
            }
        }
        for(int i=0; i<6; i++) {
            if(i == next || i == op[next]) continue;
            currMax = Math.max(currMax, dice[cnt][i]);
        }
        findMax(n, cnt+1, dice[cnt][next], sum+currMax);
    }
}