import java.io.*;
import java.util.*;

class Main {
    static class Seq{
        int idx, num;
        public Seq(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 0; test_case < T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken()), find = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine(), " ");
            ArrayList<Seq> docs = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                docs.add(new Seq(i, Integer.parseInt(st.nextToken())));
            }
            int cnt = 0;
            while(!docs.isEmpty()) {
                Seq max = docs.stream().max((s1, s2) -> Integer.compare(s1.num, s2.num)).get();
                if(docs.get(0).num < max.num) {
                    int idx = docs.indexOf(max);    // 최댓값 idx
                    for (int i = 0; i < idx; i++) {
                        docs.add(docs.remove(0));
                    }
                }
                cnt++;
                if(!docs.isEmpty() && docs.remove(0).idx == find) break;
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
        br.close();
    }

}