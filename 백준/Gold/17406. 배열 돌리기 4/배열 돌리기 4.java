import java.util.*;
import java.io.*;

class Main{
    public static int[][] map, rotate, copy;
    public static int[] seq, isSelected;
    public static int n, m, k, min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n+1][m+1];
        copy = new int[n+1][m+1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        rotate = new int[k+1][3];
        seq = new int[k];
        isSelected = new int[k+1];
        for (int i = 1; i <= k; i++) {
            rotate[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        permutation(0);
        System.out.println(min);
        br.close();
    }

    public static void permutation(int cnt) {
        if(cnt == k) {
            for (int i = 0; i <= n; i++) {
                copy[i] = Arrays.copyOfRange(map[i], 0, map[i].length);
            }
            for (int i = 0; i < k; i++) {
                rotateArr(copy, rotate[seq[i]][0] - rotate[seq[i]][2], rotate[seq[i]][1] - rotate[seq[i]][2], rotate[seq[i]][0]+rotate[seq[i]][2], rotate[seq[i]][1]+rotate[seq[i]][2]);
            }
            for (int i = 1; i <= n; i++) {
                min = Math.min(min, Arrays.stream(copy[i]).sum());
            }
            return;
        }
        for (int i = 1; i <= k; i++) {
            if(isSelected[i] == 0) {
                isSelected[i] = 1;
                seq[cnt] = i;
                permutation(cnt+1);
                isSelected[i] = 0;
            }
        }

    }

    public static void rotateArr(int[][] map, int r1, int c1, int r2, int c2) {
        while(r2 > r1 && c2 > c1) {
            int rTmp = map[r1][c2];
            for (int i = c2; i > c1; i--) {
                map[r1][i] = map[r1][i - 1];
            }
            int cTmp = map[r2][c2];
            for (int i = r2; i > r1; i--) {
                map[i][c2] = map[i - 1][c2];
            }
            map[r1 + 1][c2] = rTmp;

            rTmp = map[r2][c1];
            for (int i = c1; i < c2; i++) {
                map[r2][i] = map[r2][i + 1];
            }
            map[r2][c2 - 1] = cTmp;
            for (int i = r1; i < r2; i++) {
                map[i][c1] = map[i + 1][c1];
            }
            map[r2 - 1][c1] = rTmp;
            r1++;
            r2--;
            c1++;
            c2--;
        }
    }
}