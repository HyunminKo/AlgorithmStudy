package SWExpertAcademy;

import java.util.Scanner;

public class N_4012 {
	static int[][] arr;
	static int result; 
	static boolean[] check;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			result = Integer.MAX_VALUE;
			arr = new int[N][N];
			check = new boolean[N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			makeIndex(0,0);
			System.out.println("#"+test_case+" "+result);
		}
	}
	
	public static void makeIndex(int index,int count) {
		if(count == arr.length/2) {
			makeFood(check);
			return;
		}

		for(int i=index;i<arr.length;i++) {
			check[i] = true;
			makeIndex(i+1,count+1);
			check[i] = false;
		}
	}
	
	public static void makeFood(boolean[] check) {
		int A_sum = 0;
		int B_sum = 0;
		for(int i=0;i<check.length;i++) {
			for(int j=0;j<check.length;j++) {
				if(i == j) continue;
				if(check[i] && check[j]) A_sum += arr[i][j];
				if(!check[i] && !check[j]) B_sum += arr[i][j];
			}
		}
		result = Math.min(Math.abs(A_sum-B_sum), result);
	}

}
