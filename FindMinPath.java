import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Applied the Dijikstra's Algorithm to find the shortest path 
 * @author Jiayin Li
 * @email  jiayinli007@brandeis.edu
 * COSI 21A -- PA3
 */

public class FindMinPath {
	public static boolean hasFoundGoal = false;
	public static GraphNode goalNode = null;
	public static String pathToGoal = "";
	private static MinPriorityQueue minPQ;
	
	
	public static void main(String[] args) throws FileNotFoundException{
		minPQ = new MinPriorityQueue();
				
		GraphWrapper gw = new GraphWrapper(true);		
		// Get the GraphNode for home
		GraphNode myHome = gw.getHome();
		// Sets myHome.prioirty = 0 as required
		myHome.priority = 0;
		minPQ.insert(myHome);
		
		// A boolean variable indicates whether has found the GoalNode or not

		
		tryToFindShortestPath(minPQ);

	}
	
	
	
	public static GraphNode tryToFindShortestPath(MinPriorityQueue minPQ) throws FileNotFoundException {
		while (!hasFoundGoal && !minPQ.isEmpty()) {
			GraphNode curr = minPQ.pullHighestPriorityElement();
			// The case that we found the goal
			if (curr.isGoalNode()) {
				hasFoundGoal = true;
				goalNode = curr;
				// Save the path as answer into "answer.txt"
				printPathAnswer(goalNode);
				break;
			} else {
				if (curr.hasEast()) {
					continueSearch("East", curr, curr.getEast(), curr.getEastWeight());
				}
				
				if (curr.hasWest()) {
					continueSearch("West", curr, curr.getWest(), curr.getWestWeight());
					
				}
				
				if (curr.hasSouth()) {
					continueSearch("South", curr, curr.getSouth(), curr.getSouthWeight());
				}
				
				if (curr.hasNorth()) {
					continueSearch("North", curr, curr.getNorth(), curr.getNorthWeight());
					
				}
			}
		}
		
		return goalNode;
	}
	
	
	/**
	 * Applies the Dijikstra's Algorithm based on the pseudo-code listed in the note
	 * @param previousDir Direction from curr GraphNode to the neighbor GraphNode
	 * @param curr the current GraphNode
	 * @param neighbor the next GraphNode will be passed
	 * @param weight distance from curr GraphNode to the neighbor GraphNode
	 */	
	public static void continueSearch(String previousDir, GraphNode curr, GraphNode neighbor, int weight) {
		if (neighbor != null) {
			int x = curr.priority + weight;
			
			if (!minPQ.hashMap.hasKey(neighbor)) {
				neighbor.priority = x;
				neighbor.previousNode = curr;
				neighbor.previousDirection = previousDir;
				minPQ.insert(neighbor);
			} else {
				if (x < neighbor.priority) {
					neighbor.priority = x;
					minPQ.rebalance(neighbor);
					neighbor.previousNode = curr;
					neighbor.previousNode = curr;
				}
			}
			
		}
	}
	
	
 	/**
	 * Prints the path string to the "answer.txt" file
	 * @param goal The goal node
	 * @throws FileNotFoundException
	 */
	public static void printPathAnswer(GraphNode goal) throws FileNotFoundException{
		while (goal != null & goal.previousNode != null) {
			pathToGoal += goal.previousDirection;
			
			GraphNode previousNode = goal.previousNode;
		    if(previousNode.previousNode != null) {
		    	pathToGoal += "-";
		    }
			
			goal = previousNode;
		}
		
		String[] pathToGoalArray = pathToGoal.split("-");
		PrintStream output = new PrintStream("answer.txt");

		// Print in a reverse order
		for (int i = pathToGoalArray.length-1; i >=0; i--) {
			output.println(pathToGoalArray[i]);
			
		}
		
		output.close();
	}
}
