package silver4;

import java.util.*;

public class B1764 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//숫자 입력
		int noListen = sc.nextInt();
		int noSeen = sc.nextInt();
		
		//사람 이름에 해당하는 사람이 몇명인지 검사
		//듣과 보에 중복은 없으므로 중복 우려는 없음
		Set<String> s = new HashSet<>();
		
		
		//사람 입력
		for (int i = 0; i < noListen; i++) { //못들은사람 넣기
			String name = sc.next();
			s.add(name);
		}
		
		List<String> res = new ArrayList<String>();
		for (int i = 0; i < noSeen; i++) {
			String name = sc.next();
			if(s.contains(name)) { //듣보 존재?
				res.add(name);
			}
		}
		Collections.sort(res);
		System.out.println(res.size());
		for (String name : res) {
			System.out.println(name);
		}
	}
}
