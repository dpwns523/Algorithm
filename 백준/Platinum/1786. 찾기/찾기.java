import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] T = br.readLine().toCharArray();
        char[] P = br.readLine().toCharArray();

        int[] pi = makePattern(P);
        ArrayList<Integer> pos = new ArrayList<>();
        int cnt = 0;
        for (int i = 0, j = 0; i < T.length; i++) {
            while (j > 0 && T[i] != P[j]) { // 패턴 불일치 시 뒤로 이동
                j = pi[j-1];
            }
            if (T[i] == P[j]) {
                if(j==P.length-1) {
                    cnt++;
                    pos.add(i-j+1);
                    j = pi[j];
                } else j ++;
            }
        }
        System.out.println(cnt);
        for (int p : pos) {
            System.out.print(p+" ");
        }
        br.close();
    }

    public static int[] makePattern(char[] pattern) {
        int[] p = new int[pattern.length];

        for (int i = 1, j = 0; i < pattern.length; i++) {
            while(j > 0 && pattern[i] != pattern[j]) {
                j = p[j-1];
            }
            if(pattern[j] == pattern[i]) {
                p[i] = ++j;
            }
            else j = 0;
        }
        return p;
    }
}