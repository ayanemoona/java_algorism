# [프로그래머스 - 완전 탐색 - 모의고사 ] 

- **문제 링크:** [모의고사](https://school.programmers.co.kr/learn/courses/30/lessons/42840?language=java)
- **상태:** 비효율 
- **복습 필요:** Y

---

## 1. 문제 설명

- 수포자 3인방 1번(1,2,3,4,5 반복) 2번 (21232425 반복) 3번 (3311224455 반복) 일 때
수학 문제 배열이 주어졌을 때 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return
동점자 가 있고 오름차순으로 정렬 해야 함 
---

## 2. 내 접근 방식
-1번 수포자는 5개씩 반복되어 5나누기 나머지
-2번 수포자는 21/23/24/25 가 반복 8 나누기 나머지
- 3번 33/11/22/44/55 로 10나누기 나머지
각각의 나머지가 매개변수인 answers에 일치 하는 갯수를 세며 high 스코어를 함께 계산 
점수 계산을 끝낸 후 for문을 돌며 high 스코어와 일치하는 스포자 를 arrayList에 추가 
동점자가 있다면 리스트로 나와야 함으로 arrayList 선언을 해야 할듯(리스트가 값이 고정 X) 근데 다시 int[] 배열로 받고 있어서 ArrayList 값으로 다시 배열 만듦 
```
import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {0,0,0};
        int[] one = {1,2,3,4,5};
        int[] two = {2,1,2,3,2,4,2,5};
        int[] three = {3,3,1,1,2,2,4,4,5,5};
        int high = 0;
        List<Integer> result 
            = new ArrayList<>();
        for(int i = 0; i < answers.length;i++){
            if(answers[i] == one[i%5]){
                answer[0]+=1;
                if (high< answer[0] )high= answer[0];
            }
            if(answers[i] == two[i%8]){
                answer[1]+=1;
                if (high< answer[1]) high= answer[1];
            }
            if(answers[i] == three[i%10]){
                answer[2]+=1;
                if (high< answer[2] )high= answer[2];
            }
        }
        for(int i =0;i<answer.length;i++){
            if(high == answer[i]){
                result.add(i+1);
            }
        }
        int[] arr = result.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        return arr;
    }
}
```


---

## 3. 틀린 이유
- 조금 복잡 했던 부분
그냥 배열로 하면 동점자가 아니면 지워야 하는데 빈배열로 넘어가지게 되어서 arrayList를 통해 잘라 내게 
- 3개 비교 하는 방법이 max(값, max(값, 값)) 임
---

## 4. 올바른 접근 풀이
 
```
class Solution {
    public int[] solution(int[] answers) {

        int[] score = new int[3];

        int[] one = {1,2,3,4,5};
        int[] two = {2,1,2,3,2,4,2,5};
        int[] three = {3,3,1,1,2,2,4,4,5,5};

        for(int i = 0; i < answers.length; i++) {

            if(answers[i] == one[i % one.length])
                score[0]++;

            if(answers[i] == two[i % two.length])
                score[1]++;

            if(answers[i] == three[i % three.length])
                score[2]++;
        }

        int high = Math.max(score[0],
                   Math.max(score[1], score[2]));

        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < score.length; i++){
            if(score[i] == high){
                result.add(i + 1);
            }
        }

        return result.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
```

---

## 5. 배운점

## 1. 반복되는 패턴은 % 연산자로 처리할 수 있다

수포자들은 일정한 패턴으로 답을 찍는다.

```java
one[i % one.length]
two[i % two.length]
three[i % three.length]
```

처음에는 배열 범위를 벗어날까 걱정했지만 `%` 연산을 사용하면 배열 길이를 넘어가더라도 처음부터 다시 접근할 수 있다.

예를 들어

```java
int[] one = {1,2,3,4,5};
```

일 때

```java
one[5 % 5] == one[0]
one[6 % 5] == one[1]
```

이 된다.

따라서 반복 패턴 문제에서는 `% 배열길이`를 적극 활용할 수 있다.

---

## 2. 배열의 길이는 가변적이지 않다

처음에는 결과를 배열로 만들려고 했다.

하지만 정답이

```java
[1]
```

일 수도 있고

```java
[1,2]
```

일 수도 있고

```java
[1,2,3]
```

일 수도 있다.

배열은 생성 시 크기가 고정되므로 중간에 삭제하거나 크기를 줄일 수 없다.

```java
int[] arr = new int[3];
```

으로 만들면

```java
[1,0,0]
```

처럼 필요 없는 값이 남게 된다.

따라서 결과 개수를 미리 모를 경우 배열보다 ArrayList를 사용하는 것이 편리하다.

---

## 3. ArrayList → 배열 변환 방법

ArrayList에 결과를 저장한 후 최종적으로 배열로 변환했다.

```java
return result.stream()
        .mapToInt(Integer::intValue)
        .toArray();
```

처음에는 배열만 사용하려고 했지만 결과 개수가 가변적이므로

```java
ArrayList
↓
필요한 값만 저장
↓
배열 변환
```

방식이 훨씬 자연스럽다는 것을 배웠다.

---

## 4. 배열 출력과 ArrayList 출력의 차이

배열은

```java
System.out.println(arr);
```

를 하면 주소값이 출력된다.

따라서 디버깅할 때는

```java
Arrays.toString(arr);
```

를 사용해야 한다.

2차원 배열은

```java
Arrays.deepToString(arr);
```

를 사용한다.

---

## 5. 최대값 갱신 방법

처음에는

```java
if(high < score){
    high = score;
}
```

형태를 사용했다.

이 방법도 맞지만

```java
high = Math.max(high, score);
```

를 사용하면 더 간결하게 작성할 수 있다는 것을 배웠다.

---

## 6. 배열, 문자열, 리스트 길이 구하는 방법은 다르다

배열

```java
arr.length
```

문자열

```java
str.length()
```

리스트

```java
list.size()
```

코딩테스트에서 가장 자주 헷갈리는 부분 중 하나이다.

---

## 7. 이번 문제 핵심

1. 반복 패턴은 `% 배열길이` 사용
2. 각 사람의 점수를 배열에 저장
3. 최고 점수 계산
4. 최고 점수와 같은 사람만 ArrayList에 저장
5. ArrayList를 int[]로 변환하여 반환

---

## 한 줄 정리

"결과 개수를 미리 알 수 없는 경우 배열보다 ArrayList를 사용하고, 최종적으로 배열로 변환하는 패턴을 익혔다."
