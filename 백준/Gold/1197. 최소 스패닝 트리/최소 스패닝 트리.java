import java.util.*;
import java.io.*;

class Main{
    static class Edge{
        int start, end, value;
        public Edge(int start, int end, int value){
            this.start=start; this.end=end; this.value=value;
        }
    }
    public static Queue<Edge> graph = new PriorityQueue<>((g1, g2) -> g1.value - g2.value);
    public static int n, e, cnt =0, res=0;
    public static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        for(int i=1; i<=n; i++){    // 유니온파인드 리스트 세팅
            parent[i] = i;
        }
        e = Integer.parseInt(st.nextToken());
        for(int i=0; i<e; i++){    // 그래프 세팅
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
            graph.offer(new Edge(s, e, v));
        }
        while(!graph.isEmpty() && cnt != n-1){    // 유니온한 에지 수가 e-1개 일 때까지
            Edge edge = graph.poll();
            if(union(edge)){
                cnt++;
                res += edge.value;
            }
        }
        System.out.println(res+"");
        br.close();
    }
    public static boolean union(Edge edge) {
        int s = find(edge.start);
        int e = find(edge.end);
        if(s == e) return false;  // 사이클이 생김
        // 사이클이 생기는 것이 아니라면 유니온 연결을 해주고, 가중치의 합을 더해준다.
        if(s < e) parent[e] = s;
        else parent[s] = e;
        return true;
    }
    public static int find(int node) {
        if(node == parent[node]) return node;
        return parent[node] = find(parent[node]);   // 최종 대표 노드로 업데이트
    }
}