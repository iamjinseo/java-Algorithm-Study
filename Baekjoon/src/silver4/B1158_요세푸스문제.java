package silver4;

import java.net.SocketTimeoutException;
import java.util.*;

// 1234567
// 3번째 ㅅ ㅏ람을 제거
// => 124567
// => 12457
// => 1457
// => 145
// => 14 
// => 4
// => 
public class B1158_요세푸스문제 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();

		Deque<Integer> queue = new ArrayDeque<>(); // 사람이 앉아있는 순서
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= N; i++) {
			queue.add(i); // 사람 넣기
		}

		sb.append("<");
		while (queue.size()!=1) { // 큐
			for (int k = 1; k < K; k++) {
				queue.addLast(queue.pollFirst());
			}
			sb.append(queue.pollFirst()).append(", ");
		}
		sb.append(queue.poll()).append(">");
		System.out.println(sb);
	}
}
