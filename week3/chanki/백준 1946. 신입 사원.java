import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int numberOfTestCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < numberOfTestCase; i++) {
            int numberOfApplicant = Integer.parseInt(br.readLine());
            int[] applicantGradeInfos = new int[numberOfApplicant];

            // 초기화
            // 일차원 배열로 선언한 것은, 서류 점수를 index로 하고 면접 점수를 value로 하기 위함
            // 이러면 별도의 정렬 수행 필요 없이 바로 비교할 수 있음
            for (int applicant = 0; applicant < numberOfApplicant; applicant++) {
                String[] applicantGrades = br.readLine().split(" ");
                applicantGradeInfos[Integer.parseInt(applicantGrades[0]) - 1] = Integer.parseInt(applicantGrades[1]);
            }

            // 최소값 갱신해나가며 탐색
            int minimumValue = Integer.MAX_VALUE;
            int passer = 0;
            // 서류 등수 순으로 오름차순 정렬을 하고, 순회해나가면서
            // 이전에 나왔던 면접 점수 중 최고 등수(숫자가 작은 것)보다 높은 등수(더 작은 수)가 나오면 통과다
            // 동차는 없다고 했으므로, 같은 등수는 나올 수 없으니 등호는 배제
            for (int applicant = 0; applicant < numberOfApplicant; applicant++) {
                if (applicantGradeInfos[applicant] < minimumValue) {
                    passer++;
                    minimumValue = applicantGradeInfos[applicant];
                }
            }

            sb.append(passer).append("\n");
        }

        System.out.println(sb);
    }
}
