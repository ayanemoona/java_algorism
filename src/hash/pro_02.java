import java.util.*;
public class pro_02 {
    public static void main(String[] args) {

        pro_02 s = new pro_02();
        int[] nums = {3, 1, 2, 3};

        int result = s.solution(nums);

        System.out.println(result);
    }
    

    public int solution(int[] nums) {
            int answer = 0;
            int pokemon = nums.length;
            int pick = pokemon / 2;

            Set<Integer> set = new HashSet<>();

            for (int i : nums) {
                set.add(i);
            }

            if (pick > set.size()) {
                answer = set.size();
            } else {
                answer = pick;
            }

            return answer;
        }



    

}
