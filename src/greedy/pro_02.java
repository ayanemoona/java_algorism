package greedy;


public class pro_02 {

    public static void main(String[] args) {
        Solution2 solution = new Solution2();

        String name = "JEROEN";

        int result = solution.solution(name);

        System.out.println("결과 : " + result);
    }
}

class Solution2 {
    public int solution(String name) {
        char[] arr = name.toCharArray();
        char[] abc = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        int answer = name.length() - 1;
        int aStart = 0;
        boolean a = true;

        for (int i = 0; i < arr.length; i++) {
            if (i > 0 && arr[i] == 'A' && a == true) {
                aStart += 1;
            } else if (i > 0 && arr[i] != 'A') {
                a = false;
            }

            if (arr[i] == 'A') {
                continue;
            }

            for (int j = 1; j < abc.length; j++) {
                if (arr[i] == abc[j]) {
                    answer += j;
                    break;
                }

                if (arr[i] == abc[abc.length - j]) {
                    answer += j;
                    break;
                }
            }
        }

        return answer - aStart;
    }
}
