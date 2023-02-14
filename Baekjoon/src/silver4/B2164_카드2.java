package silver4;

import java.util.*;

public class B2164_카드2 {
	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);

	        int n = scanner.nextInt();

	        Queue<Integer> queue = new LinkedList<>();
	        for(int i=1; i<=n; i++) {
	            queue.add(i);
	        }

	        boolean flag = true;
	        while(queue.size() != 1) {
	            if(flag) queue.poll();
	            else {
	                int a = queue.poll();
	                queue.add(a);
	            }
	            flag = !flag;
	        }
	        System.out.println(queue.poll());
	}
}
