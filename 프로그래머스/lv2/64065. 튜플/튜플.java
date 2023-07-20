import java.util.*;
class Solution {
    public int[] solution(String s) {
        Map<Integer, Integer> keyMap = new HashMap<>();
        Map<Integer, Integer> valueMap = new TreeMap<>((n1, n2) -> n2 - n1);
        
        s = s.replaceAll("\\{|\\}", ""); // { 또는 } 문자를 빈 문자열로 대체 
        s = s.replaceAll(",", " ");
        int[] nums = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int i=0; i<nums.length; i++) {
            keyMap.put(nums[i], keyMap.getOrDefault(nums[i], 0) + 1);
        }
        
        for(int key : keyMap.keySet()) {
            valueMap.put(keyMap.get(key), key);
        }
        
        int[] answer = new int[valueMap.size()];
        int idx = 0;
        for(int cnt : valueMap.keySet()) {
            answer[idx++] = valueMap.get(cnt);
        }
        return answer;
    }
}