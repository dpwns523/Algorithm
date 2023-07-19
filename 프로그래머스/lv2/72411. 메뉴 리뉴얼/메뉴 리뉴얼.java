import java.util.*;
class Solution {
    public static Map<String, Integer> map = new HashMap<>();
    public static int[] courseMax = new int[11];
    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        for(int num : course) {
            for(String order : orders) {
                char[] tmp = order.toCharArray();
                Arrays.sort(tmp);
                order = new String(tmp);
                settingCourse(order, "", 0, 0, num);    // 각 order로 만들 수 있는 가지 수
            }
        }
        for(String key : map.keySet()) {
            int cnt = map.get(key);
            if(cnt >= 2 && courseMax[key.length()] == cnt) answer.add(key);
        }
        Collections.sort(answer);
        return answer.stream().toArray(String[]::new);
    }
    public void settingCourse(String order, String course, int idx, int cnt, int num) {
        if(cnt == num) {
            map.put(course, map.getOrDefault(course, 0) + 1);
            courseMax[num] = Math.max(courseMax[num], map.get(course));
            return;
        }
        for(int i=idx; i<order.length(); i++) {
            settingCourse(order, course+order.charAt(i), i+1, cnt+1, num);
        }
    }
}