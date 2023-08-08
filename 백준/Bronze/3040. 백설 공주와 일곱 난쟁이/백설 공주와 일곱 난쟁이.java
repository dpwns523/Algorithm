import java.util.*;
import java.io.*;

class Main{
    public static boolean isFind = false;
    public static int[] nums, seq, isSelected;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        nums = new int[9];
        isSelected = new int[9];
        seq = new int[7];

        for (int i = 0; i < 9; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        permutation(0);
        br.close();
    }

    public static void permutation(int cnt) {
        if(isFind) return;
        if (cnt == 7) {
            if (Arrays.stream(seq).sum() == 100) {
                for (int i = 0; i < 7; i++) {
                    System.out.println(seq[i]);
                }
                isFind = true;
            }
            return;
        }
        for (int i = 0; i < 9; i++) {
            if (isSelected[i] == 0) {
                isSelected[i] = 1;
                seq[cnt] = nums[i];
                permutation(cnt + 1);
                isSelected[i] = 0;
            }
        }
    }
}