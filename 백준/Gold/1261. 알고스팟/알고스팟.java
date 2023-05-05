import java.util.*;
import java.io.*;

class Main{
    static class Position{
        int r, c, cnt;
        public Position(int r, int c, int cnt) {
            this.r=r; this.c=c; this.cnt=cnt;
        }
    }
    public static int[][] map, visited, direction = {{1,0},{-1,0},{0,1},{0,-1}};
    public static int row, col;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        map = new int[row][col];
        visited = new int[row][col];
        for(int i=0; i<row; i++){
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(bfs()+"");
    }
    public static int bfs(){
        Queue<Position> queue = new PriorityQueue<>((p1, p2) -> p1.cnt - p2.cnt);
        queue.offer(new Position(0, 0, 0));
        visited[0][0] = 1;
        while(!queue.isEmpty()) {
            Position p = queue.poll();
            if (p.r == row - 1 && p.c == col - 1) {
                return p.cnt;
            }
            for (int i = 0; i < direction.length; i++) {
                int nextR = p.r + direction[i][0];
                int nextC = p.c + direction[i][1];
                if (checkPos(nextR, nextC) && visited[nextR][nextC] == 0) {
                    visited[nextR][nextC] = 1;
                    queue.offer(new Position(nextR, nextC, map[nextR][nextC] == 1 ? p.cnt + 1 : p.cnt));
                }
            }
        }
        return -1;
    }
    public static boolean checkPos(int r, int c){
        return (r<0 || r>=row || c<0 || c>=col) ? false : true;
    }
}
