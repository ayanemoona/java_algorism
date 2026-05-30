# [프로그래머스 - 스택/큐 - 같은 숫자는 싫어 ] 

- **문제 링크:** [같은 숫자는 싫어](https://school.programmers.co.kr/learn/courses/30/lessons/12906?language=java)
- **상태:** 비효율 
- **복습 필요:** Y

---

## 1. 문제 설명

배열 arr에서 연속적으로 나타나는 숫자는 제거하고 남은 수들을 return 하는 solution 함수를 완성
---

## 2. 내 접근 방식
hash로 같은거 2개 받았을 때 그거 세서 넣으면 되지 않을까 키를 -> 스택은 넣은 순서 보장 못함 
큐로 하나 씩 뽑아서 씀
큐에서 하나 뽑았을 때 정답지로 쓸 리스트에 있는지 확인
없으면 추가 /있는데 리스트의 마지막이랑 일치 안하면 추가 /다른 케이스는 추가 안함

```
import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Queue<Integer> queue = new LinkedList<>();
        for(int i : arr){
            queue.offer(i);
        }
        List<Integer> list = new ArrayList<>();
    
        while (!queue.isEmpty()){
            int value = queue.poll();
            if(list.contains(value)){
                if(list.get(list.size() - 1)!= value){
                    list.add(value);
                }
                continue;
            }else{
                list.add(value);
            }
        
        }
        int[] result = new int[list.size()];
        for (int i=0; i< list.size();i++){
            result[i]=list.get(i);
        }
        return result;
    }
}

```

```
import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        List<Integer> list1 = new ArrayList<>();
       list1.add(arr[0]);
        for(int i =1;i<arr.length; i++){
            if(list1.get(list1.size()-1)== arr[i]){
                continue;
            }else{
                list1.add(arr[i]);
            }
        }
        
        int[] result = new int[list1.size()];
        for (int i=0; i< list1.size();i++){
            result[i]=list1.get(i);
        }
        return result;
    }
}
```


---

## 3. 틀린 이유
- 코드는 작은 입력에선 동작하지만 큰 입력(최대 1,000,000)에선 성능 문제가 생김
    루프 안에서 list.contains(value)를 호출하면 각 호출이 O(n)이라 전체가 최악 O(n^2)이 되어 시간초과
    굳이 Queue로 한 칸씩 빼는 것도 불필요한 오버헤드
- 1차로 문법 모름 이슈
- 큐 그딴거 필요 없음  

---

## 4. 올바른 접근 풀이
-  
```
import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        int preNum = 10;
        for(int num : arr) {
            if(preNum != num)
                tempList.add(num);
            preNum = num;
        }       
        int[] answer = new int[tempList.size()];
        for(int i=0; i<answer.length; i++) {
            answer[i] = tempList.get(i).intValue();
        }
        return answer;
    }
}
```



---

## 5. 배운점
- 그냥 배열은 그냥 add 못함 -> 크기가 정해져있어서 인덱스 지정 필수
ArrayList 는 크기가 정해져있지 않아 무한정 추가 가능 
배열은 찾는거 반복문 밖에 없음
ArrayList는 contains로 가능 

Stack
pop 빼내기 근데 인자 안씀 for문으로 빼내기 못함 -> 빼낼 때 크기 변경 됨
while로 해결 가능 

Queue<Integer> 이름 = new LinkedList<>();

 