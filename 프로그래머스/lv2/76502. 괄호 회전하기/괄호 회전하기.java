import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = 0;
        if(s.length() % 2 == 1) return 0;
        for(int i=0; i<s.length(); i++) {
            s = s.substring(1, s.length()) + s.substring(0,1);
            if(checkBracket(s)) answer++;
        }
        return answer;
    }
    public boolean checkBracket(String s) {
        Stack<Character> out = new Stack<>();
        for(int i=0; i<s.length(); i++){
            char first = s.charAt(i);
            try{
                // 괄호가 매칭되는지
                if(first == ']' && out.peek() != '[') return false;
                else if(first == ')' && out.peek() != '(') return false;
                else if(first == '}' && out.peek() != '{') return false;
                
                // 여는 괄호였다면 push, 위에서 return되지 않았다면 pop
                if(first == '[' || first == '(' || first == '{') out.push(first);
                else out.pop();
            }
            catch(EmptyStackException e){   // 짝이 맞지 않았다는 것
                return false;
            }
        }
        return true;
    }
}