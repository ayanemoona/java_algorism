package greedy;
import java.util.*;
public class pro_01 {

    public static void main(String[] args) {

        int n = 5;
        int[] lost = {2, 4};
        int[] reserve = {1, 3, 5};

        Solution sol = new Solution();

        int result = sol.solution(n, lost, reserve);

        System.out.println("결과 : " + result);
    }
}

class Solution {

    public int solution(int n, int[] lost, int[] reserve) {

        Arrays.sort(lost);
        Arrays.sort(reserve);

        List<Integer> reserve2 = new ArrayList<>();
        for (int a : reserve) {
            reserve2.add(a);
        }

        List<Integer> needStudent = new ArrayList<>();

        for (int a : lost) {

            if (reserve2.contains(a)) {
                reserve2.remove(Integer.valueOf(a));
                continue;
            }

            needStudent.add(a);
        }

        for (int a : reserve2) {

            if (needStudent.contains(a - 1)) {
                needStudent.remove(Integer.valueOf(a - 1));
                continue;
            }

            if (needStudent.contains(a + 1)) {
                needStudent.remove(Integer.valueOf(a + 1));
                continue;
            }
        }

        return n - needStudent.size();
    }
}
