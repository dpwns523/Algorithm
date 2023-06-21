import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), s = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        int[][] volume = new int[n][m+1];
        Arrays.stream(volume).flatMapToInt(Arrays::stream).forEach(v -> v=-1);
        int[] v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        if(s + v[0] <= m) volume[0][s+v[0]] = 1;
        if(s - v[0] >= 0) volume[0][s-v[0]] = 1;

        for(int i=1; i<n; i++){
            for(int j=0; j<=m; j++) {
                if(volume[i-1][j] == 1) {   // 이전 볼륨에서 빼거나 더한 값 저장
                    if(j - v[i] >= 0) volume[i][j-v[i]] = 1;
                    if(j + v[i] <= m) volume[i][j+v[i]] = 1;
                }
            }
        }
        for(int i=m; i>=0; i--) {
            if(volume[n-1][i] == 1) {
                System.out.println(i);
                break;
            }
            if(i == 0) System.out.println("-1");
        }
        br.close();
    }
}