import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MinPriorityQueueTest {
	
	static MinPriorityQueue minPQ = new MinPriorityQueue();
	
	GraphNode node1 = new GraphNode("5451397c-0d6e-4d7d-985f-bd627dcd2fcc", false);
	GraphNode node2 = new GraphNode("22bea985-2d6b-4f9c-80f5-15001db7b822", false);
	GraphNode node3 = new GraphNode("555d9242-ea47-440c-b3ac-e492542171b7", false);
	GraphNode node4 = new GraphNode("3286c2a7-e17b-438d-8116-7ce1be04c1d0", false);
	GraphNode node5 = new GraphNode("080e073d-70e9-46d5-9182-08d4671f9ee6", false);

	GraphNode node6 = new GraphNode("6ec0919a-11ba-418a-b81d-2b4bc2443850", false);
	GraphNode node7 = new GraphNode("d060dbd6-49a0-4880-a986-46e13cb7738e", false);
	GraphNode node8 = new GraphNode("66271f0e-0d4c-45f5-ae9f-12d7bb76f0a1", false);
	GraphNode node9 = new GraphNode("aa08af04b2f0 8fe2b48d-4ba3-4680-a2f8", false);
	GraphNode node10 = new GraphNode("a09932c5-87cb-46e1-88b9-9cc7cc9ebf37", false);
	
	GraphNode node11 = new GraphNode("13f480ea-7811-4461-be93-d7cc3c7a86cb", false);
	GraphNode node12 = new GraphNode("ad45f53b-13a8-44fd-80e7-63eac1b6ef04", false);

	@Test
	void insertTest() {
		
		minPQ.insert(node1);
		minPQ.insert(node2);		
		minPQ.insert(node5);
		minPQ.insert(node6);		
		minPQ.insert(node10);
		minPQ.insert(node3);
		
		assertEquals(6, minPQ.heapSize);		
		assertEquals(true, minPQ.isMinHeap());
		
	}
	
	
	@Test
	void extractMinTest() {
		
		GraphNode min1 = minPQ.pullHighestPriorityElement();
		GraphNode min2 = minPQ.pullHighestPriorityElement();
		
		assertEquals(4, minPQ.heapSize);		
		assertEquals(true, minPQ.isMinHeap());
		
		GraphNode newNode1 = new GraphNode("5451397c-0d6e-4d7d-985f-bd627dcd2fcc", false);
		assertEquals(newNode1.getId(), node1.getId());
		
		GraphNode newNode2 = new GraphNode("22bea985-2d6b-4f9c-80f5-15001db7b822", false);
		assertEquals(newNode2.getId(), node2.getId());
		
	}

}
