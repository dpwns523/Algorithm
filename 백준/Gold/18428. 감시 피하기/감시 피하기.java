import java.io.*;
import java.util.*;

class Main {
    public static char[][] map;
    public static int[][] visited, direction = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public static List<int[]> teachers = new ArrayList<>();
    public static int n, student;
    public static boolean isFinish = false;
    public static Set<int[]> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        map = new char[n][n];
        visited = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if(map[i][j] == 'T') teachers.add(new int[]{i, j});
                else if(map[i][j] == 'S') student++;
            }
        }
        dfs(0,0,0);
        if(isFinish) System.out.println("YES");
        else System.out.println("NO");
        br.close();
    }

    public static void dfs(int r, int c, int cnt) {
        if(cnt == 3) {
            check();
            return;
        }
        for (int i = r; i < n; i++) {
            for (int j = i == r ? c : 0; j < n; j++) {
                if(isFinish) return;
                if(map[i][j] == 'X') {
                    map[i][j] = 'O';
                    dfs(i, j, cnt+1);
                    map[i][j] = 'X';
                }
            }
        }
    }

    public static void check() {
        int cnt = 0;
        set.clear();
        for (int[] teacher : teachers) {
            for (int i = 0; i < direction.length; i++) {
                int nextR = teacher[0], nextC = teacher[1];
                while (checkPos(nextR, nextC) && map[nextR][nextC] != 'O') {
                    if(map[nextR][nextC] == 'S' && !set.contains(new int[]{nextR, nextC})) {
                        set.add(new int[]{nextR, nextC});
                        cnt++;
                    }
                    nextR += direction[i][0];
                    nextC += direction[i][1];
                }
            }
        }
        if(cnt == 0) isFinish = true;
    }

    public static boolean checkPos(int r, int c) {
        return (r<0 || r>=n || c<0 || c>=n) ? false : true;
    }
}