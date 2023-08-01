import java.io.*;
import java.util.*;

class Main {
    static class Position {
        int r, c, dir;

        public Position(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    public final static int n = 19;
    public static Queue<Position> user1 = new LinkedList<>(), user2 = new LinkedList<>();
    public static boolean winner = false;
    public static int[][] map, visited, direction = {{0, -1, 0, 1},{-1, 0, 1, 0},{-1, 1, 1, -1},{-1, -1, 1, 1}};
    public static Position res = new Position(-1,-1,-1);
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[n][n];
        visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) user1.offer(new Position(i, j, -1));
                if (map[i][j] == 2) user2.offer(new Position(i, j, -1));
            }
        }
        while (!winner && !user1.isEmpty()) {
            Position p = user1.poll();
            if (visited[p.r][p.c] == 0) {
                visited[p.r][p.c] = 1;
                for (int i = 0; i < direction.length; i++) {
                    res = p;    
                    dfs(new Position(p.r, p.c, i), 1);
                    if(winner) {
                        if(res.c > p.c) res = p;
                        else if(res.c == p.c) res = res.r > p.r ? p : res;
                        break;
                    }
                }
                visited[p.r][p.c] = 0;
            }
        }
        if (winner){
            System.out.println("1");
            System.out.println((res.r+1)+" "+(res.c+1));
        }
        else {
            while (!winner && !user2.isEmpty()) {
                Position p = user2.poll();
                if (visited[p.r][p.c] == 0) {
                    visited[p.r][p.c] = 1;
                    res = p;
                    for (int i = 0; i < direction.length; i++) {
                        dfs(new Position(p.r, p.c, i), 1);
                        if(winner) {
                            if(res.c > p.c) res = p;
                            else if(res.c == p.c) res = res.r > p.r ? p : res;
                            break;
                        }
                    }
                    visited[p.r][p.c] = 0;
                }
            }
            if(winner) {
                System.out.println("2");
                System.out.println((res.r+1)+" "+(res.c+1));
            }
        }
        if (!winner) System.out.println("0");
        br.close();
    }
    public static void dfs(Position p,int cnt){
        if(cnt == 5) {
            for (int i = 0; i < direction[0].length; i += 2) {
                int nextR = p.r + direction[p.dir][i];
                int nextC = p.c + direction[p.dir][i + 1];
                int startR = res.r + direction[p.dir][i];
                int startC = res.c + direction[p.dir][i+1];
                if(checkPos(startR, startC) && visited[startR][startC] == 0 && map[p.r][p.c] == map[startR][startC]) return;
                if(checkPos(nextR,nextC) && visited[nextR][nextC] == 0 && map[p.r][p.c]==map[nextR][nextC]) return;

            }
            res = new Position(p.r, p.c, p.dir);
            winner = true;
            return;
        }

        for (int i = 0; i < direction[0].length; i += 2) {
            int nextR = p.r + direction[p.dir][i];
            int nextC = p.c + direction[p.dir][i + 1];
            if (checkPos(nextR, nextC) && map[nextR][nextC] == map[p.r][p.c] && visited[nextR][nextC] == 0) {
                visited[nextR][nextC] = 1;
                dfs(new Position(nextR, nextC, p.dir), cnt + 1);
                visited[nextR][nextC] = 0;
            }
        }
    }
    public static boolean checkPos(int r, int c) {
        return (r<0 || r>=n || c<0 || c>=n) ? false : true;
    }
}