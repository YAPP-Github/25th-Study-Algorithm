import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        LinkedHashSet<String> sudentSet = new LinkedHashSet<>();
      
        // 수강신청 대기목록에 학생들 받기
        for(int i = 0; i < L; i++){
            String student = br.readLine();
          
            // 이미 대기열에 들어있다면 맨 뒤로 밀려나기
            if(studentSet.contains(student)){
                studentSet.remove(student);
            }
            studentSet.add(student);
        }
      
        // 수강신청 성공한 학생 출력
        int count = 0;
        for(String student : studentSet){
            if(count == K){
                break;
            }
            count++;
            System.out.println(student);
        }
    }
}
