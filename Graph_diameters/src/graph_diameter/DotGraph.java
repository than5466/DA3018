package graph_diameter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DotGraph<E> {
	private Map<E,LinkedList<E>> EdgeSet;
	

	public DotGraph() {
		this.EdgeSet = new HashMap<E,LinkedList<E>>();
	}
	
	private void addVertice(E n) {
		this.EdgeSet.put(n,new LinkedList<E>());
	}
	
	private void addEdge(E n,E m) {
		
		if (!this.EdgeSet.get(n).contains(m)) {
			this.EdgeSet.get(n).add(m);
		}
	}
	
	public void add(E n, E m) {
		if (!this.EdgeSet.containsKey(n)) {
			addVertice(n);
		}
		if (!this.EdgeSet.containsKey(m)) {
			addVertice(m);
		}
		addEdge(n,m);
	}
	
	public Map<E,LinkedList<E>> GetEdgeSet() {
		return this.EdgeSet;
	}

}