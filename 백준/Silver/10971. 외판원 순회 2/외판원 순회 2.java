import java.io.*;
import java.util.*;

public class Main {
    public static int[][] graph, dp;
    public static final int INF = 100 * 1000000 + 1;
    public static int statusFullBit, n, start = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        statusFullBit = (1 << n) - 1;   // 노드 4개 표현 1111 = 15
        graph = new int[n][n];
        dp = new int[n][statusFullBit];
        for (int i = 0; i < n; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(dp[i], INF);
        }
        System.out.println(tsp(start, 1));
        br.close();
    }

    public static int tsp(int node, int check) {
        if(check == statusFullBit) {    // 모든 노드 탐색 완료
            if(graph[node][start] == 0) return INF;     // 연결된 노드가 아님 -> 순회 불가
            else return graph[node][start]; // 시작 노드로 돌아가는 비용
        }

        if(dp[node][check] != INF) return dp[node][check];   // 이미 방문한 곳

        // 다음 방문 할 곳
        for (int i = 0; i < n; i++) {
            int next = check | (1 << i);    // check에는 이전에 방문한 노드 정보가 있음, or 연산으로 선택되지 않는 다음 노드를 선택한다
            if(graph[node][i] == 0 || (check & (1 << i)) != 0) continue;    // 현재 노드에서 연결된 노드가 아니거나, 이미 방문한 곳이면 pass
            dp[node][check] = Math.min(dp[node][check], tsp(i, next) + graph[node][i]); // next에는 i번 노드 선택까지 방문 정보가 저장됨
        }
        
        return dp[node][check]; // 최종 결과 0번 출발 시 총 값은 0번 인덱스에 저장될 것
    }

}
