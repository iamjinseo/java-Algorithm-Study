package gold5;

import java.io.*;
import java.util.*;

public class B2493_탑 {

	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 탑 개수
		Stack<int[]> s1 = new Stack<>(); //처음에 입력받았을 때 탑을 넣는곳
		Stack<int[]> s2 = new Stack<>(); //s1에서 pop하면서 들어갈 곳
		int[] res = new int[N]; //결과출력용 배열
		
		StringTokenizer st =new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			//숫자와 순서(인덱스+1) 형태로 스택에 넣기
			int[] arr = {Integer.parseInt(st.nextToken()), i};
			s1.push(arr);
		}
		
		// 끝에서부터 pop 하며 s2에 다시 넣음
		// s2의 top보타 s1에서 pop한 것이 더 크면 s2의 top 없애기. pop한 게 더 클 때까지
		// s2의 top보다 s1에서 pop한 것이 더 작으면 s2에 그냥 넣기
		
		//만약 7 2 3 4면
		// 4빼고 넣음->3뺐는데 4보다 작아서 넣음->2뺐는데 3보다 작아서 넣음-> 
		// 7 뺌->2보다 크니까 2 없앰->3보다 크니까 3 없앰->4보다 크니까 4 없앰
		// 수를 없애면서 어떤 수에 의해 없어졌는지 출력해야 함
		// 예를들어 2가 7에 의해 없어졌으므로 res[2-1]에 7의 인덱스를 넣음
		while(!s1.isEmpty()) { //s1이 빌 때까지
			int[] popped = s1.pop();
			while(!s2.isEmpty() && popped[0]>s2.peek()[0]) {
				int[] popped2 = s2.pop();
//				System.out.println(popped[0]+"이 더 커서"+popped2[0]+"빼야댐");
				res[popped2[1]-1] = popped[1]; //현재 탑(popped2[1])이 어떤 탑(popped[1])보다 더 작았는지 넣음
//				System.out.println("그래서 지금 res는?: "+ Arrays.toString(res));
//				sb.append(popped[1]).append(" ");
			}
			s2.push(popped); //s2에 비교할 게 없거나 pop된 게 더 작으면 pop된 거 넣기
		}
		while(!s2.isEmpty()) { //위의 예시같은 경우 마지막에 7이 남아있을 것임
			int[] popped = s2.pop();
			res[popped[1]-1] = 0;
		}
		for (int i : res) {
			System.out.print(i+" ");
		}
	}
}
