import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()), cnt = 0;

        while(n>0) {
            if(n % 5 == 0){
                cnt += n / 5;
                n -= (n / 5) * 5;
            }
            else {
                n -= 3;
                cnt++;
            }
        }
        if(n<0) System.out.println("-1");
        else System.out.println(cnt + "");
        br.close();
    }
}