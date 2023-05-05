import java.util.*;
import java.io.*;

class Main{
    static class City{
        int num, w;
        public City(int num, int w){
            this.num=num; this.w=w;
        }
    }
    public static List<ArrayList<City>> graph = new ArrayList<>();
    public static int[] d, visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
            graph.get(s).add(new City(e, w));
        }
        long[] distance = new long[n + 1];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[1] = 0;
        // 정점의 개수 -1번 동안 최단거리 업데이트 + 한 번 더 업데이트체크(음수 사이클 )
        for(int i=1; i<=n; i++){    // i개의 에지(간선)를 이용했을 때의 최단거리 업데이트
            for(int j=1; j<=n; j++){    // 1번 노드부터 n번 노드까지
                if(distance[j] == Long.MAX_VALUE) continue;
                for(City city : graph.get(j)){
                    if(distance[city.num] > distance[j] + city.w){
                        if(i == n){
                            System.out.println("-1");
                            br.close();
                            return;
                        }
                        distance[city.num] = distance[j] + city.w;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=2; i<=n; i++){
            if(distance[i] == Long.MAX_VALUE) sb.append("-1\n");
            else sb.append(distance[i]+"\n");
        }
        System.out.print(sb);
        br.close();
    }

}
