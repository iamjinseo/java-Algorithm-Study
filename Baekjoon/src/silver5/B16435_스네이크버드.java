package silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B16435_스네이크버드 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		st=new StringTokenizer(br.readLine());
		int[] fruits = new int[N];
		for (int i = 0; i < N; i++) {
			fruits[i] = Integer.parseInt(st.nextToken());
		} //end input
		
		int res = L;
		Arrays.sort(fruits);
		for (int f : fruits) {
			if(f>res) break; //과일이 더 높으면 안됨
			
			res++;
		}
		System.out.println(res);
	}

}
