import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class HashMapTest {

	@Test
	void hasKeyTest() {
		
		HashMap mp = new HashMap();
		
		GraphNode node1 = new GraphNode("5451397c-0d6e-4d7d-985f-bd627dcd2fcc", false);
		mp.set(node1, 1);
		GraphNode node2 = new GraphNode("22bea985-2d6b-4f9c-80f5-15001db7b822", false);
		mp.set(node2, 2);
		GraphNode node3 = new GraphNode("37420837-eb1e-43e8-a8f2-ac464a413195", false);

		assertEquals(true, mp.hasKey(node1));
		assertEquals(true, mp.hasKey(node2));
		assertEquals(false, mp.hasKey(node3));
	}
	
	
	@Test
	void setAndRehashTest() {
		
		HashMap mp = new HashMap();
		
		GraphNode node1 = new GraphNode("5451397c-0d6e-4d7d-985f-bd627dcd2fcc", false);
		mp.set(node1, 1);
		GraphNode node2 = new GraphNode("22bea985-2d6b-4f9c-80f5-15001db7b822", false);
		mp.set(node2, 2);
		GraphNode node3 = new GraphNode("555d9242-ea47-440c-b3ac-e492542171b7", false);
		mp.set(node3, 3);
		GraphNode node4 = new GraphNode("3286c2a7-e17b-438d-8116-7ce1be04c1d0", false);
		mp.set(node4, 4);
		GraphNode node5 = new GraphNode("080e073d-70e9-46d5-9182-08d4671f9ee6", false);
		mp.set(node5, 5);
		
		assertEquals(5, mp.size());
		
		GraphNode node6 = new GraphNode("6ec0919a-11ba-418a-b81d-2b4bc2443850", false);
		mp.set(node6, 6);
		GraphNode node7 = new GraphNode("d060dbd6-49a0-4880-a986-46e13cb7738e", false);
		mp.set(node7, 7);
		GraphNode node8 = new GraphNode("66271f0e-0d4c-45f5-ae9f-12d7bb76f0a1", false);
		mp.set(node8, 8);
		GraphNode node9 = new GraphNode("aa08af04b2f0 8fe2b48d-4ba3-4680-a2f8", false);
		mp.set(node9, 9);
		GraphNode node10 = new GraphNode("a09932c5-87cb-46e1-88b9-9cc7cc9ebf37", false);
		mp.set(node10, 10);
		
		GraphNode node11 = new GraphNode("13f480ea-7811-4461-be93-d7cc3c7a86cb", false);
		mp.set(node11, 11);
		GraphNode node12 = new GraphNode("ad45f53b-13a8-44fd-80e7-63eac1b6ef04", false);
		mp.set(node12, 12);
		

		assertEquals(12, mp.size());
	}
		
	
	@Test
	void getValueTest() {
		
		HashMap mp = new HashMap();
		
		GraphNode node1 = new GraphNode("5451397c-0d6e-4d7d-985f-bd627dcd2fcc", false);
		mp.set(node1, 1);
		GraphNode node2 = new GraphNode("22bea985-2d6b-4f9c-80f5-15001db7b822", false);
		mp.set(node2, 2);
		GraphNode node3 = new GraphNode("555d9242-ea47-440c-b3ac-e492542171b7", false);
		mp.set(node3, 3);

		mp.set(node2, 4);
		mp.set(node3, 5);
		
		GraphNode node4 = new GraphNode("3286c2a7-e17b-438d-8116-7ce1be04c1d0", false);
		
		assertEquals(1, mp.getValue(node1));
		assertEquals(4, mp.getValue(node2));
		assertEquals(5, mp.getValue(node3));
		assertEquals(-1, mp.getValue(node4));

	}
}
