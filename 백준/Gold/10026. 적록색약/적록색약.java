import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] graph;
    static boolean[][] visited;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new char[N][N];
        int normalcnt = 0, sickcnt = 0;

        for (int i=0; i<N; i++){
            graph[i] = br.readLine().toCharArray();
        }

        visited = new boolean[N][N];
        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                if (!visited[i][j]){
                    visited[i][j] = true;
                    dfs(i, j, graph[i][j]);
                    normalcnt++;
                }
                if(graph[i][j] =='G') graph[i][j] = 'R';
            }
        }

        visited = new boolean[N][N];
        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                if (!visited[i][j]){
                    visited[i][j] = true;
                    dfs(i, j, graph[i][j]);
                    sickcnt++;
                }

            }
        }

        System.out.println(normalcnt+" "+sickcnt);
    }

    private static void dfs(int row, int col, char color){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{row, col});

        while(!q.isEmpty()){
            int[] polls = q.poll();
            int r = polls[0];
            int c = polls[1];

            for (int i=0; i<4; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 범위 안에 있고, 방문하지 않았고
                // 색이 같으면
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]){
                    if (graph[nr][nc] == color){
                        visited[nr][nc] = true;
                        q.offer(new int[] {nr, nc});
                    }
                }
            }
        }

    }


}