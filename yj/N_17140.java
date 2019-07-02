package BaekJoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class N_17140 {
	static int[][] arr;
	static List<String> list;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		int c = sc.nextInt();
		int k = sc.nextInt();
		arr = new int[3][3];
		for(int i = 0;i<3;i++) {
			for(int j=0;j<3;j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		int time = 0;
		while(true) {
			if(r-1 < arr.length && c-1 < arr[0].length && arr[r-1][c-1] == k) {
				break;
			}
			list = new ArrayList<String>();
			if(arr.length >= arr[0].length) { // 행 정렬
				getRCount();
			}else { // 열 정렬
				getCCount();
			}
			time++;
			if(time > 100) {
				time = -1;
				break;
			}
		}
		System.out.println(time);
	}
	
	public static void getRCount() {
		int max = 0;
		for(int i=0;i<arr.length;i++) {
			int count=0;
			HashMap<String,Integer> hm = new HashMap<>();
			for(int j=0;j<arr[0].length;j++) {
				if(arr[i][j] != 0) {
					String key = i+","+arr[i][j];
					if(hm.containsKey(key)) {
						hm.put(key, hm.get(key)+1);
					}else {
						hm.put(key, 1);
						count++;
					}					
				}
			}
			List<String> keySetList = new ArrayList<>(hm.keySet());
			Collections.sort(keySetList, new Comparator<String>() {
	            @Override
	            public int compare(String o1, String o2) {
	            	if(hm.get(o1) == hm.get(o2)) {
	            		if(Integer.parseInt(o1.split(",")[1]) < Integer.parseInt(o2.split(",")[1])) {
	            			return -1;
	            		}else {
	            			return 1;
	            		}
	            	}else {
	            		return hm.get(o1).compareTo(hm.get(o2));
	            	}
	            }
	        });
			for(String key : keySetList) {
				String item = key + "," + hm.get(key);
				list.add(item);
			}
			max = Integer.max(count, max);
		}
		
		arr = new int[arr.length][max*2];
		int num = 0;
		int count = 0;
		for(String s : list) {
			String[] temp = s.split(",");
			if(num != Integer.parseInt(temp[0])) {
				num = Integer.parseInt(temp[0]);
				count = 0;
			}
			arr[Integer.parseInt(temp[0])][count] = Integer.parseInt(temp[1]);
			arr[Integer.parseInt(temp[0])][count+1] = Integer.parseInt(temp[2]);
			count+=2;
		}
	}
	
	public static void getCCount() {
		int max = 0;
		for(int i=0;i<arr[0].length;i++) {
			int count=0;
			HashMap<String,Integer> hm = new HashMap<>();
			for(int j=0;j<arr.length;j++) {
				if(arr[j][i] != 0) {
					String key =i+","+arr[j][i];
					if(hm.containsKey(key)) {
						hm.put(key, hm.get(key)+1);
					}else {
						hm.put(key, 1);
						count++;
					}					
				}
			}
			List<String> keySetList = new ArrayList<>(hm.keySet());
			Collections.sort(keySetList, new Comparator<String>() {
	            @Override
	            public int compare(String o1, String o2) {
	            	if(hm.get(o1) == hm.get(o2)) {
	            		if(Integer.parseInt(o1.split(",")[1]) < Integer.parseInt(o2.split(",")[1])) {
	            			return -1;
	            		}else {
	            			return 1;
	            		}
	            	}else {
	            		return hm.get(o1).compareTo(hm.get(o2));
	            	}
	            }
	        });
			for(String key : keySetList) {
				String item = key + "," + hm.get(key);
				list.add(item);
			}
			max = Integer.max(count, max);
		}

		arr = new int[max*2][arr[0].length];
		int num = 0;
		int count = 0;
		for(String s : list) {
			String[] temp = s.split(",");
			if(num != Integer.parseInt(temp[0])) {
				num = Integer.parseInt(temp[0]);
				count = 0;
			}
			arr[count][Integer.parseInt(temp[0])] = Integer.parseInt(temp[1]);
			arr[count+1][Integer.parseInt(temp[0])] = Integer.parseInt(temp[2]);
			count+=2;
		}

	}
}
