# [프로그래머스 - 탐욕법 - 조이스틱 ] 

- **문제 링크:** [조이스틱](https://school.programmers.co.kr/learn/courses/30/lessons/42860)
- **상태:** 비효율 
- **복습 필요:** Y

---

## 1. 문제 설명

-  ▲ - 다음 알파벳
▼ - 이전 알파벳 (A에서 아래쪽으로 이동하면 Z로)
◀ - 커서를 왼쪽으로 이동 (첫 번째 위치에서 왼쪽으로 이동하면 마지막 문자에 커서)
▶ - 커서를 오른쪽으로 이동 (마지막 위치에서 오른쪽으로 이동하면 첫 번째 문자에 커서)
---

## 2. 내 접근 방식
- 해당 알파벳이 될라면 앞에서 -1/뒤에서 +1 접근 한 값이다 둘을 한번애 for문을 돌려서 먼저 값과 일치 한 곳의 숫자를 더하기 
2번째 부터 연속되는 A 갯수를 세서 전체에서 빼면 됨 연속 안되면 필요 없음 


``` 
import java.util.*;
class Solution {
    public int solution(String name) {
        char[] arr = name.toCharArray();
        char[]  abc = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        int answer = name.length()-1;
        int aStart = 0;
        boolean a = true;
        
        for(int i =0; i < arr.length;i++){
            if(i >0 && arr[i] == 'A' && a == true){
                aStart +=1;
            }else if(i > 0 && arr[i] != 'A'){
                a = false;
            }
            if(arr[i] =='A'){
                continue;
            }
            
            for(int j = 1; j < abc.length;j++){
                if(arr[i] == abc[j]){
                    answer += j;
                    break;
                }
                if(arr[i] == abc[abc.length-j]){
                    answer += j;
                    break;
                }
            }
        }
        
        return answer-aStart;
    }
}
```


---

## 3. 틀린 이유
- 파이썬에서 -1 이면 리스트 마지막인데 자바에서 아님 음수 인덱스를 지원하지 않음 
- ">" 이 조건으로 했을 때 여기에 포함 되지 않은 값도 작은 값도 if에 걸린다는 것을 까먹
- 좌우 이동하는게 틀림 BBBAAAAAAB 이렇게 A가 두번째가 아니더라도 연속이 있을 수 있음 
방향을 바꾸는 것이 항상 이득은 아니고,
A 구간의 위치와 길이에 따라 달라진다.
A가 얼마나 긴가?
A 앞에 수정할 문자가 몇 개 있는가?
A 뒤에 수정할 문자가 몇 개 있는가? 를 봐야 함 
=> 모든 A 구간을 후보로 놓고 이동 횟수를 계산한 뒤 최솟값을 선택
---

## 4. 올바른 접근 풀이
- 
문자열 전체를 탐색한다.
연속된 A 구간을 찾는다.
해당 A 구간을 통과하는 경우와 건너뛰는 경우를 비교한다.
모든 위치에서 방향을 바꾼다고 가정하고 이동 횟수를 계산한다.
그중 가장 작은 이동 횟수를 선택한다.
int next = i + 1;

while(next < len && name.charAt(next) == 'A'){
    next++;
}
```
class Solution {
    public int solution(String name) {
        int answer = 0;
        int len = name.length();

        // 좌우 이동 기본값
        int move = len - 1;

        for (int i = 0; i < len; i++) {

            // 위아래 이동
            answer += Math.min(
                    name.charAt(i) - 'A',
                    'Z' - name.charAt(i) + 1
            );

            // 연속된 A 찾기
            int next = i + 1;

            while (next < len && name.charAt(next) == 'A') {
                next++;
            }

            // 오른쪽 갔다가 되돌아오기
            move = Math.min(
                    move,
                    i * 2 + len - next
            );

            // 왼쪽 먼저 갔다가 나중에 오기
            move = Math.min(
                    move,
                    (len - next) * 2 + i
            );
        }

        return answer + move;
    }
}
```

---

## 5. 배운점
문자열 받은걸 리스트에 하나 씩 넣어야 함  
Arrays.toString()은 배열 → 문자열
char[] arr = str.toCharArray();
print는 한가지 인자만 출력 가능 