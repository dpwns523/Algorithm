import java.util.*;
import java.io.*;

public class Main {
    public static int[][] direction = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public static char[][] map;
    public static int row, col, max = Integer.MIN_VALUE;
    public static Set<Character> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new char[row][col];
        for (int i = 0; i < row; i++) {
            map[i] = br.readLine().toCharArray();
        }
        set.add(map[0][0]);
        dfs(0, 0, 1);
        System.out.println(max);
        br.close();
    }
    public static void dfs(int r, int c, int cnt) {
        for (int i = 0; i < direction.length; i++) {
            int nextR = r + direction[i][0];
            int nextC = c + direction[i][1];
            if(checkPos(nextR, nextC) && !set.contains(map[nextR][nextC])) {
                set.add(map[nextR][nextC]);
                dfs(nextR, nextC, cnt+1);
                set.remove(map[nextR][nextC]);
            }
        }
        max = Math.max(max, cnt);
    }

    public static boolean checkPos(int r, int c) {
        return (r<0 || r>=row || c<0 || c>=col) ? false : true;
    }
}
