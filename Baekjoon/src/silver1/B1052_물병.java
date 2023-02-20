package silver1;

import java.util.Scanner;

/*
N이 2의 제곱이면 물병 1개에 도달할 수 있음
만약에 수가 무엇이든 K가 2면? 2의 제곱수 두개로 나뉘어야 함.
그렇다면 N을 최대한 큰 2의 제곱수로 자름. 
그렇다면 남은 나머지 부분에서, 2의 제곱수가 될 때까지 물병을 몇 개 더 채운 수가 정답이 아닐까? 
 * */
public class B1052_물병 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int res = 0;

		/*
		 * N/K 값보다 가장 근접하게 큰 2의 제곱수를 찾아 그만큼 N에서 빼버림
		 * 그런데 구해낸 2의 제곱수가 N보다 크면
		 */
		while (N > 0) {
//			if(N==K) break;
//			else if(N<K) {
//				
//			}
			
			int target = (int)(Math.log(N) / Math.log(K)); // logk(2)
			target = (int)Math.pow(2, target); // N/K값보다 가장 근접하게 큰 2의 제곱수 
			if(target>N/K)
			
			System.out.println("target: "+target);
			N -= (int)Math.pow(2, target); // N에서 빼버림
			System.out.println("N: "+N);
			res++;
			K--;
			System.out.println("res: "+res+", K: "+K);
		}
		System.out.println(res);
	}
}
