package app;

/*
 * AdjListNode class
 *
 * Basic Object to hold target edge and weight
 */
public class AdjListNode {

	int edge, weight;
	int prev;

	public AdjListNode(int e, int w) {

		this.edge = e;
		this.weight = w;

	}
	public int getEdge() {
		return edge;
	}

	public int getWeight() {
		return weight;
	}

	public int getPrev() {
		return prev;
	}
	public void setPrev(int prev) {
		this.prev = prev;
	}

}
