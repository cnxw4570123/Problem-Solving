import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static final String IDX = "GH";
    static int[] counts = {0, 0};
    static Person[] persons;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        persons = new Person[n];
        
        for (int i = 0; i < n; i++) {
            int position = sc.nextInt();
            char ch = sc.next().charAt(0);
            persons[i] = new Person(position, ch);
        }
        // Please write your code here.
        Arrays.sort(persons, (p1, p2) -> Integer.compare(p1.x, p2.x));

        int dist = 0;
        for (int i = 0; i < n; i++) {
            Arrays.fill(counts, 0);
            for (int j = i; j < n; j++) {
                counts[IDX.indexOf(persons[j].c)]++;
                if(
                    (counts[0] > 0 && counts[1] == 0) ||
                    (counts[1] > 0 && counts[0] == 0) ||
                    (counts[0] == counts[1])
                ) {
                    dist = Math.max(dist, persons[j].x - persons[i].x);
                }
            }
        }
        System.out.println(dist);
    }

    static class Person {
        int x;
        char c;

        Person(int x, char c) {
            this.x = x;
            this.c = c;
        }
    }

    // [a, a + x]
    // 겹치는 좌표 X.
}