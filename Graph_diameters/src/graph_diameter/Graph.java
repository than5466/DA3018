package graph_diameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph {
	// V = Vertices (nodes), E = Edges.

	private int V = 0;
	private Queue<Integer> q;
	private boolean connected;

	private Map<Integer, LinkedList<Integer>> edges;
	private ArrayList<Integer> visited;

	public Graph() {
		this.edges = new HashMap<Integer, LinkedList<Integer>>();
	}

	public Graph(int n, double p) {
		this.connected = true;
		this.edges = new HashMap<Integer, LinkedList<Integer>>();
		for (int i = 1; i <= n; i++) {
			addVertice(i);
		}
		boolean compare;
		for (int i = 1; i < n; i++) {
			for (int j = i + 1; j <= n; j++) {
				compare = randomize(p);
				if (compare) {
					addEdge(i, j);
				}
			}
		}

	}

	public void addVertice(int node) {
		this.edges.put(node, new LinkedList<Integer>());
		this.V++;
	}

	public void addEdge(int node1, int node2) {
		/**
		if (!this.edges.containsKey(node1)) {
			addVertice(node1);
		}
		if (!this.edges.containsKey(node2)) {
			addVertice(node2);
		}
		*/
		this.edges.get(node1).add(node2);
		this.edges.get(node2).add(node1);

	}

	public static boolean randomize(double n) {
		return n > Math.random();
	}

	public Map<Integer,Integer> distance(int start) {
		this.visited = new ArrayList<Integer>();
		Map<Integer,Integer> mapDist = new HashMap<Integer, Integer>();
		this.q = new Queue<Integer>();
		return distance(start, mapDist, 0);
	}

	private Map<Integer,Integer> distance(int start, Map<Integer,Integer> map, int dist) {

		if (!this.visited.contains(start)) {
			this.visited.add(start);
			for (int i : this.edges.get(start)) {
				this.q.enqueue(i);
				this.visited.add(i);
			}
		} else if (this.q.isEmpty()) {
			for (int i = 1; i <= size(); i++) {
				if (!this.visited.contains(i)) {
					map.put(i, -1);
					this.connected = false;
				}

			}
			return map;
		} else {
			int a;
			int x = this.q.size();
			while (x > 0) {
				a = this.q.dequeue();
				for (int i : this.edges.get(a)) {
					if (!this.visited.contains(i)) {
						this.q.enqueue(i);
						this.visited.add(i);
					}
				}
				map.put(a, dist);
				x--;
			}
		}
		return distance(start,map,dist+1);

	}
	
	public int size() {
		return this.V;
	}
	
	public boolean connected() {
		return this.connected;
	}

	public class Queue<E> {
		private LinkedList<E> q;

		public Queue() {
			this.q = new LinkedList<>();
		}

		public void enqueue(E var) {
			this.q.add(var);
		}

		public E dequeue() {

			return this.q.removeFirst();
		}

		public boolean isEmpty() {
			return this.q.isEmpty();
		}

		public int size() {
			return this.q.size();
		}

		public boolean contains(E var) {
			return this.q.contains(var);
		}
	}

	public static void main(String[] args) {
		Graph x = new Graph(25, 0.1);

		for (LinkedList<Integer> a : x.edges.values()) {
			System.out.println(a);
		}
		Map<Integer,Integer> a = x.distance(5);
		System.out.println(a);

	}

}
