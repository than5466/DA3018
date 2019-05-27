package graph_diameter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DotGraph<E> {
	private Map<E,LinkedList<E>> graph;
	/**
	 * graph structure used in the GraphDotIO class.
	 */

	public DotGraph() {
		this.graph = new HashMap<E,LinkedList<E>>();
	}
	
	private void addVertice(E n) {
		this.graph.put(n,new LinkedList<E>());
	}
	
	private void addEdge(E n,E m) {
		
		if (!this.graph.get(n).contains(m)) {
			this.graph.get(n).add(m);
		}
	}
	
	/**
	 * 
	 * @param n
	 * @param m
	 * add an edge from the node n to the node m.
	 */
	public void add(E n, E m) {
		if (!this.graph.containsKey(n)) {
			addVertice(n);
		}
		if (!this.graph.containsKey(m)) {
			addVertice(m);
		}
		addEdge(n,m);
	}
	
	public Map<E,LinkedList<E>> GetGraph() {
		return this.graph;
	}

}