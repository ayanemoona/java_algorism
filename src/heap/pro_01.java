package heap;
import java.util.*;

public class pro_01 {

    public static void main(String[] args) {

        Solution solution = new Solution();

        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;

        int result = solution.solution(scoville, K);

        System.out.println("정답: " + result);
    }
}

class Solution {

    public int solution(int[] scoville, int K) {

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int s : scoville) {
            queue.offer(s);
        }

        int answer = 0;

        while (queue.size() > 1) {

            if (queue.peek() >= K) {
                break;
            }

            int value1 = queue.poll();
            int value2 = queue.poll();

            int result = value1 + (value2 * 2);

            queue.offer(result);

            answer++;
        }

        if (queue.peek() < K) {
            return -1;
        }

        return answer;
    }
}