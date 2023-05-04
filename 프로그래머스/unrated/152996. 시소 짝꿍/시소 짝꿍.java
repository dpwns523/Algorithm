import java.util.*;
class Solution {
    public static int[][] rates = {{2, 3}, {2, 1}, {3, 4}};
    public long solution(int[] weights) {
        long answer = 0;
        Map<Integer, Integer> weightMap = new HashMap<>();
        for(int i=0; i<weights.length; i++){
            weightMap.put(weights[i], weightMap.getOrDefault(weights[i], 0) + 1);
        }
        for(int weight : weightMap.keySet()){
            long num = weightMap.get(weight), pairPeople = 0;
            answer += num * (num - 1) / 2;    // 동일한 무게의 사람들 nC2
            for(int[] rate : rates){    // 2m, 3m에 앉았다면 2*weight = 3 * x
                int pairWeight = weight*rate[0] % rate[1] == 0 ? weight * rate[0] / rate[1] : 0;
                pairPeople += weightMap.containsKey(pairWeight) ? weightMap.get(pairWeight) : 0;
            }
            answer += num * pairPeople;     
        }
        return answer;
    }
}