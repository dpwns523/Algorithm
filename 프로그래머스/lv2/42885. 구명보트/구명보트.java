import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int st = 0, ed = people.length-1, sum = 0;
        while(st < ed) {
            if(people[st] + people[ed] <= limit) {
                st++;
                ed--;
            }
            else ed--;
            
            answer++;
        }
        return st == ed ? ++answer : answer;    // 마지막 인원
    }
}