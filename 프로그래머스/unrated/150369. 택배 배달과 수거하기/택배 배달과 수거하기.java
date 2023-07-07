import java.util.*;
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int d = 0;
        int p = 0;
        for(int i=n-1; i>=0; i--){
            d -= deliveries[i]; // 배달하러 감
            p -= pickups[i];    // 수거하러 감
            int cnt = 0;
            while(d < 0 || p < 0){  // 음수라는 것은 배달을 한번에 못했거나 한 번에 수거하지 못함
                // 수거, 배달 둘 중 하나라도 못했다면 왔다갔다하면서 배달을 할 수 있음
                d += cap;   
                p += cap;
                cnt +=1;
            }
            answer += (i+1)*2*cnt;
        }
        return answer;
    }
}