import java.util.*;
import java.io.*;

public class Main {
    public static final int MAX_VALUE = 100 * 1000 + 1;
    public static int[][] map = new int[101][101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());

            for (int i = 1; i <= n; i++) {  // 초기화
                for (int j = 1; j <= n; j++) {
                    map[i][j] = i==j ? 0 : MAX_VALUE;
                }
            }

            for (int i = 0; i < m; i++) {   // 간선 입력
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
                map[a][b] = c;
                map[b][a] = c;
            }

            for (int k = 1; k <= n; k++) {  // 최단경로 업데이트
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                    }
                }
            }

            int k = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] friends = new int[k];
            for (int i = 0; i < k; i++) {   // 모임에 오는 친구입력
                friends[i] = Integer.parseInt(st.nextToken());
            }
            int min = Integer.MAX_VALUE, minIdx = 0;
            for (int i = 1; i <= n; i++) {  // 최소 비용으로 모두 모이는 경우 탐색
                int cost = 0;
                for (int j = 0; j < k; j++) {
                    cost += map[friends[j]][i];
                }
                if(min > cost) {
                    min = cost;
                    minIdx = i;
                }
            }
            System.out.println(minIdx);
        }
        br.close();
    }

}
