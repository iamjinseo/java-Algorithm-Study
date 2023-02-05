package silver2;

import java.io.*;
import java.util.*;

public class B11725_트리의부모찾기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Graph_11725 g = new Graph_11725(N);
		
		while(N-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			
			
		}
	}
}

class Graph_11725{
	ArrayList<Integer>  adjList = new ArrayList<Integer>();
	boolean[] visited;
	
	public Graph_11725(int N) {
		visited = new boolean[N+1];
	}
	
	
	
	
}
