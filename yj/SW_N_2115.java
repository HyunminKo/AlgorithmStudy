package SWExpertAcademy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class N_2115 {
	static int[][] arr;
	static int M;
	static int C;
	static int max_cost;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			max_cost = 0;
			int N = sc.nextInt(); // 벌통 크기
			M = sc.nextInt(); // 벌통 개수
			C = sc.nextInt(); // 꿀 채취 가능한 양
			arr = new int[N][N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			System.out.println("#"+test_case+" "+ selectHoney());
		}
	}
	
	public static int selectHoney() {
		List<Integer> cost_list = new ArrayList<>();
		int[] honey = new int[M];
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<=arr.length-M;j++) { // 한 라인 경우의 수 담기
				int index = 0;
				for(int k=0;k<M;k++) { // 한 라인에서 M만큼의 경우의 수 추출
					honey[index++] = arr[i][j+k];
				}
				putBucket(honey,"",0,0); // 그릇에 무엇을 담아야 할지 경우의 수 
			}
			cost_list.add(max_cost);
			max_cost = 0;
		}
		Collections.sort(cost_list); // 내림차순 정렬 후 0,1번째만 추출
		Collections.reverse(cost_list);
		return cost_list.get(0)+cost_list.get(1);
	}

	public static void putBucket(int[] arr,String comb,int sum,int index) { // 그릇에 무엇을 담아야 할지 경우의 수 
		if(sum > C) return; // 넘어가면 리턴
		if(comb != "" && sum <= C) {
			String[] temp = comb.split("");
			int cost=0;
			for(int i=0;i<temp.length;i++) {
				cost = cost + Integer.parseInt(temp[i]) * Integer.parseInt(temp[i]); 
			}
			max_cost = Math.max(cost, max_cost);
		}
		
		for(int k = index;k<arr.length;k++) {
			 putBucket(arr,comb+arr[k],sum+arr[k],k+1);
		}	
	}
}
