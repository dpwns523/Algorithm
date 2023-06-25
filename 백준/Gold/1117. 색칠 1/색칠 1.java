import java.util.*;
import java.io.*;

class Main {
    /*
        처음 f를 기준으로 접었을 때
        겹치는 부분 vs 안겹치는 부분을 나눠 색칠되는 범위를 구한다
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long W = Integer.parseInt(st.nextToken()), H = Integer.parseInt(st.nextToken()),
                f = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken()),
                x1 = Integer.parseInt(st.nextToken()), y1 = Integer.parseInt(st.nextToken()),
                x2 = Integer.parseInt(st.nextToken()), y2 = Integer.parseInt(st.nextToken());

        // 전체 넓이에서 뺀 값을 반환
        long wh = W * H, draw;
        // wf는 겹치는 구간을 판정하기 위함 -> 접은 부분이 더 긴 경우, 접은 부분이 짧은 경우
        long wf = f > W-f ? W-f : f;

        if(x1 < wf) {   // 시작점이 f로 접었을 때 겹치는 위치에 있는지
            if(x2 < wf) { // 끝점도 겹치는 부분에 있음
                draw = (x2 - x1) * (y2 - y1) * 2;
            }
            else {  // 끝점은 안겹침
                draw = (wf - x1) * (y2 - y1) * 2;
                draw += (x2 - wf) * (y2 - y1);
            }
        }
        else draw = (x2 - x1) * (y2 - y1);  // 단면
        wh -= draw*(c+1);
        System.out.println(wh);
        br.close();
    }
}