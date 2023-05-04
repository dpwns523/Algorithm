import java.util.*;
class Solution {
    public static int[][] map, direction = {{0,1}, {0, -1}, {1, 0}, {-1,0}, {-1, -1}, {-1, 1}, {1, -1}, {1,1}};
    public int solution(String[] board) {
        map = new int [board.length][board[0].length()];
        for(int i=0; i<map.length; i++){
            String[] s = board[i].split("");
            for(int j=0; j<map[0].length; j++){
                if(s[j].equals("O")) map[i][j] = 1;
                else if(s[j].equals("X")) map[i][j] = 2;
            }
        }
        if(finishedGame()) return 1;
        else return 0;
    }
    public boolean finishedGame() {
        int oCount = (int)Arrays.stream(map).flatMapToInt(Arrays::stream).filter(m -> m == 1).count();
        int xCount = (int)Arrays.stream(map).flatMapToInt(Arrays::stream).filter(m -> m == 2).count();
        // X가 더 많을 경우
        if(oCount < xCount) return false;
        boolean firstWin = findWinner(1), secondWin = findWinner(2);
        if(firstWin && secondWin) return false;
        if(firstWin){   // 선공 승
            return oCount - 1 == xCount ? true : false;
        }
        if(secondWin){  // 후공 승
            return oCount == xCount ? true : false;
        }
        // 게임 진행 중
        return oCount == xCount || oCount -1 == xCount ? true : false;
    }
    public boolean findWinner(int n) {
        for(int i=0; i<map.length; i++){
            for(int j=0; j<map[0].length; j++){
                if(map[i][j] == n){
                    for(int k=0; k<direction.length; k++){
                        int cnt = 1, r = i, c = j;
                        for(int l=0; l<map.length; l++){
                            int nextR = r + direction[k][0];
                            int nextC = c + direction[k][1];
                            if(checkPos(nextR, nextC) && map[nextR][nextC] == n)cnt++;
                            else break;
                            r = nextR;
                            c = nextC;
                        }
                        if(cnt == map.length) return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean checkPos(int r, int c){
        return (r<0 || r>=map.length || c<0 || c>=map.length) ? false : true;
    }
}