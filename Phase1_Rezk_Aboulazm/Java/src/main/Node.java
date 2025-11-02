package main;
/*
 * Node class
 * 
 * Basic Object to hold node index as int and longitude as well as latitude values as doubles.
 */
public class Node {
	 int index;
	 double lo,lat;

	    public Node(int index, double lo, double lat) {
	    	
	        this.index = index;
	        this.lo = lo;
	        this.lat = lat;
		
	    }    
		public int getIndex() { 
			return index; 
		}
		
	   public double getLo() { 
	    	return lo; 
		}
	   public double getLat() {
		   return lat;
	   }
}
