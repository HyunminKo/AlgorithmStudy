package SWExpertAcademy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class N_5644 {
	static DOT[] Apath;
	static DOT[] Bpath;
	static BC[] BCarr;
	static int[][] score;
	static List<Integer> list;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int M = sc.nextInt(); // 총 이동시간
			int A = sc.nextInt(); // BC의 개수
			Apath = new DOT[M + 1];
			Bpath = new DOT[M + 1];
			BCarr = new BC[A];

			int x = 1;
			int y = 1;
			Apath[0] = new DOT(x, y);
			for (int i = 1; i < Apath.length; i++) {
				int dir = sc.nextInt();
				DOT d = goDir(x, y, dir);
				Apath[i] = d;
				x = d.x;
				y = d.y;
			}

			x = 10;
			y = 10;
			Bpath[0] = new DOT(x, y);
			for (int i = 1; i < Bpath.length; i++) {
				int dir = sc.nextInt();
				DOT d = goDir(x, y, dir);
				Bpath[i] = d;
				x = d.x;
				y = d.y;
			}

			for (int i = 0; i < A; i++) {
				int X = sc.nextInt();
				int Y = sc.nextInt();
				int C = sc.nextInt();
				int P = sc.nextInt();
				BCarr[i] = new BC(X, Y, C, P);
			}

			System.out.println("#" + test_case + " " + getChargeNum());
		}
	}

	public static int getChargeNum() {
		int sum = 0;
		
		for (int i = 0; i < Apath.length; i++) {
			DOT da = Apath[i];
			DOT db = Bpath[i];
			int[][] check = new int[2][BCarr.length];
			list  = new ArrayList<Integer>();
			for (int j = 0; j < BCarr.length; j++) {
				BC bc = BCarr[j];

				check[0][j] = isCharge(da.x, da.y, bc);
				check[1][j] = isCharge(db.x, db.y, bc);
			}

			int aScore = 0;
			int bScore = 0;
			
			isSame(check);
			if(!list.isEmpty()) {
				int[] maxS = getMax(check);
				aScore = maxS[0];
				bScore = maxS[1];
			}else {
				aScore = selectMax(0,check);
				bScore = selectMax(1,check);
			}
			sum += aScore + bScore;
		}
		return sum;
	}
	
	public static int selectMax(int who,int[][] check) {
		int max = Integer.MIN_VALUE;
		for(int i=0;i<check[0].length;i++) {
			max = Math.max(max, check[who][i]);
		}
		return max;
	}
	
	public static void isSame(int[][] check) {
		for(int i=0;i<check[0].length;i++) {
			if (check[0][i] != 0 || check[1][i] != 0) {
				if(check[0][i] == check[1][i]) {
					list.add(i);
				}				
			}
		}
	}
	
	public static int[] getMax(int[][] check) {
		int[] maxS = new int[2];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < check[0].length; i++) {
			for (int j = 0; j < check[0].length; j++) {
				if (!list.contains(i) || !list.contains(j) || (list.contains(i) && list.contains(j) && check[0][i] != check[1][j])) {
						if (check[0][i] + check[1][j] > max) {
							max = check[0][i] + check[1][j];
							maxS[0] = check[0][i];
							maxS[1] = check[1][j];					
					}
				}
			}
		}
		return maxS;
	}

	public static int getOther(int who, int index, int[][] check) { // 인덱스 자리 제외하고 큰값 구하기
		int max = Integer.MIN_VALUE;

		for (int j = 0; j < check[0].length; j++) {
			if (j != index) {
				max = Math.max(max, check[who][j]);
			}
		}
		return max;
	}

	public static int isCharge(int ax, int ay, BC bc) {
		int dist = Math.abs(ax - bc.X) + Math.abs(ay - bc.Y);
		if (dist <= bc.C)
			return bc.P;
		return 0;
	}

	public static DOT goDir(int x, int y, int dir) {
		if (dir == 1) {
			return new DOT(x, y - 1);
		} else if (dir == 2) {
			return new DOT(x + 1, y);
		} else if (dir == 3) {
			return new DOT(x, y + 1);
		} else if (dir == 4) {
			return new DOT(x - 1, y);
		}
		return new DOT(x, y);
	}

	static class DOT {
		int x, y;

		public DOT(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class BC {
		int X, Y, C, P;

		public BC(int X, int Y, int C, int P) {
			this.X = X;
			this.Y = Y;
			this.C = C;
			this.P = P;
		}
	}
}
