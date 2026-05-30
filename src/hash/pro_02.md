# [프로그래머스 - 해시 - 폰켓몬 ] 

- **문제 링크:** [폰켓몬](https://school.programmers.co.kr/learn/courses/30/lessons/1845)
- **상태:** 비효율 
- **복습 필요:** Y

---

## 1. 문제 설명

총 N마리의 폰켓몬 중 N/2 마리만 가져갈 수 있음 
같은 폰켓몬은 같은 번호 부여
폰켓몬 종류에 따라 번호를 붙인 명단 배열을 줘서 내가 가져갈 수 있는 많은 폰켓몬 종류에 대해서 출력

---

## 2. 내 접근 방식
중복을 줄인 Set 일 때 전체 폰켓몬의 종류가 보임 
내가 가져갈 수 있는 종류가 많아도 최대 N/2 마리 거나 더 적은 종류를 가져갈 수 있거나 둘 중 하나 
N/2 마리와 Set으로 중복을 줄인 폰켓몬의 종류 를 출력 
```
import java.util.*;
class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int pokemon = nums.length;
        int pick = pokemon/2;
        Set<Integer> set = new HashSet<>();
        for(int i : nums){set.add(i);}
        if(pick>set.size()){
            answer = set.size();
        }else{
            answer = pick;
        }
        
        return answer;
    }
}
```


---

## 3. 틀린 이유
- set 사용에 능숙하지 않아 사용법 물어봄
length() / length 사용 헷갈림

---

## 4. 올바른 접근 풀이
-  
```
import java.util.HashSet;

class Solution {
    public int solution(int[] nums) {

            HashSet<Integer> hs = new HashSet<>();

            for(int i =0; i<nums.length;i++) {
                hs.add(nums[i]);
            }

            if(hs.size()>nums.length/2)
                return nums.length/2;

            return hs.size();
    }
}
```



---

## 5. 배운점
- Set 선언 
impot java.util.*;
 Set<Integer> set = new HashSet<>();

값넣기 - add()
크기 - size() length()사용 X

배열(Array) 일 때 nums.length
문자열(String) 일 때 str.length()

 