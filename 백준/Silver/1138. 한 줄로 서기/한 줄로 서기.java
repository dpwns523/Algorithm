import java.util.*;
import java.io.*;

class Main {
    /*
        키가 큰 순서대로(내림차순) 배열에 넣고, 뒤에 키 작은 사람이 같은 위치에 들어오면 키 큰 사람을 뒤로 미루면 됨
        ex: 키 3인 사람의 왼쪽이 3명이 있어야한다. -> arr[3]에 넣는다. ? ? ? 3 ? ?
        키 2인 사람의 왼쪽이 3명이 있어야 한다. -> arr[3]에 넣는다. ? ? ? 2 3 ? ? -> 키 큰 사람이 앞에 오는게 아니기 때문에 가능
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Integer> list = new ArrayList<>();
        for(int i=n-1; i>=0; i--){
            list.add(seq[i], i+1);  // 중간에 삽입 비용이 큼
        }
        list.stream().forEach(s -> System.out.print(s+" "));
        br.close();

    }
}