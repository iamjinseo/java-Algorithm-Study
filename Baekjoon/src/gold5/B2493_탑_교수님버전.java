package gold5;

import java.io.*;
import java.util.*;

// 스택버전
public class B2493_탑_교수님버전 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());  //타워 개수

		Stack<Tower> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) { //1번 타워부터 N번 타워까지 순서대로 입장~~
			Tower now = new Tower(i, Integer.parseInt(st.nextToken()));
			
			//스택에(나보다 왼쪽에 있던)작은 놈들은 다 치워. 계속 치워. 그러면? 비거나 큰놈일 때 반복 종료
			while(!stack.isEmpty() && stack.peek().height<now.height)
				stack.pop();
			
			if(stack.empty()) //결국 다 쪼마니들이었네(내가 나머지들 다 치워버렸을 때)
				sb.append("0 ");
			else
				sb.append(stack.peek().num+" "); //나보다 큰 놈이 스택에 들어있으므로 걔에 의해 레이저가 닿지 않겠음?? 
			
			stack.push(now);
		}
		System.out.println(sb);
	}

	static class Tower {
		int num, height;

		public Tower(int num, int height) {
			this.num = num;
			this.height = height;
		}
	}
}
