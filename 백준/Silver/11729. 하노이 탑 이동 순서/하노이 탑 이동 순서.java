import java.util.*;
import java.io.*;

class Main{
    public static BufferedWriter bw;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        
        bw.write((int)(Math.pow(2,n)-1)+"\n");
        hanoi(n, 1, 2, 3);
        bw.flush();
        bw.close();
        br.close();
        
    }
    public static void hanoi(int n, int start, int mid, int to) throws IOException{
        if(n==1) {
            bw.write(start+" "+to+"\n");
            return;
        }
        // n-1개의 판을 중간으로 옮긴다
        hanoi(n-1, start, to, mid);
        bw.write(start+" "+to+"\n");
        // n-1개의 판을 최종 목적지로 옮긴다. 대신 시작은 mid 위에서 중간으로 옮겼기 때문
        hanoi(n-1, mid, start, to);
    }
}