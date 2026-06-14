package dp;
import java.util.*;

public class pro_02 {

    static class Solution {
        public int solution(int[][] triangle) {
            List<List<Integer>> arr = new ArrayList<>();
            int answer = 0;

            for (int i = 0; i < triangle.length; i++) {
                List<Integer> row = new ArrayList<>();

                for (int j = 0; j < triangle[i].length; j++) {
                    if (i - 1 < 0) {
                        row.add(triangle[i][j]);
                    } else {
                        if (j == 0) {
                            int data2 = triangle[i][j] + arr.get(i - 1).get(j);
                            row.add(data2);
                        } else if (j == i) {
                            int data = triangle[i][j] + arr.get(i - 1).get(j - 1);
                            row.add(data);
                        } else {
                            int data = triangle[i][j] + arr.get(i - 1).get(j - 1);
                            int data2 = triangle[i][j] + arr.get(i - 1).get(j);

                            if (data > data2) {
                                row.add(data);
                            } else {
                                row.add(data2);
                            }
                        }
                    }
                }
                arr.add(row);
            }

            List<Integer> lastRow = arr.get(arr.size() - 1);

            for (int a : lastRow) {
                if (answer < a) {
                    answer = a;
                }
            }

            return answer;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] triangle = {
            {7},
            {3, 8},
            {8, 1, 0},
            {2, 7, 4, 4},
            {4, 5, 2, 6, 5}
        };

        int result = sol.solution(triangle);

        System.out.println("결과: " + result);
    }
}