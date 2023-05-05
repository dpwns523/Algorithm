import java.util.*;
import java.io.*;

class Main{
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        int[] degree = new int[n+1];
        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            degree[b]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1; i<=n; i++){
            if(degree[i] == 0){
                queue.offer(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()){
            int p = queue.poll();
            sb.append(p+" ");
            for(int next : graph.get(p)) {
                degree[next]--;
                if(degree[next] == 0) queue.offer(next);
            }
        }
        System.out.print(sb);
        br.close();
    }
}
        