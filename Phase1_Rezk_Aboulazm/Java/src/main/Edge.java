package main;
/*
 * Edge class
 * 
 * Basic Object to hold target node and weight as int
 */
public class Edge {
	
	 int target, weight;	

    public Edge(int target, int weight) {
    	
    	this.target = target;
    	this.weight = weight;
	
    }  
    
   public int getTarget() { 
    	return target; 
	}
   public int getWeight() {
	   return weight;
   }
}
