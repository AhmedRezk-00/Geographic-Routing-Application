package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/*
 * ReadFile class
 *
 * Read graph data with Buffered Reader and save Nodes and Edge data into Arrays
 * Calculate offset for edge list.
 */
public class ReadFile {

	public static int numberOfNodes = 0;
	public static int numberOfEdges = 0;

	public static double[][] nodeList = null;
	public static double[][] ogNodeList = null;
	public static int[][] edgeList = null;

	public static int[] offSet = null;
	private int[] offSetTemp = null;

	/*
	 * this method reads input file and fills node- and edge list with necessary
	 * data.
	 */
	public void readFile(String file) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line = null;
		int row = 0;
		int k = 0;
		int[] compare = new int[2];
		int maxLength = 0;

		try {
			/*
			 * read file line by line
			 */
			while ((line = reader.readLine()) != null) {

				/*
				 *  skips unimportant parts of file
				 */
				if (line.contains("#")) {
					continue;
				}
				/*
				 *  splitting file into columns
				 */
				String[] columns = line.split(" ");
				/*
				 *  separate first two lines from rest of file
				 */
				if (columns.length < 2) {

					numberOfNodes = Integer.parseInt(reader.readLine());
					numberOfEdges = Integer.parseInt(reader.readLine());


					nodeList = new double[3][numberOfNodes];
					ogNodeList = new double [2][numberOfNodes];

					edgeList = new int[2][numberOfEdges];
					offSetTemp = new int[numberOfEdges];


				} else {
					/*
					 * separating nodes from edges
					 */
					if (line.contains(".")) {
						/*
						 * saving nodes in array[i][j] with i = latitude(0),
						 * longitude(1) and initial index(2)
						 */

						nodeList[0][Integer.parseInt(columns[0])] = Double.parseDouble((columns[2]));
						nodeList[1][Integer.parseInt(columns[0])] = Double.parseDouble((columns[3]));
						nodeList[2][Integer.parseInt(columns[0])] = Double.parseDouble((columns[0]));

						/*
						 * keep one unsorted nodeList
						 */
						ogNodeList[0][Integer.parseInt(columns[0])] = Double.parseDouble((columns[2]));
						ogNodeList[1][Integer.parseInt(columns[0])] = Double.parseDouble((columns[3]));


					} else {
						/*
						 * saving edges in array[i][row] with row = index and i = targetID(0), cost(1) and sourceID(2)
						 */

						edgeList[0][row] = Integer.parseInt(columns[1]);
						edgeList[1][row++] = Integer.parseInt(columns[2]);

						/*
						 * add one to every node's offSet
						 */
						compare[0] = Integer.parseInt(columns[0]);

						if(offSetTemp[k] == 0) {
							offSetTemp[k] += 1;
						}
						/*
						 * if source node equals current offSet entry plus one except for source
						 */
						if(compare[1] == Integer.parseInt(columns[0]) && row != 0) {
							offSetTemp[k] += 1;
						}

						else {
							k=Integer.parseInt(columns[0]);
						}
						/*
						 * get the previous entry
						 */
						if(compare[0] != compare[1]) {
							compare[1] = compare[0];
							compare[0] = Integer.parseInt(columns[0]);
						}
						/*
						 * calculate necessary length of offSet for memory consuption
						 */
						if(maxLength < k) {
							maxLength = k;
						}
					}
				}
			}

		} finally {
			/*
			 * assign only needed space of offSet
			 */
			offSet = new int[maxLength+1];
			for(int i = 0; i< maxLength+1; i++) {
				offSet[i] = offSetTemp[i];
			}
			/*
			 * clear unused memory
			 */
			offSetTemp = null;
			reader.close();
			reader = null;
			System.gc();
		}

	}
}
