package graph_diameter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
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
		int z;
		double x = 0.1;
		this.mean = new HashMap<Double, Double>();
		ArrayList<Integer> list;
		ArrayList<Integer> l;
		this.size = n;
		while (x < 1) {
			map = new HashMap<Integer, Map<Integer, Integer>>();
			y = 0;
			for (int j = 1; j <= 100; j++) {
				list = new ArrayList<Integer>();
				graph = new Graph(n, x);
				for (int i = 1; i <= n; i++) {
					map.put(i, graph.distance(i));
					z = max(map.get(i));
					list.add(z);
					if (!graph.connected()) {
						map.remove(i);
						j--;
						break;
					}

				}
				if (graph.connected()) {
					y += Collections.max(list);
				}
			}
			
			y = y/100;
			mean.put(x, y);

			x = x + 0.1;

			x = Double.parseDouble(dec.format(x).replace(',', '.'));

			/*
			 * graph = new Graph(n, x); for (int i = 1; i <= n; i++) { map.put(i,
			 * graph.distance(i)); if (!graph.connected()) { map.remove(i); break; }
			 * 
			 * } if (graph.connected()) { mean.put(x, calculateMean());
			 * 
			 * } x = x + 0.1;
			 * 
			 * x = Double.parseDouble(dec.format(x).replace(',', '.'));
			 * 
			 */

		}
	}

	private int max(Map<Integer, Integer> map) {

		return Collections.max(map.values());
	}

	public Map<Double, Double> getMean() {
		return this.mean;
	}

	public int getSize() {
		return this.size;
	}

	public static void main(String[] args) {
		MeanDiameter a = new MeanDiameter(100);
		final Object[][] table = new String[10][];
		table[0] = new String[] { "Probability", "Vertices", "Mean diameter" };
		// a.getMean().forEach((key, value) -> System.out
		// .println("The mean diameter when p = " + key + " with " + a.getSize() + "
		// vertices is " + value + "."));
		a.getMean().forEach((key, value) -> table[(int) (key * 10)] = new String[] { Double.toString(key),
				String.valueOf(a.getSize()), Double.toString(value) });
		for (final Object[] row : table) {
			System.out.format("%-13s%-11s%-20s\n", row);
		}
	}
}