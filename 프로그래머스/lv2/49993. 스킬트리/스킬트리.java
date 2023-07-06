import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        char[] skills = skill.toCharArray();
        List<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i=0; i<27; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=skills.length-1; i>=0; i--) {
            int idx = skills[i] - 'A';
            for(int j=i-1; j>=0; j--) {
                graph.get(idx).add(skills[j] - 'A');
            }
        }
        
        for(String tree : skill_trees) {
            int[] visited = new int[27];
            boolean flag = true;
            char[] trees = tree.toCharArray();
            for(int i=0; i<trees.length; i++) {
                int idx = trees[i] - 'A';
                for(int n : graph.get(idx)) {
                    if(visited[n] == 0) flag = false;
                }
                visited[idx] = 1;
            }
            if(flag) answer++;
        }
        return answer;
    }
}