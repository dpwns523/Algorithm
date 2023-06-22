import java.util.*;
import java.io.*;

class Main {
    static class Present {
        int price, satis;
        public Present(int price, int satis){
            this.price=price; this.satis=satis;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());
        Present[] present = new Present[n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            present[i] = new Present(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        }
        Arrays.sort(present, (p1, p2) -> p1.price - p2.price);
        int s = 0, e = 0;
        long max = 0, tmp = 0;
        while(s<=e && e<n){
            if(present[e].price - present[s].price < d) tmp += present[e++].satis;
            else tmp -= present[s++].satis;     // 받은 선물 최소 가격을 다음으로 이동한 후 다시 검사
            max = Math.max(max, tmp);
        }
        System.out.println(max);
        br.close();

    }
}