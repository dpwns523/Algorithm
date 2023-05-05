import java.util.*;
import java.io.*;

class Main{
    static class Fish{
        int dist, r, c;
        public Fish(int dist, int r, int c){
            this.dist=dist; this.r=r; this.c=c;
        }
    }
    static class Position{
        int r, c;
        public Position(int r, int c){
            this.r=r; this.c=c;
        }
    }
    public static int[][] map, visited, direction = {{1,0},{-1,0},{0,1},{0,-1}};
    public static int n, size = 2;
    public static Position shark;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    shark = new Position(i, j);
                    map[i][j] = 0;
                }
            }
        }
        br.close();
        int res = 0;
        int eat = 0;
        // bfs를 통해 한 마리씩 잡아먹는다
        while(!checkFinish()){  // 다 먹음
            Fish next = bfs(shark);
            if(next == null) break;
            map[next.r][next.c] = 0;
            shark = new Position(next.r, next.c);
            res += next.dist;
            eat++;
            if(eat == size){
                size++;
                eat = 0;
            }
        }
        System.out.println(res+"");
    }
    public static Fish bfs(Position shark) {
        Queue<Fish> next = new PriorityQueue<>(
                (f1, f2) -> f1.dist == f2.dist ? (f1.r == f2.r ? f1.c - f2.c : f1.r - f2.r) : f1.dist - f2.dist);
        Queue<Position> queue = new LinkedList<>();
        int[][] dist = new int[n][n];
        visited = new int[n][n];
        visited[shark.r][shark.c] = 1;
        queue.offer(shark);
        while(!queue.isEmpty()){
            Position p = queue.poll();
            for(int i=0; i<direction.length; i++){
                int nextR = p.r + direction[i][0];
                int nextC = p.c + direction[i][1];
                if(checkPos(nextR, nextC) && visited[nextR][nextC] == 0 && map[nextR][nextC] <= size){
                    // 이동 가능
                    dist[nextR][nextC] = dist[p.r][p.c] + 1;
                    queue.offer(new Position(nextR, nextC));
                    visited[nextR][nextC] = 1;
                    if(map[nextR][nextC]>0 && map[nextR][nextC] < size){    // 잡아먹을 수 있는 물고기
                        next.offer(new Fish(dist[nextR][nextC], nextR, nextC));
                    }
                }
            }
        }
        return next.poll();
    }
    public static boolean checkPos(int r, int c){
        return (r<0 || r>=n || c<0 || c>=n) ? false : true;
    }
    public static boolean checkFinish() {
        return Arrays.stream(map).flatMapToInt(Arrays::stream).filter(m -> m > 0).count() > 0 ? false : true;
    }
}