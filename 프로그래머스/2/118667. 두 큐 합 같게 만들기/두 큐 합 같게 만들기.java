import java.util.*;
import java.io.*;


class Solution {
    static Queue<long[]> q1 = new ArrayDeque(), q2 = new ArrayDeque();
    static int count = 0;
    public int solution(int[] queue1, int[] queue2) {
        long queue1Sum = Arrays.stream(queue1).sum();
        long queue2Sum = Arrays.stream(queue2).sum();
        if((queue1Sum + queue2Sum) % 2 == 1) return -1;
        
        for(int i = 0; i < queue1.length; i++){
            q1.add(new long[]{queue1[i], 1, i});
            q2.add(new long[]{queue2[i], 2, i});
        }
        while((queue1Sum != queue2Sum)){
            if(count > queue1.length * 3) return -1;
            
            if(queue1Sum > queue2Sum){
                long[] current = q1.poll();
                q2.add(current);
                queue1Sum -= current[0];
                queue2Sum += current[0];
            } else{
                long[] current = q2.poll();
                q1.add(current);
                queue2Sum -= current[0];
                queue1Sum += current[0];
            }
            count++;
        }
        return count;
    }
}

/*
mid = 80
A = 10 10 10 80 10 | 120
B = 5 5 5 5 10 10 | 40

A = 10 10 80 10 | 110
B = 5 5 5 5 10 10 10(1) | 50

A = 10 80 10 | 100
B = 5 5 5 5 10 10 10(1) 10(1) | 60

A = 80 10 | 90
B = 5 5 5 5 10 10 10(1) 10(1) 10(1) | 70

A = 10 | 10
B = 5 5 5 5 10 10 10(1) 10(1) 10(1) 80(1)| 150

A = 10 5(2) | 15
B = 5 5 5 10 10 10(1) 10(1) 10(1) 80(1)| 145

A = 10 5(2) 5(2) | 20
B = 5 5 10 10 10(1) 10(1) 10(1) 80(1)| 140

A = 10 5(2) 5(2) 5(2)| 25
B = 5 10 10 10(1) 10(1) 10(1) 80(1)| 135

A = 10 5(2) 5(2) 5(2) 5(2) | 30
B = 10 10 10(1) 10(1) 10(1) 80(1)| 130

A = 10 5(2) 5(2) 5(2) 5(2) 10(2) | 40
B = 10 10(1) 10(1) 10(1) 80(1)| 120

A = 10 5(2) 5(2) 5(2) 5(2) 10(2) 10(2) | 50
B = 10(1) 10(1) 10(1) 80(1)| 110

A = 10 5(2) 5(2) 5(2) 5(2) 10(2) 10(2) 10(1)| 60
B = 10(1) 10(1) 80(1)| 100

A = 10 5(2) 5(2) 5(2) 5(2) 10(2) 10(2) 10(1) 10(1) | 70
B = 10(1) 80(1)| 90

A = 10 5(2) 5(2) 5(2) 5(2) 10(2) 10(2) 10(1) 10(1) 10(1) | 80
B = 80(1)| 80


*/