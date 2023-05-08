class Solution {
    public static int[][] map;
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        map = new int[rows][columns];
        int n = 1;
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                map[i][j] = n++; 
            }
        }
        for(int i=0; i<queries.length; i++){
            answer[i] = rotation(queries[i][0]-1, queries[i][1]-1, queries[i][2]-1, queries[i][3]-1);
        }
        return answer;
    }
    
    public int rotation(int r1, int c1, int r2, int c2) {
        int min = map[r1][c2], tmp = map[r1][c2];  // 윗행 오른쪽 저장해놓고 회전 후 덮어쓴다
        for(int i=c2; i>c1; i--) {   // 윗 행에서 오른쪽 방향 ->
            min = Math.min(min, map[r1][i-1]);
            map[r1][i] = map[r1][i-1];
        }
        
        for(int i=r1; i<r2; i++) {   //  아랫행에서 위쪽 방향 
            min = Math.min(min, map[i][c1]);
            map[i][c1] = map[i+1][c1];
        }
        
        for(int i=c1; i<c2; i++) {   // 아랫행에서 왼쪽 방향 <-
            min = Math.min(min, map[r2][i]);
            map[r2][i] = map[r2][i+1];
        }
        
        for(int i=r2; i>r1; i--) {  // 윗행에서 아랫쪽 방향
            min = Math.min(min, map[i][c2]);
            map[i][c2] = map[i-1][c2];
        }
        map[r1+1][c2] = tmp;
        
        return min;
    }
}