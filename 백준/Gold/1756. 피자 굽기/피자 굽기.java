import java.util.*;
import java.io.*;

class Main {
    /*
        피자를 반죽이 완성된 순서대로 굽는다.
        1. 반죽은 가장 안쪽부터 쌓인다
        2. 더 아래로 넣을 수 없을 때 그 위치에 굽는다
        3. 각 반죽 O(n) * 오븐 깊이 O(D) -> O(nD)=O(n^2)이므로 시간초과가 발생한다.
        4. 오븐의 길이가 제각각이고, 윗쪽에서 아래쪽으로 이동할 때 지름이 더 짧으면 그곳에 피자를 굽는다.
        5. 맨안쪽의 지름이 5였다가 그 다음 지름이 3이라면 실제 맨 안쪽은 들어갈 수 있는 지름이 3이다
        => 이러한 방식으로 값을 업데이트하면 제일 안 쪽 부터 작은 지름으로 정렬이된다
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());
        int[] oven = new int[d];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<d; i++) {
            oven[i] = Integer.parseInt(st.nextToken());
            if(i>0 && oven[i] > oven[i-1]) oven[i] = oven[i-1];
        }
        int[] pizza = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int e = d;
        for(int i=0; i<n; i++) {
            int s = 0;
            while(s<e) {
                int mid = (s + e) / 2;
                if(oven[mid] >= pizza[i]) s = mid + 1;
                else e = mid;
            }
            if(--e < 0) break;
        }
        System.out.println(e+1);
        br.close();
    }
}