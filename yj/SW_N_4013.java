package SWExpertAcademy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N_4013 {
	static String[] arr;
	static Queue<Info> queue;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int K= sc.nextInt();
			arr = new String[4];
			queue = new LinkedList<>();
			for(int i=0;i<4;i++) {
				String str = "";
				for(int j=0;j<8;j++) {
					int dir = sc.nextInt();
					str += dir;
				}
				arr[i] = str;
			}
			
			for(int i=0;i<K;i++) { // num은 1~4
				int num = sc.nextInt();
				int dir = sc.nextInt();
				queue.add(new Info(num-1,dir));
			}
			
			rotateMagnet();
			
			// 점수 계산
			int result = 0;
			if((arr[0].charAt(0) - '0') == 1) {
				result += 1;
			}
			if((arr[1].charAt(0) - '0') == 1) {
				result += 2;
			}
			if((arr[2].charAt(0) - '0') == 1) {
				result += 4;
			}
			if((arr[3].charAt(0) - '0') == 1) {
				result += 8;
			}

			System.out.println("#"+test_case+" "+result);
		}
	}
	
	public static void rotateMagnet() {
		while(!queue.isEmpty()) {
			Info info = queue.poll();
			int num = info.num;
			int dir = info.dir;
			Queue<Info> temp_queue = new LinkedList<>();
			temp_queue.add(info);
			while(num-1 > -1){
				// 왼쪽비교
				if(arr[num-1].charAt(2) != arr[num].charAt(6)) { // 자성 다르면 
					dir = -dir;
					temp_queue.add(new Info(num-1,dir));
				}else {
					break;
				}
				num--;
			}
			
			num = info.num;
			dir = info.dir;
			while(num+1 < 4){
				// 오른쪽비교
				if(arr[num].charAt(2) != arr[num+1].charAt(6)) { // 자성 다르면 
					dir = -dir;
					temp_queue.add(new Info(num+1,dir));
				}else {
					break;
				}
				num++;
			}
			
			while(!temp_queue.isEmpty()) {
				Info item = temp_queue.poll();
				setMagnet(item.num,item.dir);
			}
		}
	}
	
	
	public static void setMagnet(int num,int dir) {
		String str = arr[num];
		if(dir == 1) {
			arr[num] = str.charAt(str.length()-1) + str.substring(0,str.length()-1);
		}else {
			arr[num] = str.substring(1,str.length()) + str.charAt(0);
		}
	}

	public static class Info {
		int num,dir;
		
		public Info(int num,int dir) {
			this.num = num;
			this.dir = dir;
		}
	}
}
