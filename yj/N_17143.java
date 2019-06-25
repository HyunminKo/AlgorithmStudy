package BaekJoon;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N_17143 {
	static Queue<Fish> fish_queue;
	static int R;
	static int C;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		fish_queue = new LinkedList<Fish>();
		R = sc.nextInt();
		C = sc.nextInt();
		int M = sc.nextInt();
		int result = 0;
		for(int i=0;i<M;i++) {
			int x = sc.nextInt();
			int y =sc.nextInt();
			int move = sc.nextInt();
			int dir = sc.nextInt(); // 1:상, 2:하, 3:우, 4:좌
			int size = sc.nextInt();
			fish_queue.add(new Fish(x,y,move,dir,size));
		}
		
		int y_location = 0;
		while(y_location < C) {
			// 물고기 죽이기
			y_location++;
			Fish kf = killFish(y_location);
			
			if(kf != null) {
				fish_queue.remove(kf);
				result += kf.size;				
			}
			// 물고기 이동
			moveFish();	
		}
		System.out.println(result);
	}

	public static Fish killFish(int y_location) {
		Fish kf = null;
		int x_location = Integer.MAX_VALUE;
		for(Fish f : fish_queue) {
			if(f.y == y_location && f.x < x_location) {
				x_location = f.x;
				kf = f;
			}
		}
		return kf;
	}
	
	public static void moveFish() {
		Queue<Fish> temp_queue = new LinkedList<Fish>();
		int temp;
		while(!fish_queue.isEmpty()) {
			Fish f = fish_queue.poll();
			int dir = f.dir;
			int na = f.move;
			while(na > 0) { // 움직이기
				if(dir == 1) { // 상
					temp = f.x - na;
					if(temp <= 0) {
						na = -(temp-1);
						temp = temp + na;
						dir = 2;
					}else {
						na = 0;
					}
					f.x = temp;
				}else if(dir == 2) { // 하
					temp = f.x + na;
					if(temp > R) {
						na = temp - R; // 남은 이동수
						temp = temp - na; // 이동한 좌표
						dir = 1;
					}else {
						na = 0;
					}
					f.x = temp;
				}else if(dir == 3) { // 우
					temp = f.y + na;
					if(temp > C) {
						na = temp - C;
						temp = temp - na;
						dir = 4;
					}else {
						na = 0;
					}
					f.y = temp;
				}else { // 좌
					temp = f.y - na;
					if(temp <= 0) {
						na = -(temp-1);
						temp = temp + na;
						dir = 3;
					}else {
						na = 0;
					}
					f.y = temp;
				}	
			}
			f.dir = dir;
			temp_queue.add(f);
		}
		fish_queue = eatFish(temp_queue);
	}
	
	public static LinkedList<Fish> eatFish(Queue<Fish> temp_queue) {
		HashMap<String,Fish> hm = new HashMap<String,Fish>();
		for(Fish f : temp_queue) {
			String key = f.x +","+f.y;
			if(hm.containsKey(key)) {
				Fish temp = hm.get(key);
				if(temp.size < f.size) {
					hm.put(key, f);
				}
			}else {
				hm.put(key, f);
			}
		}
		return new LinkedList<>(hm.values());
	}
}

class Fish{
	int x,y,size,dir,move;
	
	public Fish(int x,int y,int move,int dir,int size) {
		this.x = x;
		this.y = y;
		this.move = move;
		this.dir = dir;
		this.size= size;
	}
}