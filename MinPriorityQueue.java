import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A data structure that allows to add items with certain priorities. 
 * Be able to extract the item with the lowest priority in O(1) time.
 * @author Jiayin Li
 * @email  jiayinli007@brandeis.edu
 * COSI 21A -- PA3
 */

public class MinPriorityQueue {
	
	public static int defaultCapacity = 10;
	
	public GraphNode[] heapArray;
	public HashMap hashMap;
	
	// Number of valid elements in the heap
	public int heapSize;
	// The maximum capacity in the heapArray
	public int capacity;
	
	
	/**
	 * Default MinPriorityQueue constructor
	 */
	public MinPriorityQueue() {
		this(defaultCapacity);
	}
	
	
	/**
	 * Constructor for the HashMap with given capacity
	 */
	public MinPriorityQueue(int capacity) {
		// Index from 1
		this.heapArray = new GraphNode[capacity + 1];
		this.capacity = capacity + 1;
		this.heapSize = 0;
		hashMap = new HashMap();	
	}
	
	
	/**
	 * Inserts the given Node into the queue with its priority
	 * @param g
	 */
	public void insert(GraphNode g) {
		if (g == null) {
            throw new NullPointerException();
		}
		
		if (heapSize >= this.capacity - 1) {
			resize();
		}

		heapSize++;
		heapArray[heapSize] = g;
		
		hashMap.set(g, heapSize);
		Heapify_Up(heapSize);
		
		
		if (!isMinHeap()) {
			throw new IllegalStateException("Min-Heap Property is destoryed");
		}
		
	}
	
	
	/**
	 * Calls the heapify method to restore the heap-like property of the PQ at GraphNode g
	 */
	public void rebalance(GraphNode g) {
		int index = hashMap.getValue(g);
		int parentIdx = getParent(index);
		
		// If the current Node's priority is greater than its parent, a Heapify_Down operation is needed
		if (heapArray[index].priority > heapArray[parentIdx].priority) {
			Heapify_Down(index);
		} else {
			// Otherwise, if the current Node's priority is less than its parent, in Min-Heap a Heapify_Up operation is needed
			Heapify_Up(index);	
		}
	}
	
	
	/**
	 * Resizes the heapArray if there is a need, enlarge the size of heapArray by the factor of 2
	 */
	private void resize() {
		GraphNode[] newHeapArray = new GraphNode[this.capacity * 2];
		
		for (int i = 1; i < newHeapArray.length; i++) {
			if(this.heapArray[i] != null) {
				newHeapArray[i]  = this.heapArray[i];
			}
		}
		
		this.heapArray = newHeapArray;
	}
	
	
	/**
	 * Returns and removes from the priority queue the GraphNode with the highest priority in the queue
	 * The same function as Extract-Min
	 * @return the GraphNode with the highest priority in the queue
	 */
	public GraphNode pullHighestPriorityElement() {
		if (heapSize == 0) {
			throw new NoSuchElementException("Heap UnderFlow");
		}
		GraphNode minResult = heapArray[1];
		// Sets the value of the minResult to -1, meaning deletion
		hashMap.set(minResult, -1);
		heapArray[1] = heapArray[heapSize];
		//heapArray[heapSize] = null;
		// Decrease the size of the heap by 1 element
		heapSize--;
		hashMap.set(heapArray[1], 1);
		// Call Heapify_Down on the new root, on a heap of size n-1;
		Heapify_Down(1);
		
		if (!isMinHeap()) {
			throw new IllegalStateException("Min-Heap Property is destoryed");
		}

		return minResult;
		 
	}
	
	
	/**
	 * Heapify to maintain the heap property
	 * @param index the given index within the heapArray needs to be heapified down
	 */
	private void Heapify_Down(int index) {

		int left = getLeftChild(index);
		int right = getRightChild(index);
		int smallest;
			
		if (left <= heapSize && heapArray[left].priority < heapArray[index].priority) {
			smallest = left;
		} else {
			smallest = index;
		}
		
		if (right <= heapSize && heapArray[right].priority < heapArray[smallest].priority) {
			smallest = right;
		}
		
		if (smallest != index) {
			swap(index, smallest);
			Heapify_Down(smallest);
		}
	}
	
	
	/**
	 * Heapify to maintain the heap property
	 * @param index the given index within the heapArray needs to be heapified up
	 */
	private void Heapify_Up(int index) {
		while (index > 1 && heapArray[index].priority < heapArray[getParent(index)].priority) {
			swap(index, getParent(index));
			index = getParent(index);
		}
	}
	
	
	/**
	 * Returns the parent index of the given index i
	 * @param i the given index i 
	 * @return the parent index of i
	 */
	private int getParent(int i) {
		return i /2;	
	}
	
	
	/**
	 * Returns the left child index of the given index i
	 * @param i the given index i 
	 * @return the left child index of i
	 */
	private int getLeftChild(int i) {
		return 2 * i;
	}
	
	
	/**
	 * Returns the left child index of the given index i
	 * @param i the given index i 
	 * @return the left child index of i
	 */
	private int getRightChild(int i) {
		return 2 * i + 1;
	}
	
	
	/**
	 * Returns whether the MinPriorityQueue is empty
	 * @return true if the MinPriorityQueue is empty. Otherwise, returns false
	 */
	public boolean isEmpty() {
		return heapSize ==0 ;
	}
	
	
	/**
	 * Swap GraphNode at index i with index j in the array 
	 * Swap GraphNode at index i with index j in the hashMap
	 * @param i
	 * @param j
	 */
	private void swap(int i, int j) {
		
		hashMap.set(heapArray[i], j);
		hashMap.set(heapArray[j], i);
		
		GraphNode temp = heapArray[i];
		heapArray[i] = heapArray[j];
		heapArray[j] = temp;
	
	}
	
	
	/**
	 * Calls the helper method to check whether the Min-Heap maintains Heap property
	 * Definition: Min-Heap is a complete binary tree where the key of each node is greater than the keys of its children
	 * @return true if the current heap maintains the heap property. Otherwise, returns false 
	 */
	public boolean isMinHeap() {		
		return isMinHeap(1);	
	}
	
	
	/**
	 * A Helper method to check whether the current heap maintains Heap property 
	 * Mainly responsible for checking the property : The key of each node is greater than the keys of its children
	 * @param index the given index
	 * @return true if the current heap maintains the heap property. Otherwise, returns false 
	 */
	private boolean isMinHeap(int index) {
		if (index > heapSize) {
			return true;
		}
		
		int left = getLeftChild(index);
		int right = getRightChild(index);
		
		if (left <=heapSize && index > left) {
			return false;
		}
		
		if (right <=heapSize && index > right) {
			return false;
		}
		
		return isMinHeap(left) && isMinHeap(right);
	}

}
