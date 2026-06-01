# [프로그래머스 - 힙 - 더 맵게 ] 

- **문제 링크:** [더 맵게](https://school.programmers.co.kr/learn/courses/30/lessons/42626)
- **상태:** 비효율 
- **복습 필요:** Y

---

## 1. 문제 설명

- 스코빌 지수 배열을 받아서 모든 음식을 스코빌 지수를 K이상으로 만들고 싶음
K이상으로 만들기 위해 스코빌 지수가 가장 낮은 두개의 음식을 아래와 같은 수식으로 새로운 음식 만듦
섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
Leo가 가진 음식의 스코빌 지수를 담은 배열 scoville과 원하는 스코빌 지수 K가 주어질 때, 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 return 
모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return
---

## 2. 내 접근 방식
- 받은 배열을 크기 순으로 정렬 후 빼내기 앞에서 부터 
앞에서 빼낼 수 있어야 하고 추가할 수 있어야 함으로 Deque 사용

```
import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        Arrays.sort(scoville);
        Deque<Integer> queue = new ArrayDeque<>();
        for(int s : scoville){
            queue.offerLast(s);
        }
        int answer = 0;
        while(queue.size() != 1){
            if(queue.peekFirst()>= K){
                break;
            }
            int value1 = queue.pollFirst();
            int value2 = queue.pollFirst();
            int result = value1 + (value2*2);
            queue.offerFirst(result);
            answer+=1;
            
        }
        if(answer == queue.size()-1){
            answer = -1;
        }
        return answer;
    }
}
```


---

## 3. 틀린 이유
- Deque는 정렬 상태가 유지가 안되서 매번 정렬이 불가능 
=> PriorityQueue 사용 해야 함 (내부적으로 오름차순으로 재배치 함 )
- queue.size()로 확인을 했는데 큐에서 빼서 삭제 하기 때문에 이거 사용 하면 의미 없음 

---

## 4. 올바른 접근 풀이
 
```
import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> q = new PriorityQueue<>();

        for(int i = 0; i < scoville.length; i++)
            q.add(scoville[i]);

        int count = 0;
        while(q.size() > 1 && q.peek() < K){
            int weakHot = q.poll();
            int secondWeakHot = q.poll();

            int mixHot = weakHot + (secondWeakHot * 2);
            q.add(mixHot);
            count++;
        }

        if(q.size() <= 1 && q.peek() < K)
            count = -1;

        return count;
    }
}
```



---

## 5. 배운점
- 배열 정렬
Arrays.sort(이름) 오름차순 
Deque<Integer> dq = new ArrayDeque<>();

dq.offerFirst(x); // 앞 추가
dq.offerLast(x);  // 뒤 추가

dq.pollFirst();   // 앞 삭제
dq.pollLast();    // 뒤 삭제

dq.peekFirst();   // 앞 조회
dq.peekLast();    // 뒤 조회

앞과 뒤에서 추가 삭제 가능한 자료구조 
가장 작은 값
최솟값
우선순위
반복적으로 가장 작은 것 선택
 => PriorityQueue : 힙(완전이진 트리)구조
 PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(x); // 넣기

pq.poll();   // 가장 작은 값 꺼내기

pq.peek();   // 가장 작은 값 보기

pq.size();

pq.isEmpty();