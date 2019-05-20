package graph_diameter;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class MeanDiameter {
	private Graph graph;
	private Map<Integer, Map<Integer, Integer>> map;
	private Map<Double, Double> mean;
	private int size;

	public MeanDiameter(int n) {
		DecimalFormat dec = new DecimalFormat("#0.0");
		double y;
		double x = 0.1;
		this.map = new HashMap<Integer, Map<Integer, Integer>>();
		this.mean = new HashMap<Double, Double>();
		while (x < 1) {
			y = 0;
			for (int j = 1; j <= 100; j++) {
				this.graph = new Graph(n, x);
				for (int i = 1; i <= n; i++) {
					map.put(i, graph.distance(i));
					if (!graph.connected()) {
						map.remove(i);
						j -= 1;
						break;
					}

				}

				y += calculateMean();
			}
			y = y/100;

			mean.put(x, y);
			System.out.println(x);
			x = x + 0.1;

			x = Double.parseDouble(dec.format(x).replace(',', '.'));
		}
	}

	private double calculateMean() {
		double x = 0;
		for (int j = 1; j <= graph.size(); j++) {
			for (int i = j; i <= graph.size(); i++) {
				if (i != j) {
					x += map.get(j).get(i);
				}
			}
		}
		this.size = graph.size();

		return 2 * x / (graph.size() * (graph.size() - 1));
	}

	public Map<Double, Double> getMean() {
		return this.mean;
	}

	public int getSize() {
		return this.size;
	}

	public static void main(String[] args) {
		MeanDiameter a = new MeanDiameter(100);
		a.getMean().forEach((key, value) -> System.out
				.println("The mean diameter when p = " + key + " with " + a.getSize() + " vertices is " + value + "."));
	}
}