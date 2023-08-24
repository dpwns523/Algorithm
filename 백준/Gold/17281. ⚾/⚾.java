import java.io.*;
import java.util.*;

public class Main {
    public static final int fixNum = 4;
    public static int[] seq = {0,1,2,3,4,5,6,7,8,9}, visited = new int[10];
    public static int[][] game;
    public static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        game = new int[n][10];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j < 10; j++) {
                game[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        seq[fixNum] = 1;
        visited[1] = 1;
        dfs(1);
        System.out.println(max);
        br.close();
    }
    public static void dfs(int cnt) {
        if(cnt == 10) {
            max = Math.max(max, playGame());
            return;
        }
        if(cnt == 4) {
            dfs(cnt+1);    // 4번타자 고정
            return;
        }
        for (int i = 2; i < 10; i++) {
            if(visited[i] == 1) continue;
            visited[i] = 1;
            seq[cnt] = i;
            dfs(cnt+1);
            visited[i] = 0;
        }
    }

    public static int playGame() {
        // 타석 seq로 게임 시작
        int score = 0, idx = 1;
        int[] runner = new int[3];
        for (int i = 0; i < game.length; i++) {
            Arrays.fill(runner, 0);
            int out = 0;
            while (out < 3) {
                int batter = game[i][seq[idx]];
                if (batter == 0) out++;
                else if(batter > 0) {
                    if (batter == 4) {
                        score++;
                        for (int j = 0; j < 3; j++) {
                            score += runner[j];
                            runner[j] = 0;
                        }
                    } else {
                        for (int k = 2; k >= 0; k--) {  // 주자 제크
                            if (runner[k] != 0) {
                                if (k + batter >= 3) score++;
                                else runner[k + batter] = 1;
                            }
                            runner[k] = 0;
                        }
                        runner[batter - 1] = 1;
                    }
                }
                idx = idx == 9 ? 1 : idx + 1;
            }
        }
        return score;
    }

}