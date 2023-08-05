import java.util.*;
import java.io.*;

public class Main{
    static class Location{
        int loc, cnt;
        public Location(int loc, int cnt){
            this.loc = loc; this.cnt = cnt;
        }
    }
    public static int[] visited;
    public static int n, k;
    public static int[] direction = {-1, 1, 2};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        visited = new int[100001];
        bw.write(bfs()+"");
        bw.flush();
        bw.close();
        br.close();
    }
    public static int bfs(){
        Queue<Location> queue = new LinkedList<>();
        queue.offer(new Location(n,0));
        while(!queue.isEmpty()){
            Location location = queue.poll();
            if(location.loc == k) return location.cnt;
            visited[location.loc] = 1;
            for(int i=0; i<direction.length; i++){
                int d;
                if(direction[i] == 2) d = location.loc*direction[i];
                else d = location.loc + direction[i];

                if(d >=0 && d < 100001 && visited[d] == 0){
                    queue.offer(new Location(d, location.cnt+1));
                }
            }
        }
        return 0;
    }
}