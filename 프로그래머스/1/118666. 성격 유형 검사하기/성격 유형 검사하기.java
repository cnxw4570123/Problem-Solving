import java.util.*;
import java.io.*;
import java.util.Map.Entry;

class Solution {  
    static final Map<String, int[]> scores = new HashMap<>();
    static final int MID = 4;
    public String solution(String[] survey, int[] choices) {
        init();
        for(int i = 0; i < survey.length; i++){
            int score = Math.abs(MID - choices[i]);
            if(choices[i] == MID) continue;
            if(choices[i] < MID){
                if(scores.containsKey(survey[i])){
                    scores.get(survey[i])[0] += score;
                } else{
                    scores.get(new StringBuilder(survey[i]).reverse().toString())[1] += score;
                }
            } else{
                if(scores.containsKey(survey[i])){
                    scores.get(survey[i])[1] += score;
                } else{
                    scores.get(new StringBuilder(survey[i]).reverse().toString())[0] += score;
                }
            }
        }
        StringBuilder ans = new StringBuilder();
        for(Entry<String, int[]> entry : scores.entrySet()){
            System.out.println(entry.getKey() + " " + Arrays.toString(entry.getValue()));
            String Key = entry.getKey();
            int[] score = entry.getValue();
            
            if(score[0] >= score[1]) ans.append(Key.charAt(0));
            else ans.append(Key.charAt(1));
        }
        return ans.toString();
    }
    static void init(){
        scores.put("RT", new int[2]);
        scores.put("CF", new int[2]);
        scores.put("JM", new int[2]);
        scores.put("AN", new int[2]);
        
    }
  
}