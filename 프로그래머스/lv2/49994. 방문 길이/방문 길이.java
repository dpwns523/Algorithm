import java.util.*;
class Solution {
    public static final int row = 11, col = 11;
    public static int[] command = {1,-1};
    public int solution(String dirs) {
        int answer = 0;
        int[][] map = new int[row][col];
        int r = 5, c = 5;
        // 도착점이 아닌 이동한 길에 대해서 체크하는 것 현재 위치 -> 다음 위치가 기록되어야 함
        Set<String> set = new HashSet<>();
        for(String dir : dirs.split("")) {
            int nextR = r, nextC = c;
            if(dir.equals("U")){
                nextR += command[0];
            }
            else if(dir.equals("D")) {
                nextR += command[1];
            }
            else if(dir.equals("R")) {
                nextC += command[0];
            }
            else {
                nextC += command[1];
            }
            if(nextR >= 0 && nextR < row && nextC >= 0 && nextC < col) {
                String d1 = ""+r+c+nextR+nextC;
                String d2 = ""+nextR+nextC+r+c;
                if(!set.contains(d1)) answer++;
                set.add(d1);
                set.add(d2);
                r = nextR;
                c = nextC;
            }
        }
        
        return answer;
    }
}