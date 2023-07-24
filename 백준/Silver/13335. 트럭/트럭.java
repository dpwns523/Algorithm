import java.util.*;
import java.io.*;

class Main {
    static class Input {
        int idx, time;
        public Input(int idx, int time) {
            this.idx=idx;
            this.time=time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken()), l = Integer.parseInt(st.nextToken());
        
        int[] truck = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        Deque<Input> queue = new ArrayDeque<>();
        
        queue.addLast(new Input(0, 0));
        l -= truck[0];
        int next = 1, t = 1;

        while(!queue.isEmpty()) {
            if((t - queue.peekFirst().time) % w == 0) {
                l += truck[queue.pollFirst().idx];
            }
            if((queue.isEmpty() || t - queue.peekLast().time > 0) && next < n && l - truck[next] >= 0) {	// 연속 삽입 불가
                queue.addLast(new Input(next, t));
                l -= truck[next++];
            }
            t++;
        }
        System.out.println(t);
        br.close();
    }
}