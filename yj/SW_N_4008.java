package SWExpertAcademy;

import java.util.Scanner;

public class N_4008 {
	static int[] arr;
	static int max;
	static int min;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt(); // 숫자 개수
			arr = new int[N];
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			int[] operator = new int[4];

			for(int i=0;i<4;i++) {
				operator[i] = sc.nextInt();
			}
			
			for(int i=0;i<N;i++) { // 카드 입력받기
				arr[i] = sc.nextInt();
			}
			
			dfs(operator,arr[0],1);
			
			System.out.println("#"+test_case+" "+(max-min));
		}
	}
	
	public static void dfs(int[] operator,int num,int index) {
		if(index >= arr.length) { // 숫자 끝나면
			max = Math.max(num, max);
			min = Math.min(num, min);
			return;
		}
		
		for(int i=0;i<operator.length;i++) {
			if(operator[i] != 0) {
				operator[i]--;
				if(i == 0) {
					dfs(operator,num+arr[index],index+1);
				}else if(i== 1) {
					dfs(operator,num-arr[index],index+1);
				}else if(i == 2) {
					dfs(operator,num*arr[index],index+1);
				}else {
					dfs(operator,num/arr[index],index+1);
				}
				operator[i]++;				
			}
		}
	}
}
