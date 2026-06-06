# [프로그래머스 - 완전 탐색 - 최소직사각형 ] 

- **문제 링크:** [최소 직사각형 ](https://school.programmers.co.kr/learn/courses/30/lessons/86491)
- **상태:** 비효율 
- **복습 필요:** Y

---

## 1. 문제 설명

- 2차원 배열의 명함의 가로 세로 길이가 주어질 때  모든 명함이 들어갈 수 있는최소 크기의 지갑의 크기를 구하는 것
이때 가로 세로의 구분이 없어진다. 예를 들어 가로70, 세로 50 인 명함이 있다면 이걸 세로로 세우면 가로50, 세로 70 인 명함으로 취급할 수 있다

---

## 2. 내 접근 방식
- 하나하나 확인해서 둘중 큰 걸 앞 번호에 배치하도록 변경 
그다음 앞에서 큰거랑 뒤에서 큰거를 곱해서 크기 return 
```
import java.util.*;
class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int max = 0;
        int min = 0;
        for(int[] a: sizes){
            if(a[0]<a[1]){
                int i = a[0];
                a[0] = a[1];
                a[1] = i;
            }
            if(a[0] > max){
                max = a[0];
            }
            if(a[1] > min){
                min = a[1];
            }
        }
        answer = max * min;
        return answer;
    }
}
```


---

## 3. 틀린 이유
- 맞았음 
---

## 4. 올바른 접근 풀이
 
```

class Solution {
    public int solution(int[][] sizes) {
        int length = 0, height = 0;
        for (int[] card : sizes) {
            length = Math.max(length, Math.max(card[0], card[1]));
            height = Math.max(height, Math.min(card[0], card[1]));
        }
        int answer = length * height;
        return answer;
    }
}
```

---

## 5. 배운점
System.out.println(Arrays.toString(a)); -> 1차 배열 출력
System.out.println(Arrays.deepToString(sizes)); -> 2차 배열 출력 