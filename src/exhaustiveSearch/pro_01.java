package exhaustiveSearch;
import java.util.*;
public class pro_01 {

    public static void main(String[] args) {

        int[][] sizes = {
            {60, 50},
            {30, 70},
            {60, 30},
            {80, 40}
        };

        Solution sol = new Solution();
        int result = sol.solution(sizes);

        System.out.println("정답 : " + result);
    }
}
class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int max = 0;
        int min = 0;

        for (int[] a : sizes) {

            if (a[0] < a[1]) {
                int temp = a[0];
                a[0] = a[1];
                a[1] = temp;
            }

            if (a[0] > max) {
                max = a[0];
            }

            if (a[1] > min) {
                min = a[1];
            }
        }

        answer = max * min;
        return answer;
    }
}