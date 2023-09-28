import java.util.*;
import java.io.*;

public class Main {

    public static char[][] map;
    public static int[][] direction = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public static Queue<int[]> water = new ArrayDeque<>();
    public static Queue<int[]> location = new ArrayDeque<>();
    public static int row, col;
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new char[row][col];
        for (int i = 0; i < row; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < col; j++) {
                map[i][j] = input[j];
                if(map[i][j] == '*') water.offer(new int[]{i, j});
                else if(map[i][j] == 'S') {
                    location.offer(new int[]{i, j, 0});
                    map[i][j] = 'X';
                }
            }
        }
        int res = bfs();
        System.out.println(res == Integer.MAX_VALUE ? "KAKTUS" : res);
        br.close();

    }

    public static int bfs() {
        while (!location.isEmpty()) {
            int size = water.size();
            for (int i = 0; i < size; i++) {
                int[] wa = water.poll();
                for (int j = 0; j < direction.length; j++) {
                    int nextR = wa[0] + direction[j][0];
                    int nextC = wa[1] + direction[j][1];
                    if(isRange(nextR, nextC) && (map[nextR][nextC] == '.' || map[nextR][nextC] == 'P')) {
                        water.offer(new int[]{nextR, nextC});
                        map[nextR][nextC] = '*';
                    }
                }
            }
            size = location.size();
            for (int i = 0; i < size; i++) {
                int[] loc = location.poll();
                for (int j = 0; j < direction.length; j++) {
                    int nextR = loc[0] + direction[j][0];
                    int nextC = loc[1] + direction[j][1];
                    if(isRange(nextR, nextC)) {
                        if(map[nextR][nextC] == 'D') return loc[2] + 1;
                        else if(map[nextR][nextC] == '.') {
                            location.offer(new int[]{nextR, nextC, loc[2]+1});
                            map[nextR][nextC] = 'P';
                        }
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    public static void move(Queue<int[]> queue, char replace) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int[] next = queue.poll();
            for (int j = 0; j < direction.length; j++) {
                int nextR = next[0] + direction[j][0];
                int nextC = next[1] + direction[j][1];
                if(isRange(nextR, nextC) && map[nextR][nextC] == '.') {
                    queue.offer(new int[]{nextR, nextC});
                    map[nextR][nextC] = replace;
                }
            }
        }
    }
    public static boolean isRange(int r, int c) {
        return r>=0 && r<row && c>=0 && c<col;
    }


}