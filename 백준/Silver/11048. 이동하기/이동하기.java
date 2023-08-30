import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int row, col;
    public static int[][] map, direction = {{1,0}, {0,1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new int[row][col];
        for (int i = 0; i < row; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(bfs());

        br.close();
    }

    public static int bfs() {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        int[][] res = new int[row][col];
        int[][] visited = new int[row][col];
        res[0][0] = map[0][0];
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            for (int i = 0; i < direction.length; i++) {
                int nextR = pos[0] + direction[i][0];
                int nextC = pos[1] + direction[i][1];
                if(checkPos(nextR, nextC)) {
                    int next = res[pos[0]][pos[1]] + map[nextR][nextC];
                    if(next > res[nextR][nextC] || (next == res[nextR][nextC] && visited[nextR][nextC] == 0)) {
                        // 최댓값 갱신 or 0인 곳 방문
                        visited[nextR][nextC] = 1;  // 재방문 x
                        res[nextR][nextC] = next;
                        queue.offer(new int[]{nextR, nextC});
                    }
                }
            }
        }
        return res[row-1][col-1];

    }

    public static boolean checkPos(int r, int c) {
        return (r<0 || r>=row || c<0 || c>=col) ? false : true;
    }

}
