import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
        int half = (int)(Math.pow(2,n)) / 2;
        bw.write(find(n, r, c, half)+"");
        bw.flush();
        bw.close();
        br.close();
    }
    public static int[][] direction = {{0,0}, {0,1}, {1,0}, {1,1}};
    public static int find(int n, int r, int c, int half){
        if(n == 0) return 0;
        // 1사분면
        if(r < half && c< half) return find(n-1, r, c, half/2);
        if(r < half && c >= half) return half*half+find(n-1, r, c-half, half/2);
        if(r >= half && c < half) return half*half*2 + find(n-1, r-half, c, half/2);
        return half*half*3 + find(n-1, r-half, c-half, half/2);
        
    }
}