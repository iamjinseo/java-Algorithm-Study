package gold4;

/*
주사위 모양

    뒤
왼 위 오른
    앞
   아래

만약 주사위가
0 1 2 3 4 5 6이면

주사위 남쪽으로 굴리기

  2             6
4 1 3   =>    4 2 3
  5             1 
  6             5

인덱스: 2->1, 1->5, 5->6

북쪽으로 굴리기

  2             1
4 1 3   =>    4 5 3
  5             6 
  6             2

인덱스: 1->2, 2->6, 5->1

동쪽으로 굴리기

  2            2
4 1 3   =>   6 4 1
  5            5
  6            3


서쪽으로 굴리기

  2            2
4 1 3   =>   6 4 1
  5            5
  6            3
 
 * */
public class B1449_주사위굴리기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
