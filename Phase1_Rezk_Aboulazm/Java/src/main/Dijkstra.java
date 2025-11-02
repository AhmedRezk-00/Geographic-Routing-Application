package main;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
 * Dijkstra Class
 *
 * For building the adjacency list as well as for one to all and one to one queries.
 *
 */
public class Dijkstra {

	/*
	 * fill Adjacency List with edges from edgeList
	 *
	 */
	int distance[] = new int[ReadFile.numberOfEdges];

	public ArrayList<ArrayList<AdjListNode>> buildAdjList() {

		ArrayList<ArrayList<AdjListNode> > adjList = new ArrayList<>();
		AdjListNode node = null;
		/*
		 * fill adjList with edges
		 * j = index of current edge
		 * k = index of current offSet
		 */
		int j = 0;
		int offSet = ReadFile.offSet[0]-1;

		for (int i = 0; i < ReadFile.offSet.length; i++) {
			ArrayList<AdjListNode> list = new ArrayList<>();

			if(i > 0) {
				offSet = ReadFile.offSet[i];
			}
			for(int k = 0; k < offSet; k++) {

				node = new AdjListNode(ReadFile.edgeList[0][j], ReadFile.edgeList[1][j]);

				list.add(node);
				j++;
			}
			adjList.add(list);
		}
		return adjList;
	}

	/*
	 * Method to get distance between source and target node
	 */
	public int getDistance(int targetNode) {
		return distance[targetNode];
	}
	/*
	 * Method for one to all queries.
	 *
	 * Takes the already build adjList to calculate shortest path from source to target.
	 *
	 */
	public List<Integer> oneToAll(ArrayList<ArrayList<AdjListNode>> adjList, int sourceNode) {
		/*
		 * set all distances to infinity except for source node
		 */
		List<Integer> path = new ArrayList<>();
		for (int i = 0; i < ReadFile.numberOfEdges; i++)   {
			distance[i] = Integer.MAX_VALUE;

		}

		distance[sourceNode] = 0;

		/*
		 * Priority Queue with binary heap implementation
		 */
		PriorityQueue<AdjListNode> pq = new PriorityQueue<>((e1, e2) -> e1.getWeight() - e2.getWeight());
		/*
		 * add sourceNode
		 */
		pq.add(new AdjListNode(sourceNode, 0));

		/*
		 * remove node from pq and change shortest path accordingly
		 */

		while (pq.size() > 0) {
			AdjListNode current = pq.poll();
			/*
			 * if new distance is smaller assign it as new shortest distance
			 */
			for (AdjListNode n : adjList.get(current.getEdge())) {


				if (distance[current.getEdge()] + n.getWeight() < distance[n.getEdge()]) {

					distance[n.getEdge()] = n.getWeight() + distance[current.getEdge()];

					if(!adjList.get(n.getEdge()).isEmpty()) {

						adjList.get(n.getEdge()).get(0).setPrev(current.getEdge());
					}
					pq.add(new AdjListNode(n.getEdge(), distance[n.getEdge()]));

				}
			}
		}
		return path;
	}
	/*
	 * Method for one to one queries.
	 *
	 * Takes the already build adjList to calculate shortest path from source to target.
	 */
	public List<Integer> oneToOne(ArrayList<ArrayList<AdjListNode>> adjList, int sourceNode, int targetNode) {

		/*
		 * set all distances to infinity except for source node
		 */
		List<Integer> path = new ArrayList<>();
		for (int i = 0; i < ReadFile.numberOfEdges; i++)   {
			distance[i] = Integer.MAX_VALUE;

		}

		distance[sourceNode] = 0;

		/*
		 * Priority Queue with binary heap implementation
		 */
		PriorityQueue<AdjListNode> pq = new PriorityQueue<>((e1, e2) -> e1.getWeight() - e2.getWeight());

		/*
		 * add sourceNode
		 */
		pq.add(new AdjListNode(sourceNode, 0));



		while (pq.size() > 0) {

			AdjListNode current = pq.poll();
			/*
			 * if target node is reached in Priority Queue, build path and end algorithm early
			 */
			if(current.getEdge() == targetNode)  {

				/*
				 * create new path
				 */
				path = new ArrayList<>();
				path.add(targetNode);

				/*
				 * build path with backtracking
				 */
				int temp = targetNode;
				while(temp != sourceNode) {

					path.add(adjList.get(temp).get(0).getPrev());
					temp = adjList.get(temp).get(0).getPrev();

				}
				return path;
			}

			/*
			 * if new distance is smaller assign it as new shortest distance
			 */
			for (AdjListNode n : adjList.get(current.getEdge())) {


				if (distance[current.getEdge()] + n.getWeight() < distance[n.getEdge()]) {

					distance[n.getEdge()] = n.getWeight() + distance[current.getEdge()];

					if(!adjList.get(n.getEdge()).isEmpty()) {

						adjList.get(n.getEdge()).get(0).setPrev(current.getEdge());
					}

					pq.add(new AdjListNode(n.getEdge(), distance[n.getEdge()]));

				}
			}
		}
		/*
		 * create new path
		 */
		path = new ArrayList<>();
		path.add(targetNode);

		/*
		 * build path with backtracking
		 */
		int temp = targetNode;
		while(temp != sourceNode) {

			path.add(adjList.get(temp).get(0).getPrev());
			temp = adjList.get(temp).get(0).getPrev();

		}
		return path;
	}
}