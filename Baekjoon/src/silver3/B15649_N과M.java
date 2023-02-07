package silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * N개중에서 R개 뽑기
 * _,_,_이 있을 때 3개 뽑는다면
 * 1,_,_ 와 2,_,_ 와 3,_,_ ... N,_,_
 * 그다음 1,2_ 와 1,3,_ 와 1,4,_와... 1,N,_
 * ....
 * N,N-1,N-2
 * */
public class B15649_N과M {
	static int N;
	static int R;
	static int[] result;
	static boolean[] used;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		result = new int[R];
		used = new boolean[N+1];
		
		perm(0); //result의 0번 인덱스부터 값을 채우도록
		System.out.println(sb);
	}
	static void perm(int idx) {
		if (idx==R) { //만약 idx가 결국 R의 위치에 있으면(순열이 다 만들어짐)
			for (int n : result) {
				sb.append(n+" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) { //순열 재료는 1부터 N
			if (used[i]) continue; //만약 지금 숫자가 순열에 쓰였으면 넘어가기
			
			result[idx] = i; //현재 인덱스에 숫자 넣기
			used[i] = true; //숫자가 쓰임
			perm(idx+1); //다음 인덱스부터 값을 채우도록 함
			used[i] = false; //현재 인덱스에 다른 숫자를 넣는 순열을 만들어야 하므로 현재 숫자는 안쓰인걸로 바꿈
		}
	}
}
