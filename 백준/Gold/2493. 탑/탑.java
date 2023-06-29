import java.util.*;
import java.io.*;

class Main {
    /*
        왼쪽으로 레이저 발사 -> 자기보다 큰 탑 중 가장 먼저 만나는 탑을 저장
        1. 현재 탑과 가장 가까운 탑을 비교하면서 자신보다 작은 탑은 제외시킨다( 그 뒤에 탑들에 대해 가장 큰 탑은 자기 자신이 될테니)
        2. 현재 탑보다 큰 탑이 있으면 그 탑의 인덱스를 저장한다(pop하지 않는다 - 현재 기준 가장 큰 탑이므로)
        3. 현재 탑을 스택에 넣어준다
    */
    static class Top{
        int h, n;
        public Top(int h, int n) {
            this.h=h; this.n=n;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] ans = new int[n];
        int[] top = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Stack<Top> stack = new Stack<>();
        for(int i=0; i<n; i++) {
            while(!stack.isEmpty() && stack.peek().h <= top[i]) stack.pop();

            if(!stack.isEmpty()) {
                ans[i] = stack.peek().n+1;
            }
            stack.push(new Top(top[i], i));
        }
        StringBuilder sb = new StringBuilder();
        Arrays.stream(ans).forEach(a -> sb.append(a + " "));
        System.out.println(sb);
        br.close();
    }
}