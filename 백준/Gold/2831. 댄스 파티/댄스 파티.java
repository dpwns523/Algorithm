import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> manWantTaller = new ArrayList<>();
        List<Integer> manWantSmaller = new ArrayList<>();
        List<Integer> womanWantTaller = new ArrayList<>();
        List<Integer> womanWantSmaller = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            int h = Integer.parseInt(st.nextToken());
            if(h < 0) manWantSmaller.add(Math.abs(h));
            else manWantTaller.add(h);
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            int h = Integer.parseInt(st.nextToken());
            if(h < 0) womanWantSmaller.add(Math.abs(h));
            else womanWantTaller.add(h);
        }
        Collections.sort(manWantTaller);    // 오름차순
        Collections.sort(manWantSmaller);
        Collections.sort(womanWantTaller);
        Collections.sort(womanWantSmaller);
        int idx = 0, cnt = 0, size = womanWantSmaller.size();

        for(int man : manWantTaller) {
            while(idx < size) {
                if(man < womanWantSmaller.get(idx)){
                    cnt++;
                    idx++;
                    break;
                }
                else idx++;
            }
            if(idx == size) break;
        }
        idx = 0;
        size = manWantSmaller.size();
        for(int woman : womanWantTaller) {
            while(idx < size) {
                if(woman < manWantSmaller.get(idx)) {
                    cnt++;
                    idx++;
                    break;
                }
                else idx++;
            }
            if(idx == size) break;
        }
        System.out.println(cnt);
        br.close();
    }
}