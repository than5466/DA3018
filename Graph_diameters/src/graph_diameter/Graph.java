package graph_diameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph {

	/**
	 * V is the number of vertices in the graph. The queue q is used for storing
	 * unvisited nodes in the BTS search. Implemented via a linked list. connected
	 * is whether the graph is connected or not. Updated after a BTS search is
	 * performed. The visited nodes are saved in an ArrayList. edges is a hashmap
	 * with the nodes as keys, and they're corresponding values is a linked list
	 * containing the nodes to which they share an edge.
	 */
	private int V = 0;
	private Queue<Integer> q;
	private boolean connected;

	private Map<Integer, LinkedList<Integer>> graph;
	private ArrayList<Integer> visited;

	/**
	 * First constructor, creating an empty graph.
	 */

	public Graph() {
		this.graph = new HashMap<Integer, LinkedList<Integer>>();
	}

	/**
	 * 
	 * @param n - size of the graph
	 * @param p - probability to create an edge between two nodes.
	 * 
	 *          Creates a random undirected with n nodes and a p probability for any
	 *          two nodes to share an edge. random is used to determine whether two
	 *          nodes (a and b) share an edge, and if so, an edge is created from a
	 *          to b and from b to a.
	 */

	public Graph(int n, double p) {
		this.connected = true;
		this.graph = new HashMap<Integer, LinkedList<Integer>>();
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

	/**
	 * 
	 * @param node - an integer creates a new node in the graph with value n
	 */

	public void addVertice(int node) {
		this.graph.put(node, new LinkedList<Integer>());
		this.V++;
	}

	/**
	 * 
	 * @param node1 - an existing node
	 * @param node2 - an existing node adds an edge from node1 to node2 and from
	 *              node2 to node1.
	 */

	public void addEdge(int node1, int node2) {
		this.graph.get(node1).add(node2);
		this.graph.get(node2).add(node1);

	}

	/**
	 * 
	 * @param n
	 * @return returns true or false. Greater values on n increases the chance of
	 *         returning true.
	 */

	public static boolean randomize(double n) {
		return n > Math.random();
	}

	/**
	 * 
	 * @param start - the node from which the distance is calculated.
	 * @return a hashmap where the keys are the nodes and the values are the minimum
	 *         distance from the start node to the node with the keys value.
	 */

	public Map<Integer, Integer> distance(int start) {
		this.visited = new ArrayList<Integer>();
		Map<Integer, Integer> mapDist = new HashMap<Integer, Integer>();
		this.q = new Queue<Integer>();
		return distance(start, mapDist, this.q);
	}

	/**
	 * 
	 * @param start
	 * @param map
	 * @param dist  -current distance. every recursive call this is increased by 1.
	 * @return Will return a hashmap containing the distance to all the nodes when
	 *         the queue is empty.
	 * 
	 */

	private Map<Integer, Integer> distance(int start, Map<Integer, Integer> map, Queue<Integer> queue) {
		this.visited.add(start);
		int dist = 1;

		int current;
		int size;
		for (int i : this.graph.get(start)) {
			queue.enqueue(i);
			this.visited.add(i);

		}
		while (!queue.isEmpty()) {
			size = queue.size();
			while (size > 0) {
				current = queue.dequeue();
				for (int i : this.graph.get(current)) {
					if (!this.visited.contains(i)) {
						queue.enqueue(i);
						this.visited.add(i);
					}
				}
				size--;
				map.put(current, dist);

			}
			dist++;

		}
		for (int i = 1; i <= this.V; i++) {
			if (i != start) {
				if (!map.containsKey(i)) {
					map.put(i, -1);
					this.connected = false;
				}
			}
		}
		return map;
	}

	/**
	 * 
	 * @return the number of nodes in the graph.
	 */

	public int size() {
		return this.V;
	}

	/**
	 * 
	 * @return if every node is reachable from every other node, return true,
	 *         otherwise false.
	 */

	public boolean connected() {
		return this.connected;
	}

	/**
	 * 
	 *
	 * @param <E> raw type. Only used on integers in this lab. Implemented via a
	 *            linked list.
	 */

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

		for (LinkedList<Integer> a : x.graph.values()) {
			System.out.println(a);
		}
		Map<Integer, Integer> a = x.distance(5);
		System.out.println(a);

	}

}
