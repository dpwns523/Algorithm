import java.util.*;
import java.io.*;

class Main{
    static class Position{
        int r, c, amount;
        public Position(int r, int c, int amount){
            this.r=r; this.c=c; this.amount = amount;
        }
    }
    public static int[][] map, direction={{1,0},{-1,0},{0,1},{0,-1}};
    public static Queue<Position> queue = new LinkedList<>();
    public static int R, C, T, top=51, bottom=-1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for(int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1){
                    top = top > i ? i : top;
                    bottom = bottom < i ? i : bottom;
                }
                else if(map[i][j] >= 5) queue.offer(new Position(i, j, map[i][j]));
            }
        }
        for(int k=0; k<T; k++){
            bfs();
            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++){
                    if(map[i][j] >= 5) queue.offer(new Position(i,j, map[i][j]));
                }
            }
        }
        System.out.println(Arrays.stream(map).flatMapToInt(Arrays::stream).filter(m -> m > 0).sum()+"");
        br.close();
    }
    public static void bfs(){
        while(!queue.isEmpty()){
            Position p = queue.poll();
            int amount = p.amount/5;
            for(int i=0; i<direction.length; i++){
                int nextR = p.r + direction[i][0];
                int nextC = p.c + direction[i][1];
                if(checkPos(nextR, nextC)){
                    // 현재 미세먼지의 1/5를 next로 추가
                    map[nextR][nextC] += amount;
                    map[p.r][p.c] -= amount;
                }
            }
        }
        playMachine();
    }
    public static void playMachine(){
        // 시계 반대방향으로 퍼지는 것은 시계 방향으로 값 변경 (덮어쓰기)
        for(int i=top-1; i>0; i--){    // 하
            map[i][0] = map[i-1][0];
        }
        for(int i=0; i<C-1; i++){   // 좌
            map[0][i] = map[0][i+1];
        }
        for(int i=0; i<top; i++){   // 상
            map[i][C-1] = map[i+1][C-1];
        }
        for(int i=C-1; i>0; i--){    // 우 -> i+1의 값이 i가 됨
            map[top][i] = map[top][i-1];
        }
        map[top][1] = 0;

        // 시계 방향
        for(int i=bottom+1; i<R-1; i++){  // 상
            map[i][0] = map[i+1][0];
        }
        for(int i=0; i<C-1; i++){  // 좌
            map[R-1][i] = map[R-1][i+1];
        }
        for(int i=R-1; i>bottom; i--){  // 하
            map[i][C-1] = map[i-1][C-1];
        }
        for(int i=C-1; i>0; i--){  // 우
            map[bottom][i] = map[bottom][i-1];
        }
        map[bottom][1] = 0;
    }
    public static boolean checkPos(int r, int c){
        return (r<0 || r>=R || c<0 || c>=C || (r==top && c==0) || (r==bottom && c==0)) ? false : true;
    }
}
