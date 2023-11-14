import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        init();
        for (int i = 0; i < 20; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            double credits = Double.parseDouble(st.nextToken());
            String score = st.nextToken();
            if(score.equalsIgnoreCase("p")) continue;
            double score1 = scoreMap.get(score);
            subjects.add(new Subject(name, credits, score1));
        }
        double totalCredit = subjects.stream().mapToDouble(s -> s.credits).sum();

        double totalScore = subjects.stream().mapToDouble(Subject::creditMultiplyScore).sum();

        bw.write("" + String.format("%.6f",totalScore / totalCredit));
        bw.flush();
        bw.close();
        br.close();
    }

    static Map<String, Double> scoreMap = new HashMap<>();
    static List<Subject> subjects = new ArrayList<>();
    static void init(){
        scoreMap.put("A+", 4.5);
        scoreMap.put("A0", 4.0);
        scoreMap.put("B+", 3.5);
        scoreMap.put("B0", 3.0);
        scoreMap.put("C+", 2.5);
        scoreMap.put("C0", 2.0);
        scoreMap.put("D+", 1.5);
        scoreMap.put("D0", 1.0);
        scoreMap.put("F", 0.0);
    }
}

class Subject{

    String name;
    double credits;
    double score;

    public Subject(String name, double credits, double score) {
        this.name = name;
        this.credits = credits;
        this.score = score;
    }

    public double creditMultiplyScore(){
        return credits * score;
    }

}