import java.util.*;
import java.io.*;
public class Main {
    public static int n;
    public static List<String> answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int T=0; T<t; T++) {
            n = Integer.parseInt(br.readLine());
            answer = new ArrayList<>();
            dfs(2, "1", 1, false);
            Collections.sort(answer);
            answer.stream().forEach(a -> System.out.println(a));
            System.out.println();
        }
        br.close();
    }
    public static void dfs(int cnt, String query, int sum, boolean isBlank) {
        if(n+1 == cnt) {
            if(sum == 0) {
                answer.add(query);
            }
            return;
        }
        dfs(cnt+1, query+"+"+cnt, sum+cnt, false);
        dfs(cnt+1, query+"-"+cnt, sum-cnt, false);
        if(!isBlank) {
            if(query.length() < 2)  dfs(cnt+1, query+" "+cnt, 10*(cnt-1) + cnt, true);  // 1만 있을 때
            else if(query.charAt(query.length()-2) == '+') {
                dfs(cnt+1, query+" "+cnt, ((sum-(cnt-1)) + (10*(cnt-1) + cnt)), true);
            }
            else dfs(cnt+1, query+" "+cnt, ((sum+(cnt-1)) - (10*(cnt-1) + cnt)), true);
        }
    }
}