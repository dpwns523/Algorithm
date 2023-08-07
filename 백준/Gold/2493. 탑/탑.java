import java.util.*;
import java.io.*;

class Main
{
    static class Top {
        int h, idx;
        public Top(int h, int idx) {
            this.h = h;
            this.idx = idx;
        }
    }
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] tops = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Stack<Top> stack = new Stack<>();
        for(int i=0; i<n; i++) {
            // 현재 탑이 가장 큰 탑인지 확인 -> 가장 큰 탑이라면 앞의 탑들은 무시
            while(!stack.isEmpty() && stack.peek().h <= tops[i]) stack.pop();
            int currH = tops[i];
            if(!stack.isEmpty()) tops[i] = stack.peek().idx+1;
            else tops[i] = 0;

            stack.push(new Top(currH, i));
        }
        StringBuilder sb = new StringBuilder();
        Arrays.stream(tops).forEach(top -> sb.append(top).append(" "));
        System.out.println(sb);
        br.close();
    }
}