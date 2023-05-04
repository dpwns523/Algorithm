import java.util.*;
class Solution {
    class Mineral{
        int dia, iron, stone, sum;
        public Mineral (int dia, int iron, int stone, int sum){
            this.dia=dia; this.iron=iron; this.stone=stone; this.sum=sum;
        }
    }
    public int[] dia = {1, 1, 1}, iron = {5, 1, 1}, stone = {25, 5, 1};
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        List<Mineral> group = new ArrayList<>();
        int sum = 0, idx = 0, d = 0, i = 0, s = 0, maxPick = Arrays.stream(picks).sum();
        for(int k=0; k<minerals.length; k++){
            if(minerals[k].equals("diamond")) {
                sum += 25;
                d += 1;
            }
            else if(minerals[k].equals("iron")) {
                sum += 5;
                i += 1;
            }
            else {
                sum += 1;
                s += 1;
            }
            
            if((k+1) % 5 == 0 || k == minerals.length-1){
                group.add(new Mineral(d, i, s, sum));
                sum = 0;
                d = 0;
                i = 0;
                s = 0;
            }
            if(group.size() == maxPick) break;
        }
        
        Collections.sort(group, (g1, g2) -> g2.sum - g1.sum);
        for(Mineral g : group){
            if(picks[0] > 0){
                picks[0]--;
                answer += g.dia * dia[0] + g.iron * dia[1] + g.stone * dia[2];
            }
            else if(picks[1] > 0){
                picks[1]--;
                answer += g.dia * iron[0] + g.iron * iron[1] + g.stone * iron[2];
            }
            else if(picks[2] > 0){
                picks[2]--;
                answer += g.dia * stone[0] + g.iron * stone[1] + g.stone * stone[2];
            }
        }
        
        return answer;
    }
}