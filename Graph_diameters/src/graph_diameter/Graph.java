package graph_diameter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph {
	LinkedList<Integer> listArray[];
	int V;
	Queue<Integer> queue;
	int dist;
	Map<Integer,Integer> map;
	
	public Graph( ) {
		this.V = 0;
		this.listArray = new LinkedList[0];
	}

	public Graph(int n, double p) {
		this.listArray = new LinkedList[n];
		this.V = n;
		boolean x;

		for (int i = 0; i < n; i++) {
			this.listArray[i] = new LinkedList<>();

		}

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				x = randomize(p);
				if (x) {
					this.listArray[i].add(j + 1);
					this.listArray[j].add(i + 1);

				}
			}
		}
	}
	

	public static boolean randomize(double n) {
		return n > Math.random();
	}
	
	public void distance(int start)  {
		this.dist = 0;
		map = new HashMap<Integer,Integer>();
		this.queue = new Queue<Integer>();
		distance(start,map);
	}
	
	public void distance(int start, Map<Integer,Integer>  map) {
		if (this.dist == 0) {
			this.dist ++;
			for (int i: this.listArray[start-1]) {
				this.queue.enqueue(i);
			}
			distance(start,map);
		}
		else if (this.queue.isEmpty()) {
			for (int i = 1; i <= this.V;i++) {
				if (i != start && !map.containsKey(i)) {
					map.put(i, -1);
				}
			}
			for (int i: map.keySet()) {
				System.out.print(i);
				System.out.print(" distance: ");
				System.out.println(map.get(i));
			}
		}
		else {
			int a;
			int x = this.queue.size();
			while (x > 0) {
				a = this.queue.peek();
				this.queue.dequeue();
				if (!map.containsKey(a)) {
					for (int i: this.listArray[a-1]) {
						if (i != start) {
							this.queue.enqueue(i);
						}
					}
					map.put(a, this.dist);
				}
				
				x --;
			}

			this.dist ++;
			distance(start,map);
		}
	}
	
	
	
	public class Queue<E> {
		LinkedList<E> arr;
		
		public Queue() {
			this.arr = new LinkedList<>();
		}
		
		public void enqueue(E var) {
			this.arr.add(var);
		}
		
		public void dequeue() {
			
			this.arr.removeFirst();
		}
		
		public E peek() {
			return this.arr.getFirst();
		}
		public boolean isEmpty() {
			return this.arr.isEmpty();
		}
		
		public int size() {
			return this.arr.size();
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph x = new Graph(25, 0.1);

		for (LinkedList<Integer> a : x.listArray) {
			System.out.println(a);
		}
		x.distance(5);

	}

}
