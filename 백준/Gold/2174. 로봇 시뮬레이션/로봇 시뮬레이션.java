import java.util.*;
import java.io.*;
public class Main {
    /*
        로봇 명령 -> 완수 or 벽, 로봇에 박음
        로봇번호로 명령 -> Map
        다른 로봇과 충돌 -> map = 1
     */
    static class Position {
        int r, c, d;
        public Position(int r, int c, String dir) {
            this.r = r;
            this.c = c;
            this.d = dir.equals("E") ? 0 : dir.equals("W") ? 2 : dir.equals("S") ? 1 : 3;
        }
    }
    public static int[][] map, direction = {{0,1},{-1,0},{0,-1},{1,0}};    // E W S N => 0,1,2,3
    public static int row, col;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        map = new int[row+1][col+1];

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        Map<Integer, Position> pos = new HashMap<>();
        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken());
            pos.put(i, new Position(r, c, st.nextToken()));
            map[r][c] = i;
        }

        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for(int k=0; k<m; k++) {
            if(flag) break;
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            String command = st.nextToken();
            int cnt = Integer.parseInt(st.nextToken());
            Position robot = pos.get(idx);

            if(command.equals("L")) {
                for(int i=0; i<cnt; i++) {
                    robot.d = robot.d == 0 ? direction.length-1 : robot.d-1;
                }
            }
            else if(command.equals("R")) {
                for(int i=0; i<cnt; i++) {
                    robot.d = robot.d == direction.length-1 ? 0 : robot.d+1;
                }
            }
            else {
                for(int i=0; i<cnt; i++) {
                    int nextR = robot.r + direction[robot.d][0];
                    int nextC = robot.c + direction[robot.d][1];
                    if(checkPos(nextR, nextC)) {
                        if(map[nextR][nextC] != 0) {
                            sb.append("Robot "+idx+" crashes into robot "+map[nextR][nextC]);
                            flag = true;
                            break;
                        }
                        map[robot.r][robot.c] = 0;
                        robot.r = nextR;
                        robot.c = nextC;
                        map[robot.r][robot.c] = idx;
                    }
                    else {
                        sb.append("Robot "+idx+" crashes into the wall");
                        flag = true;
                        break;
                    }
                }
            }
        }
        if(!flag) sb.append("OK");
        System.out.print(sb);
        br.close();
    }
    public static boolean checkPos(int r, int c) {
        return (r<1 || r>row || c<1 || c>col) ? false : true;
    }
}