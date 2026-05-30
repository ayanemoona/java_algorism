# [프로그래머스 - 해시 - 완주하지 못한 선수 ] 

- **문제 링크:** [완주하지 못한 선수](https://school.programmers.co.kr/learn/courses/30/lessons/42576?language=java)
- **상태:** 비효율 
- **복습 필요:** Y

---

## 1. 문제 설명

단 한명의 선수를 제외하고 모든 선수가 마라톤을 완주함
이때 문제에선 마라톤 참여 선수 명단 배열과 완주 선수 명단 배열을 주어질 때 
완주하지 못한 서수의 이름을 return 해라 
동명이인 있을 수 있음 

---

## 2. 내 접근 방식
이름 문자열을 찾는 거기 때문에 (순서대로 처음 부터 볼 필요 없음 그 해당 선수가 있는지 궁금 ) hash 사용
동명이인이 있을 수 있다고 하니 키를 선수 이름 값을 숫자로 하여 동명이인이 나올 때 +1 하기 
```
public String solution(String[] participant, String[] completion) {
        Map<String,Integer> hash = new HashMap<>();
        String answer = "";
        for (String a : participant){
            if(hash.containsKey(a)){
             hash.put(a,hash.get(a)+1);   
            }else{
            hash.put(a,1);
            }
        }
        for (String a : completion){
            if(hash.containsKey(a)){
                hash.put(a, hash.get(a)-1);
            }
        }
        for (String key : hash.keySet()){
            if(hash.get(key) == 1){
                    answer = key;
            }
        }
        
        return answer;
    }

```


---

## 3. 틀린 이유
- hash 사용에 능숙하지 않음 

---

## 4. 올바른 접근 풀이
-  
```
import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> hm = new HashMap<>();
        for (String player : participant) hm.put(player, hm.getOrDefault(player, 0) + 1);
        for (String player : completion) hm.put(player, hm.get(player) - 1);

        for (String key : hm.keySet()) {
            if (hm.get(key) != 0){
                answer = key;
            }
        }
        return answer;
    }
}
```



---

## 5. 배운점
- hash 선언 
impot java.util.*;
Map<String, Integer> 이름 = new HashMap<>();
Integer 값을 +/- 하고 싶으면 모두 put
(빼기관련 함수 없음)

getOrDefault(key, 기본값) - 키가 있으면 그 값 반환, 없으면 기본값 반환
containsKey() - key에 해당 값이 있는지 확인
get(key) - 값꺼내오기 


 