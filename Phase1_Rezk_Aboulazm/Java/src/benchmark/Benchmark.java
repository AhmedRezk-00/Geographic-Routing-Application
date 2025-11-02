package benchmark;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import main.AdjListNode;
import main.Dijkstra;
import main.FindNearestNode;
import main.QuickSort;
import main.ReadFile;
/*
 * Benchmark class
 *
 * acts as main
 */
public class Benchmark {

	public static void main(String[] args) throws IOException {
		// read parameters (parameters are expected in exactly this order)
		String graphPath = args[1];
		double lon = Double.parseDouble(args[3]);
		double lat = Double.parseDouble(args[5]);
		String quePath = args[7];
		int sourceNodeId = Integer.parseInt(args[9]);
		String solPath = args[11];
		// run benchmarks
		System.out.println("Reading graph file " + graphPath);
		System.out.println();
		long graphReadStart = System.currentTimeMillis();
		/*
		 *
		 * benchmark for reading map data into buffer
		 *
		 *
		 */
		ReadFile read = new ReadFile();
		read.readFile(graphPath);




		long graphReadEnd = System.currentTimeMillis();

		System.out.println("\tgraph read took " + (graphReadEnd - graphReadStart) + "ms");
		System.out.println();
		System.out.println("sorting node list...estimated time 5 seconds...");
		System.out.println();
		QuickSort quickSort = new QuickSort();
		quickSort.quickSort(ReadFile.nodeList, 0, ReadFile.numberOfNodes-1);

		System.out.println("Finding closest node to coordinates " + lon + " " + lat);
		System.out.println();
		long nodeFindStart = System.currentTimeMillis();
		/*
		 *
		 * benchmark to find closest node given longitude and latitude
		 *
		 *
		 *
		 *
		 */
		FindNearestNode find = new FindNearestNode();
		find.findNearest(lon, lat);
		System.out.println("\tNearest node is: " + (int) find.nearestIndex);
		System.out.println();

		long nodeFindEnd = System.currentTimeMillis();
		System.out.println("\tfinding node took " + (nodeFindEnd - nodeFindStart) + "ms");
		System.out.println();
		/*
		 * clear unused memory
		 */
		read = null;
		quickSort = null;
		find = null;
		System.gc();




		System.out.println("Running one-to-one Dijkstras for queries in .que file " + quePath);
		System.out.println();
		/*
		 * read sol file to check correctness
		 */
		BufferedReader sol = new BufferedReader(new FileReader(solPath));
		long queStart = System.currentTimeMillis();

		/*
		 * first build Adjacency list, then clear edgeList out of memory as it is no longer needed
		 */
		System.out.println("Building Adjacency List...");
		System.out.println();
		Dijkstra dijkstra = new Dijkstra();
		ArrayList<ArrayList<AdjListNode>> adjList = dijkstra.buildAdjList();

		ReadFile.edgeList = null;
		System.gc();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(quePath))) {

			String currLine;
			while ((currLine = bufferedReader.readLine()) != null) {

				int solution = Integer.parseInt(sol.readLine());
				int oneToOneSourceNodeId = Integer.parseInt(currLine.substring(0, currLine.indexOf(" ")));
				int oneToOneTargetNodeId = Integer.parseInt(currLine.substring(currLine.indexOf(" ") + 1));


				/*
				 * calculate distances for one to one dijkstra
				 *
				 */
				dijkstra.oneToOne(adjList, oneToOneSourceNodeId, oneToOneTargetNodeId);
				int distance = dijkstra.getDistance(oneToOneTargetNodeId);
				System.out.println("Distance: "+ distance+ " - solution = " + (distance - solution));

			}
		} catch (Exception e) {
			System.out.println("Exception...");
			e.printStackTrace();
		}
		sol.close();

		long queEnd = System.currentTimeMillis();
		System.out.println("\tprocessing .que file took " + (queEnd - queStart) + "ms");
		System.out.println();


		System.out.println("Computing one-to-all Dijkstra from node id " + sourceNodeId);
		System.out.println();
		long oneToAllStart = System.currentTimeMillis();
		/*
		 * benchmark for one to all dijkstra
		 *
		 */

		dijkstra.oneToAll(adjList, sourceNodeId);

		long oneToAllEnd = System.currentTimeMillis();
		System.out.println("\tone-to-all Dijkstra took " + (oneToAllEnd - oneToAllStart) + "ms");
		System.out.println();

		// ask user for a target node id
		System.out.print("Enter target node id... ");
		@SuppressWarnings("resource")
		int targetNodeId = (new Scanner(System.in)).nextInt();
		/*
		 * get distance from source to target node with one  to all Dijkstra
		 *
		 */
		int oneToAllDistance = dijkstra.getDistance(targetNodeId);

		System.out.println("Distance from " + sourceNodeId + " to " + targetNodeId + " is " + oneToAllDistance);

	}

}