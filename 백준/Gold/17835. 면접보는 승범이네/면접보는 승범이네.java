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
    public static ArrayList<ArrayList<City>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            graph.get(v).add(new City(u, c));   // 면접 장소에서 면접자들한테 갈 것이므로 반대 방향으로 연결한다
        }
        Queue<City> queue = new PriorityQueue<>((City c1, City c2) -> Long.compare(c1.cost, c2.cost));

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++) {
            queue.add(new City(Integer.parseInt(st.nextToken()), 0));
        }

        City city = bfs(queue);

        System.out.println(city.num +"\n"+ city.cost);

    }
    public static City bfs(Queue<City> queue) {
        Queue<City> res = new PriorityQueue<>((City c1, City c2) -> Long.compare(c2.cost, c1.cost));
        Set<Integer> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            City city = queue.poll();
            if(visited.contains(city.num)) continue;
            visited.add(city.num);
            res.add(city);

            for (City next : graph.get(city.num)) {
                queue.offer(new City(next.num, next.cost + city.cost));
            }
        }
        return res.isEmpty() ? new City(0,0) : res.poll();
    }
}