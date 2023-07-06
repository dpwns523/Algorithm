import java.util.*;
class Solution {
    public static final int row = 11, col = 11;
    public static int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};
    public int solution(String dirs) {
        int[][] map = new int[row][col];
        char[] commands = dirs.toCharArray();
        Set<String> set = new HashSet<>();
        int r=5, c=5;
        for(char command : commands) {
            int nextR, nextC;
            if(command == 'U') {
                nextR = r + direction[0][0];
                nextC = c + direction[0][1];
            }
            else if(command == 'D') {
                nextR = r + direction[1][0];
                nextC = c + direction[1][1];
            }
            else if(command == 'R') {
                nextR = r + direction[2][0];
                nextC = c + direction[2][1];
            }
            else {
                nextR = r + direction[3][0];
                nextC = c + direction[3][1];
            }
            if(checkPos(nextR, nextC)) {
                String d1 = ""+r+c+nextR+nextC;
                String d2 = ""+nextR+nextC+r+c;
                set.add(d1);
                set.add(d2);
                r = nextR;
                c = nextC;
            }
        }
        return set.size()/2;
    }
    public boolean checkPos(int r, int c) {
        return (r<0 || r>=row || c<0 || c>=col) ? false : true;
    }
}