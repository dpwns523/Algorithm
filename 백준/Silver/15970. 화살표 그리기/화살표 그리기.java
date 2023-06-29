import java.util.*;
import java.io.*;

class Main {
    /*
        같은 색의 점들을 잇는 화살표 중 가장 길이가 짧은 화살표들의 합
        1. 색을 기준으로 정렬한다 -> 같은 색들을 선형탐색할 수 있다.
        2. 선형탐색을 하면서 같은 색이면 길이 저장(왼쪽, 오른쪽을 확인)
    */
    static class Line{
        int pos, color;
        public Line(int pos, int color) {
            this.pos=pos; this.color=color;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] ans = new int[n];
        Arrays.fill(ans, Integer.MAX_VALUE);
        Line[] line = new Line[n];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            line[i] = new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(line, (l1, l2) -> l1.color == l2.color ? l1.pos - l2.pos : l1.color - l2.color);
        for(int i=0; i<n; i++) {
            if(i > 0) {    // 왼쪽 확인
                if(line[i-1].color == line[i].color) {
                    ans[i] = Math.min(ans[i], line[i].pos - line[i-1].pos);
                }
            }
            if(i + 1 < n) {    // 오른쪽 확인
                if(line[i].color == line[i+1].color) {
                    ans[i] = Math.min(ans[i], line[i+1].pos - line[i].pos);
                }
            }
        }
        System.out.println(Arrays.stream(ans).sum());
        br.close();
    }
}