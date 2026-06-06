package exhaustiveSearch;

import java.util.*;

public class pro_02{

    public static void main(String[] args) {

        int[] answers = {1, 3, 2, 4, 2};

        Solution2 sol = new Solution2();
        int[] result = sol.solution(answers);

        System.out.println(Arrays.toString(result));
    }
}

class Solution2 {
    public int[] solution(int[] answers) {
        int[] answer = {0, 0, 0};
        int[] one = {1, 2, 3, 4, 5};
        int[] two = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] three = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int high = 0;

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < answers.length; i++) {

            if (answers[i] == one[i % 5]) {
                answer[0] += 1;
                if (high < answer[0]) high = answer[0];
            }

            if (answers[i] == two[i % 8]) {
                answer[1] += 1;
                if (high < answer[1]) high = answer[1];
            }

            if (answers[i] == three[i % 10]) {
                answer[2] += 1;
                if (high < answer[2]) high = answer[2];
            }
        }

        for (int i = 0; i < answer.length; i++) {
            if (high == answer[i]) {
                result.add(i + 1);
            }
        }

        int[] arr = result.stream()
                .mapToInt(Integer::intValue)
                .toArray();

        return arr;
    }
}
