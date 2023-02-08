package silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 입력받음 => 맨처음수는 k 그리고 나머지는 원소
 * 0이면 입력종료
 * */
public class B6603_로또 {
	static int k;
	static int[] nums;   //k개의 수
	static int[] result = new int[6]; //완성된 조합
	static StringBuilder sb = new StringBuilder(); //출력용
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine()); //한 줄 입력
			k = Integer.parseInt(st.nextToken()); //k는 따로 입력
			
			if(k==0) break;
			
			nums = new int[k]; //k 제외한 나머지 수는 집합에 들어갈 원소들
			for (int i = 0; i < k; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(nums); //사전순으로 조합하기 위해 sort
			
			combi(0, 0); //조합할 때 결과 배열의 0번부터 조합 만들기 시작. 
			sb.append("\n");
		}
		System.out.println(sb);
	}
	//idx는 결과 배열에 쓸 인덱스, start는 
	static void combi(int idx, int start) {
		//조합 완성시 출력
		if(idx == 6) {
//			System.out.println(Arrays.toString(result));
			for (int i : result) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i <k; i++) {
			result[idx] = nums[i];
			combi(idx+1, i+1); //조합할 때 다음에 올 수 있는 수는 앞 수보다 큰 수 (순열과 다름)
		}
	}
}
