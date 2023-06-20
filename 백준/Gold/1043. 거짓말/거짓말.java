import java.util.*;
import java.io.*;

class Main {
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        for(int i=1; i<=n; i++) {
            parent[i] = i;
        }
        st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());    // 진실을 아는 사람 수
        for (int i = 0; i < num; i++) {
            parent[Integer.parseInt(st.nextToken())] = 0;   // 진실을 아는 사람은 0번 그룹
        }

        Set<Integer>[] party = new HashSet[m];  // 각 파티 참가자 저장
        for(int i=0; i<m; i++){
            party[i] = new HashSet<>();
        }

        int cnt = 0;
        for(int i=0; i<m; i++) {
            int [] participant = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (participant.length == 2){
                party[i].add(participant[1]);
                continue;
            }
            for(int j=1; j<participant[0]; j++){
                union(participant[j], participant[j+1]);    // 같은 그룹으로 설정
                party[i].add(participant[j]);    // 참가 인원 기록
                party[i].add(participant[j+1]);
            }
        }
        for(int i=0; i<m; i++) {
            boolean flag = false;
            for(int s : party[i]){
                if(find(s) == 0){
                    flag = true;
                    break;
                }
            }
            if(!flag) cnt++;
        }
        System.out.println(cnt);
        br.close();
    }

    public static void union(int a, int b){
        int p1 = find(a), p2 = find(b);
        if(p1 < p2) parent[p2] = p1;
        else parent[p1] = p2;
    }

    public static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }
}