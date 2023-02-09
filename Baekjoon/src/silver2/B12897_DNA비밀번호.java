package silver2;

import java.io.*;
import java.util.*;

// 문자열에서 부분 문자열만큼 떼오기
// 그 안에 ACGT가 제한 이상만큼 있는지 검사
public class B12897_DNA비밀번호 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		String dna = br.readLine(); // DNA문자열

		st = new StringTokenizer(br.readLine());
		int[] nums = new int[4];
		for (int i = 0; i < 4; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		///////////// 입력 끝//////////////////////

		// 초기 문자열 검사 시작
		boolean isPossible = true;
		int res = 0;
		for (int i = 0; i < P; i++) {
			if (dna.charAt(i) == 'A')
				nums[0]--; //A가 있다면 A의 최소개수에서 줄임 
			else if (dna.charAt(i) == 'C')
				nums[1]--;
			else if (dna.charAt(i) == 'G')
				nums[2]--;
			else if (dna.charAt(i) == 'T')
				nums[3]--;
		}
		for (int i = 0; i < 4; i++) {
			if (nums[i] > 0) { //최소개수가 하나라도 양수라면 최소개수를 못맞추었다는 뜻
				isPossible = false;
				break;
			}
		}
		if (isPossible)
			res++;

		// 다음 문자열들 검사 시작
		for (int i = P; i < dna.length(); i++) {
			isPossible = true;
			//만약 부분문자열이 3개면, 만들어진 초기 문자열에 현재 문자를 더하고 i-p인덱스에 있는 문자는 빼야 새로운 문자열 나옴
			if (dna.charAt(i - P) == 'A') {
				nums[0]++; //맨 앞에 있던 문자가 빠지므로 A가 빠져나간 개수를 다시 올려야함 
			} else if (dna.charAt(i - P) == 'C') {
				nums[1]++;
			} else if (dna.charAt(i - P) == 'G') {
				nums[2]++;
			} else if (dna.charAt(i - P) == 'T') {
				nums[3]++;
			}

			if (dna.charAt(i) == 'A')
				nums[0]--;
			else if (dna.charAt(i) == 'C')
				nums[1]--;
			else if (dna.charAt(i) == 'G')
				nums[2]--;
			else if (dna.charAt(i) == 'T')
				nums[3]--;
			
			for (int j = 0; j < 4; j++) {
				if (nums[j] > 0) { //최소개수가 하나라도 양수라면 최소개수를 못맞추었다는 뜻
					isPossible = false;
					break;
				}
			}
			if (isPossible)
				res++;
		}
		System.out.println(res);
	}
}
