# [프로그래머스 - 탐욕법 - 체육복 ] 

- **문제 링크:** [체육복](https://school.programmers.co.kr/learn/courses/30/lessons/42862)
- **상태:** 비효율 
- **복습 필요:** Y

---

## 1. 문제 설명

-  여벌 체육복이 있는 학생이 이들에게 체육복을 빌려줄 수 있습니다. 학생들의 번호는 체격 순으로 매겨져 있어, 바로 앞번호의 학생이나 바로 뒷번호의 학생에게만 체육복을 빌려줄 수 있습니다.
체육복이 없으면 수업을 들을 수 없기 때문에 체육복을 적절히 빌려 최대한 많은 학생이 체육수업을 들어야 합니다.
전체 학생의 수 n, 체육복을 도난당한 학생들의 번호가 담긴 배열 lost, 여벌의 체육복을 가져온 학생들의 번호가 담긴 배열 reserve가 매개변수로 주어질 때, 체육수업을 들을 수 있는 학생의 최댓값을 return
예외) 도난 당하면서도 여벌 체육복을 가지고 있을 경우 빌려주지 않는다.
---

## 2. 내 접근 방식
- 체육복을 잊고 온  lost를 ArrayList로 변경하여 뒤에 reserve 배열에 있으면 -1 일 때 부터 삭제
그다음 +1이 있으면 삭제
그리고 체육복 입은 사람 = (전체 - lost 한 사람) + (lost 한사람 - 대체자도 없는 사람 )
``` 
import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        Arrays.sort(lost);
        Arrays.sort(reserve);
        List <Integer> reserve2 = new ArrayList<>();
        for(int a : reserve){
            reserve2.add(a);
        }
        List<Integer> needStudent = new ArrayList<>();
        for(int a : lost){
            if(reserve2.contains(a)){
                reserve2.remove(Integer.valueOf(a));
                continue;
            }
            needStudent.add(a);
        }
        
        for(int a :reserve2){
            if(needStudent.contains(a-1)){
                needStudent.remove(Integer.valueOf(a-1));
                continue;
            }
            if(needStudent.contains(a+1)){
                needStudent.remove(Integer.valueOf(a+1));
                continue;
            }
        }
        
        
        return n - needStudent.size();
    }
}
```


---

## 3. 틀린 이유
- 여벌도 있고 도난도 당한 학생
여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다. 이때 이 학생은 체육복을 하나만 도난당했다고 가정하며, 남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다. => lost과 여벌 체육복 을 가지고 있는 사람이 일치 하는건 먼저 삭제 ArrayList를 생성할 때 일치하는거 먼저 삭제 
- 런타임 에러
순회하던 리스트를 삭제함 
- 정렬이 필요 함 

---

## 4. 올바른 접근 풀이
- 배열로만 구하는 것 체육복이 있으면 0 체육복 없으면 -1 초과 체육복 +1
```
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] people = new int[n];
        int answer = n;

        for (int l : lost) 
            people[l-1]--;
        for (int r : reserve) 
            people[r-1]++;

        for (int i = 0; i < people.length; i++) {
            if(people[i] == -1) {
                if(i-1>=0 && people[i-1] == 1) {
                    people[i]++;
                    people[i-1]--;
                }else if(i+1< people.length && people[i+1] == 1) {
                    people[i]++;
                    people[i+1]--;
                }else 
                    answer--;
            }
        }
        return answer;
    }
}
```

---

## 5. 배운점
그리디는 정렬이 필수 

ArrayList 삭제 
remove -> 이건 인덱스 삭제 
list.remove(Integer.valueOf(target)) -> 이건 값 삭제 Integer 객체로 보내야 함 
=======
출력
배열일 때 -> 객체 주소 나옴 Arrays.toString(arr)
ArrayList일 때 -> 걍 출력 가능 

=====
배열 -> ArrayList로 
List<String> list = new ArrayList<>(Arrays.asList(arr));

String[] → Arrays.asList() 가능
Integer[] → Arrays.asList() 가능

int[] → Arrays.asList() 안 됨

==========
