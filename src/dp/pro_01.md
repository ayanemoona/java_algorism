# [프로그래머스 - 동적계획법 - N으로 표현 ] 

- **문제 링크:** [N으로 표현](https://school.programmers.co.kr/learn/courses/30/lessons/42895)
- **상태:** 비효율 
- **복습 필요:** Y

---

## 1. 문제 설명

-   N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 N 사용횟수의 최솟값을 return 
N은 1 이상 9 이하입니다.
수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
최솟값이 8보다 크면 -1을 return
---

## 2. 내 접근 방식
- 재귀로 하면 될거 같은데 감이 안잡혀.. 모르겠음 
``` 

```


---

## 3. 틀린 이유
- 아얘 접근 방법을 모름 

---

## 4. 올바른 접근 풀이
- 
```
import java.util.*;

class Solution {
    public int solution(int N, int number) {        
        if(N == number) {
            return 1;
        }
        
        ArrayList<HashSet<Integer>> hs = new ArrayList<>();
        
        for(int i = 0; i < 10; i++) {
            hs.add(new HashSet<Integer>());
        }
        
        String concatenate = "";
        for(int i = 1; i <= 8; i++) {
            concatenate += Integer.toString(N);
            hs.get(i).add(Integer.parseInt(concatenate));
        }
        
        int answer = -1;
        for(int i = 2; i <= 8; i++) {
            for(int j = 1; j <= i - 1; j++) {
                Iterator<Integer> iter1 = hs.get(j).iterator();
                
                while(iter1.hasNext()) {
                    Integer iter1Val = iter1.next();
                    
                    Iterator<Integer> iter2 = hs.get(i - j).iterator();
                    while(iter2.hasNext()) {
                        Integer iter2Val = iter2.next();
                        hs.get(i).add(iter1Val + iter2Val);
                        hs.get(i).add(iter1Val - iter2Val);
                        hs.get(i).add(iter1Val * iter2Val);
                        if(iter2Val != 0) {
                            hs.get(i).add(iter1Val / iter2Val);
                        }
                    }
                }
            }
            
            boolean flag = false;
            Iterator<Integer> it = hs.get(i).iterator();
            while(it.hasNext()) {
                if(it.next() == number) {
                    answer = i;
                    flag = true;
                    break;
                }
            }
            
            if(flag) {
                break;
            }
        }
        
        return answer;
    }
}
profile

```

---

## 5. 배운점
dp : 한번 계산 한거 다시 계산 안함
계산 결과 저장 하고 재사용