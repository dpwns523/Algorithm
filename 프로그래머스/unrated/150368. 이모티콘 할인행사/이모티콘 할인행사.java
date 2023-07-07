import java.util.*;
class Solution {
    class Kakao{
        int total, amount;
        public Kakao(int total, int amount){
            this.total=total; this.amount=amount;
        }
    }
    public static ArrayList<Kakao> answer = new ArrayList<>();
    public static int[] discount = {10, 20, 30, 40};    // 가능한 할인 비율
    public int[] solution(int[][] users, int[] emoticons) {
        int[] dis = new int[emoticons.length];    // 각 인덱스에 이모티콘 할인율이 들어감
        dfs(users, emoticons, dis,0);
        Collections.sort(answer, (a,b) -> a.total == b.total ? b.amount-a.amount : b.total-a.total);
        Kakao k = answer.get(0);
        return new int[]{k.total, k.amount};
    }
    public void dfs(int[][] users, int[] emoticons, int[] dis, int cnt){
        if(cnt == emoticons.length){  // 계산
            int amount = 0;
            int join = 0;
            for(int i=0; i<users.length; i++){
                int sum = 0;
                for(int j=0; j<emoticons.length; j++){
                    if(users[i][0] <= dis[j]) sum += (emoticons[j]/100)*(100-dis[j]); 
                }
                if(sum >= users[i][1]) join +=1;
                else amount += sum;
            }
            answer.add(new Kakao(join, amount));
        }
        else{
            for(int i=0; i<discount.length; i++){   // 가능한 할인율로 완탐
                dis[cnt] = discount[i];
                dfs(users,emoticons,dis,cnt+1);
            }
        }
    }
}