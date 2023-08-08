import java.util.*;
import java.io.*;

class Main{
    public static final int size = 101, recSize = 10;
    public static int[][] maps = new int[size][size];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            makeRect(r,c);
        }
        System.out.println(Arrays.stream(maps).flatMapToInt(map -> Arrays.stream(map)).filter(m -> m==1).count()+"");
    }
    public static void makeRect(int row, int col){
        for(int i=row; i<row+recSize; i++){
            for(int j=col; j<col+recSize; j++){
                maps[i][j] = 1;
            }
        }
    }
}