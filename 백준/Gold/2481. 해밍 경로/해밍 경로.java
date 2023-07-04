import java.util.*;
import java.io.*;
public class Main {
    /*

     */
    public static int[][] permutation;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        Map<Integer, Integer> map = new HashMap<>();
        int start = 0;
        for(int i=1; i<=n; i++) {
            int w = Integer.parseInt(br.readLine(), 2);    // 2진수 변환
            map.put(w, i);    // i번째 수
            if(i == 1) start = w;
        }
        int[] link = new int[n+1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        link[1] = 1;
        while(!queue.isEmpty()) {   // 1번 부터 연결되는 경로 탐색
            int val = queue.poll();
            int idx = map.get(val);

            for(int i=0; i<k; i++) {
                int next = val ^ (1 << i);    // k번째 비트까지 토글 000 -> 001, 010, 100
                if(map.containsKey(next)) {    // 해밍거리가 1인 다음 코드 연결
                    int nextIdx = map.get(next);
                    if(link[nextIdx] == 0) {
                        link[nextIdx] = idx;
                        queue.offer(next);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(br.readLine());

        for(int i=0; i<m; i++) {
            int find = Integer.parseInt(br.readLine());
            if (link[find] == 0) sb.append("-1\n");
            else {
                Stack<Integer> stack = new Stack<>();
                stack.push(find);
                while (find != 1) {
                    find = link[find];
                    stack.push(find);
                }
                while (!stack.isEmpty()) {
                    sb.append(stack.pop() + " ");
                }
                sb.append("\n");
            }
        }
        System.out.print(sb);
        br.close();
    }

}