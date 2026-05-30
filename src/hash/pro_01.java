import java.util.*;
class pro_01 {
    public static void main(String[] args) {
        pro_01 T = new pro_01();
        String[] participant = {"mislav", "stanko", "mislav", "ana"};
        String[] completion = {"stanko", "ana", "mislav"};
        System.out.println(T.solution(participant, completion));
    }
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
}