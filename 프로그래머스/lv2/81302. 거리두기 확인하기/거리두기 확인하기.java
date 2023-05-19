import java.util.*;
class Solution {
    class Position{
        int r, c;
        public Position(int r, int c){
            this.r=r; this.c=c;
        }
    }
    public int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};
    public int row, col;
    public int[] solution(String[][] places) {
        row = places[0].length;
        col = places[0][0].length();
        int[] answer = new int[places.length];
        for(int k=0; k<places.length; k++) {    // 대기방 개수
            Queue<Position> queue = new LinkedList<>();
            for(int i=0; i<row; i++) {
                for(int j=0; j<col; j++){
                    if(places[k][i].charAt(j) == 'P') queue.offer(new Position(i, j));
                }
            }
            answer[k] = dfs(places[k], queue);
        }
        return answer;
    }
    public int dfs(String[] place, Queue<Position> queue) {
        int[][] visited = new int[row][col];
        while(!queue.isEmpty()) {
            Position p = queue.poll();
            visited[p.r][p.c] = 1;
            for(int i=0; i<direction.length; i++){
                int nextR = p.r + direction[i][0];
                int nextC = p.c + direction[i][1];
                if(checkPos(nextR, nextC) && visited[nextR][nextC] == 0){
                    visited[nextR][nextC] = 1;
                    if(place[nextR].charAt(nextC) == 'P') return 0;
                    if(place[nextR].charAt(nextC) == 'O' && !checkAnother(place, visited, nextR, nextC)) return 0;
                }
            }
        }
        return 1;
    }
    public boolean checkAnother(String[] place, int[][] visited, int r, int c) {
        for(int i=0; i<direction.length; i++){
                int nextR = r + direction[i][0];
                int nextC = c + direction[i][1];
                if(checkPos(nextR, nextC) && visited[nextR][nextC] == 0 && place[nextR].charAt(nextC) == 'P') return false;
            }
        return true;
    }
    public boolean checkPos(int r, int c) {
        return (r<0 || r>=row || c<0 || c>=col) ? false : true;
    }
    
}