import java.io.*;
import java.util.*;

class Main {
    static class City {
        int num;
        long cost;
        public City(int num, long cost) {
            this.num = num;
            this.cost = cost;
        }
    }
    public static final long INF = Long.MAX_VALUE;
    public static ArrayList<ArrayList<City>> graph = new ArrayList<>();
    public static long[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        dist = new long[n+1];
        Arrays.fill(dist, INF);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            graph.get(v).add(new City(u, c));   // 면접 장소에서 면접자들한테 갈 것이므로 반대 방향으로 연결한다
        }
        Queue<City> queue = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++) {
            int meeting = Integer.parseInt(st.nextToken());
            dist[meeting] = 0;
            queue.add(new City(meeting, 0));
        }

        bfs(queue);

        long max = 0;
        int maxIdx = 0;
        for (int i=1; i<=n; i++) {
            if(dist[i] > max && dist[i] != INF) {
                max = dist[i];
                maxIdx = i;
            }
        }

        System.out.println(maxIdx +"\n"+ max);

    }
    public static void bfs(Queue<City> queue) {
        while (!queue.isEmpty()) {
            City city = queue.poll();
            for (City next : graph.get(city.num)) {
                if(dist[next.num] > city.cost + next.cost) {
                    dist[next.num] = city.cost + next.cost;
                    queue.offer(new City(next.num, dist[next.num]));
                }
            }
        }
    }
}