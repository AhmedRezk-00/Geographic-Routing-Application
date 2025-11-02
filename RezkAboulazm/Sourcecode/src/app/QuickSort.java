package app;
/*
 * QuickSort Class
 * 
 * In-place QuickSort with O(n log n) time complexity.
 *  
 */
public class QuickSort {

	/*
	 * Recursively calls itself till size of 1 is reached.
	 */
	public void quickSort(double[][] nodeList, int low, int high) {
	    if (low < high) {
	          
	    	/*
	         * index of pivot element
	         */
	        int pivotIndex = partition(nodeList, low, high);
	  
	        /*
	         * sort elements before and after pivot element separately
	         */
	        quickSort(nodeList, low, pivotIndex - 1);
	        quickSort(nodeList, pivotIndex + 1, high);
	    }
	}
	/*
	 * partition each sublist
	 */
	private int partition(double[][] nodeList, int low, int high){
	      
		/*
	     *  pivot element
	     */
	    double pivot = nodeList[0][high];  
	   
	    int i = (low - 1); 
	    /*
	     * if element is smaller than pivot swap it
	     */
	    for(int j = low; j <= high - 1; j++) {   
	        if (nodeList[0][j] < pivot)  {
	            	            
	            i++; 
	            swap(nodeList, i, j);
	        }
	    }
	    swap(nodeList, i + 1, high);
	    return (i + 1);
	}
	
	/*
	 * swaps two values in place
	 * swap long, lat and initial index for each node
	 */
	private void swap(double[][] nodeList, int i, int j){
	    double temp0 = nodeList[0][i];
	    nodeList[0][i] = nodeList[0][j];
	    nodeList[0][j] = temp0;
	    
	    double temp1 = nodeList[1][i];
	    nodeList[1][i] = nodeList[1][j];
	    nodeList[1][j] = temp1;
	    
	    double temp2 = nodeList[2][i];
	    nodeList[2][i] = nodeList[2][j];
	    nodeList[2][j] = temp2;
	    
	    
	}
}
