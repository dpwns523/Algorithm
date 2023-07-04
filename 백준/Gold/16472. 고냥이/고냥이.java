import java.util.*;
import java.io.*;
public class Main {
    /*
        2
        abbcaccba

        st = 0, ed = 1 투 포인터로 접근
        aabbbcccaa ->의 경우도 알파벳의 종류는 3가지이므로 st와 ed만으로 판별할 수 없음
     */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] strings = br.readLine().toCharArray();
        int[] visited = new int[26];
        int st = 0, ed = 0, cnt = 0, max = 0;
        while(st <= ed && ed < strings.length) {
            if(visited[strings[ed]-'a'] == 0) {
                cnt++;
            }
            visited[strings[ed]-'a']++;
            if(cnt > n) max = Math.max(max, ed-st);
            while(cnt > n) {
                visited[strings[st]-'a']--;
                if(visited[strings[st]-'a'] == 0) cnt--;
                st++;
            }
            ed++;
        }
        System.out.println(max == 0 ? strings.length : max);
        br.close();
    }

}