package no_rank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class S2383_점심식사시간 {
	static int N, cnt, ans;
	static final int M=1, W=2, D=3, C=4; //이동중, 대기, 내려가는 중, 완료
	static class Person implements Comparable<Person> {
		int r, c, arrivalTime, downCnt, status;
		public Person(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		public void init() {
			arrivalTime = downCnt = 0;
			status = M;
		}
		
		@Override
		public int compareTo(Person o) {
			return Integer.compare(this.arrivalTime, o.arrivalTime);
		}
	}
	static ArrayList<Person> pList; // 사람 리스트
	static int[][] sList;  // 계단 리스트
	static int[] selected; // 어떤 사람이 선택한 계단의 인덱스
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());  // 맵 한 변의 길이
			pList = new ArrayList<>();	// 사람리스트
			sList = new int[2][3];		// 계단리스트
			ans = Integer.MAX_VALUE;
			
			StringTokenizer st = null;
			
			for (int i = 0, k=0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int c = Integer.parseInt(st.nextToken());
					if(c==1) { // 사람
						pList.add(new Person(i, j));
					} else if(c>1) { // 계단
						sList[k++] = new int[] {i, j, c};
					}
				}
			}
			cnt = pList.size(); // 사람 총 수
			selected = new int[cnt]; // 선택한 계단 인덱스
			// 모든 사람에게 계단 배정
			divide(0);
			System.out.println(ans);
		}
	}
	
	// power set 
	static void divide(int index) {
		if(index == cnt) { // 모든사람의 계단 선택 완료
			int res = go();
			if(ans>res) ans = res; // 최소값 갱신
			return;
		}
		
		// 계단 0  선택
		selected[index] = 0;
		divide(index+1);
		// 계단 1 선택
		selected[index] = 1;
		divide(index+1);
	}
	
	// 선택된 계단에 따라 사람들의 리스트 만들고 내려가기 처리 후 소요된 시간 리턴
	static int go() {
		ArrayList<Person>[] list = new ArrayList[] {new ArrayList<Person>(), new ArrayList<Person>()};
		
		for (int i = 0; i < cnt; i++) {
			Person p = pList.get(i); // 사람 선택
			p.init(); // 사람의 상태를 초기화
			int no = selected[i];    // 선택된 사람이 지금 있는 계단
			p.arrivalTime = Math.abs(p.r - sList[no][0]) + Math.abs(p.c - sList[no][1]); // 계단과의 거리 
			list[no].add(p); // no 계단에 사람 p가 있음 
		}
		
		int timeA=0, timeB=0;
		if(list[0].size()>0) { // 첫번째 계단 내려가기
			timeA = processDown(list[0], sList[0][2]);
		}
		if(list[1].size()>0) { // 두번째 계단 내려가기
			timeB = processDown(list[1], sList[1][2]);
		}
		return Math.max(timeA, timeB);
	}
	
	// 내려가기
	static int processDown(ArrayList<Person> list, int height) {
		Collections.sort(list); // 계단 입구까지 도착시간이 빠른 순으로 정렬
		int time = list.get(0).arrivalTime; // 먼저 도착한 사람의 시간부터 흘러가게 함
		int size = list.size();
		
		int ingCnt = 0, cCnt=0;  // 내려가는 사람수, 완료 사람수
		Person p = null;
		while(true) {
			for (int i = 0; i < size; i++) {
				p = list.get(i);
				
				if(p.status == C) { // 다 내려감
					continue;
				}else if(p.arrivalTime == time) {
					p.status = W; // 웨이팅 상태로 변경
				}else if(p.status == W && ingCnt < 3) { // 대기중인데 내려가는 사람이 3명보다 적으면
					p.status =D; //내려가는 중임
					p.downCnt = 1;
					ingCnt++; //내려가는 사람이 한 명 늘어남
				}else if(p.status == D) {
					if(p.downCnt == height) {
						p.status = C;
						ingCnt--;
						cCnt++;
					}else {
						p.downCnt++;
					}
				}
			} // 해당 시간에 모든 사람에 대해 처리
			if(cCnt == size) break; 
			time += 1;
		}
		return time;
	} 
}