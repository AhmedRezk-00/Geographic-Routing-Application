package app;

/*
 * FindNearestNode Class
 *
 * First determine closest longitude via binary search, then calculate distance of neighbouring nodes to find the minimum distance.
 */
public class FindNearestNode {
	/*
	 * Method to find the nearest node
	 * starting from the closest longitude value,
	 * build a triangle with long. and lat. values and compare the hypothenuse distance to the given node
	 *
	 */
	public static double nearestIndex;
	public static double nearestLon;
	public static double nearestLat;


	public double findNearest(double longitude, double latitude) {

		nearestIndex = ReadFile.numberOfNodes;

		double currentClosestDistance = Double.MAX_VALUE;

		/*
		 * search for closest node according to longitude
		 */
		binarySearch(ReadFile.nodeList, 0, ReadFile.numberOfNodes-1, longitude);

		/*
		 * move to right side until distance gets to large
		 */
		for(int i = (int) currentIndex; i < ReadFile.numberOfNodes; i++) {
			/*
			 * calculate distance for each node
			 */
			double distance = Math.sqrt((Math.pow((longitude - ReadFile.nodeList[1][i]), 2) + Math.pow(latitude - ReadFile.nodeList[0][i], 2)));

			/*
			 * assign minimum distance and save index <-- bug here TODO: fix this shit
			 */
			if(distance < currentClosestDistance) {

				nearestIndex = i;
				currentClosestDistance = distance;

			}
		}
		/*
		 * move to left side until distance gets to large
		 */
		for(int i = (int) currentIndex; i >= 0; i--) {
			/*
			 * calculate distance for each node
			 */
			double distance = Math.sqrt((Math.pow((longitude - ReadFile.nodeList[1][i]), 2) + Math.pow(latitude - ReadFile.nodeList[0][i], 2)));

			/*
			 * assign minimum distance and save index
			 */
			if(distance < currentClosestDistance) {
				nearestIndex = i;
				currentClosestDistance = distance;
			}
		}


		nearestLon = ReadFile.nodeList[1][(int)nearestIndex];
		nearestLat = ReadFile.nodeList[0][(int)nearestIndex];

		return nearestIndex;
	}
	/*
	 * index of currently closest node
	 */
	private double currentIndex;

	/*
	 * splitting nodeList in subarrays untill size of 1 is reached
	 */
	private double binarySearch(double nodeList[][], int left, int right, double longitude)
	{
		if (right >= left) {
			int mid = left + (right - left) / 2;
			/*
			 * if searched longitude is exactly the middle el. return it
			 */
			if (nodeList[1][mid] == longitude) {
				currentIndex = nodeList[2][mid];
				return mid;
			}
			/*
			 * reassign value of currently closest node each split
			 */
			if (nodeList[1][mid] > longitude) {

				currentIndex = nodeList[2][mid];
				return binarySearch(nodeList, left, mid - 1, longitude);
			}

			currentIndex = nodeList[2][mid];
			if(mid != nodeList.length-1) {
				return binarySearch(nodeList, mid + 1, right, longitude);
			}
		}
		/*
		 * return index of closest node
		 */
		return currentIndex;
	}


}