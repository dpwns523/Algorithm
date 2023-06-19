import java.util.*;
import java.io.*;

class Main {
    // +, -, x, /
    public static int[] command = new int[4];
    public static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        command = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 커멘드를 넘겨줘서 연산한다.
        for(int i=0; i<command.length; i++) {
            if(command[i] > 0){
                command[i] -= 1;
                dfs(cal(i, num[0], num[1]), 2, num);
                command[i] += 1;
            }
        }
        System.out.print(max+"\n"+min);
        br.close();
    }

    public static void dfs(int curr, int len, int[] num) {
        if(len == num.length) {
            max = Math.max(curr, max);
            min = Math.min(curr, min);
            return;
        }
        for(int i=0; i<command.length; i++) {
            if(command[i] > 0) {
                command[i] -= 1;
                dfs(cal(i, curr, num[len]), len+1, num);
                command[i] += 1;
            }
        }
    }
    public static int cal(int command, int n1, int n2) {
        if(command == 0) return n1 + n2;
        else if(command == 1) return n1 - n2;
        else if(command == 2) return n1 * n2;
        else return n1 / n2;
    }
}