import java.util.*;
import java.io.*;

class Main {
    static class Load {
        int s, e, d;
        public Load(int s, int e, int d) {
            this.s=s; this.e=e; this.d=d;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());
        List<Load> loads = new ArrayList<>();
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            if(e > d || e-s < c) continue;
            loads.add(new Load(s, e, c));
        }
        int idx=0, loc=0;
        Load load = new Load(0, 0, 0);
        if(!loads.isEmpty()){
            Collections.sort(loads, (l1, l2) -> l1.s == l2.s ? l1.e - l2.e :l1.s - l2.s);
            load = loads.get(idx++);
        }
        int[] dist = new int[10001];
        Arrays.fill(dist, 10001);
        dist[0] = 0;    // 시작

        while(loc<d) {
            if (load.s == loc) {    // 시작지점이 같은 지름길이 여러개 있을 수 있음
                dist[load.e] = Math.min(dist[load.e], dist[load.s] + load.d);
                load = idx == loads.size() ? new Load(-1, -1, 10001) : loads.get(idx++);
            }
            else {
                dist[loc + 1] = Math.min(dist[loc] + 1, dist[loc + 1]);
                loc++;
            }
        }
        System.out.println(dist[d]);
        br.close();
    }

}