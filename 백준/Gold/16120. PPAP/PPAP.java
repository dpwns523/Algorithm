import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        if(isPPAP(s)) System.out.println("PPAP");
        else System.out.println("NP");
        br.close();


    }

    public static boolean isPPAP(String s) {
        int st = 0, pCnt = 0, size = s.length();
        if(s.equals("P")) return true;
        while (st < size) {
            if(s.charAt(st) == 'P') pCnt++;
            else if(s.charAt(st) == 'A') {
                if(pCnt>=2 && st < size-1 && s.charAt(st+1) == 'P') {
                    pCnt--;
                    st++;
                }
                else return false;
            }
            st++;
        }
        return pCnt == 1;

    }
}