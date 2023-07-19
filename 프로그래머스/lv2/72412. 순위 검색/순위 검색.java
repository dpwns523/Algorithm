import java.util.*;
class Solution {
    public static Map<String, List<Integer>> map = new HashMap<>();
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        Set<Integer> set = new HashSet<>();
        
        for (String s : info) { // 가능한 쿼리 생성
            String[] infos = s.split(" ");
            set.add(Integer.parseInt(infos[4]));
            inputMap(infos, "", 0);
        }

        for(String key : map.keySet()) {
            Collections.sort(map.get(key));
        }
        int idx=0;
        for(String q : query) {
            int cnt = 0;
            // 쿼리 조회
            String[] infos = q.split(" ");
            int n = Integer.parseInt(infos[7]);
            String key = String.join(" ", Arrays.copyOfRange(infos, 0, infos.length-1));
            if(map.containsKey(key)) {
                List<Integer> list = map.get(key);
                int queryScoreIdx = binarySearch(list, n);
                answer[idx] = list.size() - queryScoreIdx;
            }
            idx++;
        }
        return answer;
    }
    
    public static void inputMap(String[] infos, String query, int cnt) {
        if(cnt == 4) {
            int score = Integer.parseInt(infos[cnt]);
            if(!map.containsKey(query)){
                List<Integer> l = new ArrayList<>();
                l.add(score);
                map.put(query, l);
            }
            else map.get(query).add(score);
            return;
        }
        if(cnt < 3) {
            inputMap(infos, query+"- and ", cnt+1);
            inputMap(infos, query+ infos[cnt] +" and ", cnt+1);
        }
        else {
            inputMap(infos, query+"-", cnt+1);
            inputMap(infos, query+ infos[cnt], cnt+1);    
        }
    }
    
    public static int binarySearch(List<Integer> list, int key) {
        int low = 0, high = list.size() - 1;
        while (low <= high) { 
            int mid = low + (high - low) / 2;
            if (list.get(mid) >= key) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}