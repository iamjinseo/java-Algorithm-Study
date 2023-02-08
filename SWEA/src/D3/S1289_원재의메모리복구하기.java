package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1289_원재의메모리복구하기 {
	static String memory; 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			memory = br.readLine();
			String firstMemory = memory.replace('1', '0'); //초기메모리
			System.out.printf("#%d %d", t, fix(firstMemory, 0, 0));
		}
	}
	/**
	 * @param mem 현재 고치고 있는 메모리
	 * @param idx 지금 재귀에서 가리키고 있는 memory의 자리수
	 * @param turn 고친 횟수
	 * */
	static int fix(String mem, int idx, int turn){
		if(idx>=memory.length()) return -123456;
		
		//현재 자리수에서 숫자를 고칠까 말까 정하기
		//만약 원래 메모리랑 지금 메모리랑 자리수의 숫자가 다르면 그 숫자로 바꾸기
		if(memory.charAt(idx) != mem.charAt(idx)) {
			if(memory.charAt(idx)=='0') { //원래 지금 자리수 숫자가 0이어야 되면
				// 예를들어 1010인데 지금 1111이면, 1+000해야 함
				// idx전까지의 문자열 떼고, idx부터의 문자열은 전부 0으로 변경
				mem = mem.substring(0, idx) + mem.substring(idx).replace('1', '0');
			}
			else { //원래 지금 자리수 숫자가 1이어야 되면
				// 1011 vs 1000 => 10+11
				mem = mem.substring(0, idx) + mem.substring(idx).replace('0', '1');
			}
			turn++; //수정 횟수 증가
		}
		//만약 목표숫자 달성 시 재귀 종료
		if(mem.equals(memory)) return turn;
		//목표숫자 미달성시 다음자리 숫자 정하기
		return fix(mem, ++idx, turn);
	}

}
