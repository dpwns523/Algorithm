import java.util.*;
import java.io.*;

public class Main {
    public static int[][] map, direction = {{1,0},{-1,0},{0,1},{0,-1}};
    public static int cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] res = new int[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int i = n-1; i >= 0; i--) {
            if(stack.isEmpty()) res[i] = -1;
            else {
                while (!stack.isEmpty()) {
                    if(stack.peekFirst() > arr[i]) {
                        res[i] = stack.peekFirst();
                        break;
                    }
                    else stack.pollFirst();
                }
                if(res[i] == 0) res[i] = -1;
            }
            if(i>0 && arr[i-1] < arr[i]) stack.addFirst(arr[i]);
        }
        for (int i = 0; i < n; i++) {
            sb.append(res[i]).append(" ");
        }
        System.out.println(sb);
        br.close();

    }

}