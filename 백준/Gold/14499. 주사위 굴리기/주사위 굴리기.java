import java.util.*;
import java.io.*;

class Main{
    public static int[] dice = {0,0,0,0,0,0};    // 동, 서, 남, 북, 위, 바닥
    public static int[][] direction = {{0,1}, {0,-1}, {-1,0}, {1,0}};
    public static int row, col;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        int[][] map = new int[row][col];
        for(int i=0; i<row; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<col; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] commands = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        StringBuilder sb = new StringBuilder();
        for(int command: commands){
            int nextR = r + direction[command-1][0];
            int nextC = c + direction[command-1][1];
            if(!checkLoc(nextR, nextC)) continue;
            switch(command){
                case 1:
                    switchEast();
                    break;
                case 2:
                    switchWest();
                    break;
                case 3:
                    switchNorth();
                    break;
                case 4:
                    switchSouth();
                    break;
            }
            if(map[nextR][nextC] == 0) map[nextR][nextC] = dice[5];
            else {
                dice[5] = map[nextR][nextC];
                map[nextR][nextC] = 0;
            }
            r = nextR;
            c = nextC;
            sb.append(dice[4]+"\n");
        }
        System.out.print(sb);
        br.close();
    }

    public static boolean checkLoc(int r, int c){
        return (r<0 || r>=row || c<0 || c>=col) ? false : true;
    }

    public static void switchNorth(){
        // 북쪽이동
        int tmp = dice[3];
        dice[3] = dice[4];
        dice[4] = dice[2];
        dice[2] = dice[5];
        dice[5] = tmp;
    }

    public static void switchSouth() {
        // 남쪽이동
        int tmp = dice[3];
        dice[3] = dice[5];
        dice[5] = dice[2];
        dice[2] = dice[4];
        dice[4] = tmp;
    }

    public static void switchWest() {
        // 서쪽이동
        int tmp = dice[5];
        dice[5] = dice[1];
        dice[1] = dice[4];
        dice[4] = dice[0];
        dice[0] = tmp;
    }

    public static void switchEast() {
        // 동쪽이동
        int tmp = dice[5];
        dice[5] = dice[0];
        dice[0] = dice[4];
        dice[4] = dice[1];
        dice[1] = tmp;
    }
}