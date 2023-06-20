    import java.util.*;
    import java.io.*;

    class Main {
        public static int[] parent;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());
            int m = Integer.parseInt(br.readLine());
            parent = new int[n+1];
            for(int i=1; i<=n; i++){
                parent[i] = i;
            }
            for(int i=1; i<=n; i++) {
                int[] node = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for(int j=0; j<node.length; j++){
                    if(node[j] == 1) union(i, j+1);
                }
            }
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = find(Integer.parseInt(st.nextToken()));
            for(int i=0; i<m-1; i++) {
                if(start != find(Integer.parseInt(st.nextToken()))){
                    System.out.println("NO");
                    break;
                }
                if(i == m-2) System.out.println("YES");
            }
            br.close();
        }
        public static void union(int a, int b) {
            int p1 = find(a), p2 = find(b);
            if(p1 < p2) parent[p2] = p1;
            else parent[p1] = p2;
        }

        public static int find(int a) {
            if(a == parent[a]) return a;
            return parent[a] = find(parent[a]);
        }
    }