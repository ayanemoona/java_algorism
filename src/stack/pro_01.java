package stack;

import java.util.*;
class pro_01 {
    public static void main(String[] args) {
        pro_01 T = new pro_01();
        int[] arr = {1,1,3,3,0,1,1};
    
        System.out.println(T.solution(arr));
    }



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

