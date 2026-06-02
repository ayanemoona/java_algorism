# [프로그래머스 - 정렬 - K번째 수 ] 

- **문제 링크:** [K번째 수 ](https://school.programmers.co.kr/learn/courses/30/lessons/42748?language=java)
- **상태:** 비효율 
- **복습 필요:** Y

---

## 1. 문제 설명

- 배열 array가 주어질때 commands 2차원 배열이 매개변수(i,j,k)로 주어져
i부터 j 숫자 까지 자르고 정렬후 k번째 있는 수를 구해 나온 결과를 배열에 담아 return 
---

## 2. 내 접근 방식
- 정답 배열은 내가 commands으로 만든 값의 갯수랑 일치해서 배열 사용
배열을 사용 함으로 sort 함수랑 인덱스를 자를 수 있는 copyOfRange 함수 사용 

```
import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int i =0 ; i < commands.length; i++){
            int first = commands[i][0];
            int last = commands[i][1];
            int result = commands[i][2];
            int[] newArray = Arrays.copyOfRange(array,first - 1,last);
            Arrays.sort(newArray);
            answer[i] = newArray[result-1];
            
        }
        
        return answer;
    }
}
```


---

## 3. 틀린 이유
- 인덱스 범위 잘못함 => 다음은 프린트를 찍어봐서 어디 까지 했는지 확인 하자 바로 답지 보지 말고 

---

## 4. 올바른 접근 풀이
 
```
import java.util.Arrays;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for(int i=0; i<commands.length; i++){
            int[] temp = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]);
            Arrays.sort(temp);
            answer[i] = temp[commands[i][2]-1];
        }

        return answer;
    }
}
```

- 퀵 정렬로도 풀 수 있음
# 퀵 정렬 (Quick Sort)

## 개념

퀵 정렬은 기준값(Pivot)을 하나 선택한 뒤,

* Pivot보다 작은 값은 왼쪽
* Pivot보다 큰 값은 오른쪽

으로 나누고, 이를 재귀적으로 반복하여 정렬하는 알고리즘이다.

---

## 동작 과정

배열

[5, 2, 6, 3, 1]

Pivot = 6

↓

[5, 2, 3, 1] | 6 | []

↓

왼쪽과 오른쪽 배열을 다시 같은 방식으로 분할

↓

최종 정렬

[1, 2, 3, 5, 6]

---

## 특징

* 분할 정복(Divide and Conquer) 방식 사용
* 재귀 호출 사용
* 평균 시간복잡도 : O(n log n)
* 최악 시간복잡도 : O(n²)
* 실제 라이브러리 정렬 구현에도 활용되는 대표적인 정렬 알고리즘

---

## 코드 핵심

Pivot 선택

int pivot = arr[(left + right) / 2];

작은 값 찾기

while(arr[pl] < pivot) pl++;

큰 값 찾기

while(arr[pr] > pivot) pr--;

교환

swap(arr[pl], arr[pr]);

왼쪽, 오른쪽 부분 배열 재귀 정렬

sort(arr, left, pr);
sort(arr, pl, right);

---

## 코테 포인트

직접 구현하는 경우는 드물다.

보통은

Arrays.sort(arr);

를 사용한다.

퀵 정렬 문제는 "원리 이해"가 목적이며, 실전 코테에서는 라이브러리 정렬을 사용하는 경우가 대부분이다.

---

```
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int n = 0;
        int[] ret = new int[commands.length];

        while(n < commands.length){
            int m = commands[n][1] - commands[n][0] + 1;

            if(m == 1){
                ret[n] = array[commands[n++][0]-1];
                continue;
            }

            int[] a = new int[m];
            int j = 0;
            for(int i = commands[n][0]-1; i < commands[n][1]; i++)
                a[j++] = array[i];

            sort(a, 0, m-1);

            ret[n] = a[commands[n++][2]-1];
        }

        return ret;
    }

    void sort(int[] a, int left, int right){
        int pl = left;
        int pr = right;
        int x = a[(pl+pr)/2];

        do{
            while(a[pl] < x) pl++;
            while(a[pr] > x) pr--;
            if(pl <= pr){
                int temp = a[pl];
                a[pl] = a[pr];
                a[pr] = temp;
                pl++;
                pr--;
            }
        }while(pl <= pr);

        if(left < pr) sort(a, left, pr);
        if(right > pl) sort(a, pl, right);
    }
}
```



---

## 5. 배운점
String       -> substring()
int[] 배열    -> Arrays.copyOfRange() => 시작 포함 끝 미포함
ArrayList    -> subList()

2차배열일 때 행의 크기를 알고 싶으면 array.length/ 열의 크기는 array[0].length