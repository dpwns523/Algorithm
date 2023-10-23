import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        if(isMake(S, T)) System.out.println("1");
        else System.out.println("0");
        br.close();
    }

    public static boolean isMake(String s, String t) {
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(t);
        while(!queue.isEmpty() && queue.peek().length() >= s.length()) {
            String prev = queue.poll();
            if(prev.equals(s)){
                return true;
            }
            if(prev.charAt(prev.length()-1) == 'A') queue.offer(prev.substring(0, prev.length()-1));
            if(prev.charAt(0) == 'B') {
                queue.offer(new StringBuffer(prev).reverse().substring(0, prev.length()-1));
            }
        }
        return false;
    }

}