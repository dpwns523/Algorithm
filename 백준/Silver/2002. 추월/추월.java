import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<String> in = new LinkedList<>();
        Queue<String> out = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        int[] visited = new int[n];
        // 차선이 하나 + 추월 차선 하나 일 경우 성립
        for(int i=0; i<n; i++) {
            String car = br.readLine();
            in.offer(car);
            map.put(car, i);
        }
        for(int i=0; i<n; i++) {
            out.offer(br.readLine());
        }
        int cnt = 0;
        while(!in.isEmpty() && !out.isEmpty()) {
            if(!in.peek().equals(out.peek())) {
                if(visited[map.get(in.peek())] == 1) {  // 이미 out에서 빼낸 추월 차량
                    in.poll();
                }
                else {
                    visited[map.get(out.poll())] = 1;
                    cnt++;
                }
            }
            else {
                visited[map.get(in.poll())] = 1;
                out.poll();
            }
        }
        System.out.println(cnt);
        br.close();
    }
}