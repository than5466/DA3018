package graph_diameter;

import java.util.HashMap;
import java.util.Map;

public class MeanDiameter {
	Graph graph;
	Map<Integer,Map<Integer,Integer>> map;
	Map<Integer,Double> mean;
	
	public MeanDiameter(int n, double p) {
		map = new HashMap<Integer,Map<Integer,Integer>>();
		mean = new HashMap<Integer,Double>();
		graph = new Graph(n,p);
		for (int i = 1; i <= n; i++) {
			map.put(i, graph.distance(i));
			if (!graph.connected()) {
				map.remove(i);
				break;
			}
			mean.put(i, calculateMean(i));
			
		}
	}
	public double calculateMean(int n) {
		double x = 0;
		for (int i = 1; i < graph.size(); i++) {
			if (i != n) {
				x += map.get(n).get(i);
			}
		}
		return x/(graph.size()-1);
	}
	
	public static void main(String[] args) {
		MeanDiameter a = new MeanDiameter(100,0.1);
		System.out.println(a.map);
		System.out.println(a.mean);
	}
}
