/**
 * HashMap is a data structure that allows to add items 
 * and test if they are elements within the heap
 * @author Jiayin Li
 * @email  jiayinli007@brandeis.edu
 * COSI 21A -- PA3
 */
public class HashMap {
	
	private static final int defaultCapacity = 10;
	
	private Entry[] hashTable;
	// Size of the hashTable
	private int tableSize;
	// Number of valid key-value pairs in the table
	private int entriesSize;
	
	
	/**
	 * Default HashMap constructor
	 */
	public HashMap() {
		this(defaultCapacity);
	}
	
	
	/**
	 * Constructor for the HashMap with a given capacity
	 */
	public HashMap(int capacity) {
		tableSize = capacity;
		hashTable = new Entry[capacity];
		entriesSize = 0;
		tableSize = capacity;
	}
 	
	
	/**
	 * This method checks the HashMap to see if there is an entry for the key (GraphNode) 
	 * If there is, updates its corresponding value to the given 'value'
	 * Otherwise, add the given pair into the HashMap
	 * Using linear Probing 
	 * @param key
	 * @param value
	 */
	public void set(GraphNode key, int value) {
		if (key == null) {
			throw new IllegalArgumentException("The input key is invalid");
		}
		
		double loadFactor = 1.0 * this.entriesSize / this.tableSize;
		
		if (loadFactor >= 0.5) {
			rehash(2*tableSize);
		}
				
		int i;
		for(i = hashCode(key); this.hashTable[i]!=null && this.hashTable[i].getKey()!=null; i = (i+1) % tableSize) {
			// if the key is already exists, then updates the corresponding value
			if (this.hashTable[i].getKey().getId().equals(key.getId())) {
				this.hashTable[i].setValue(value);
				return;
			}
		}
		
		Entry newEntry = new Entry(key, value);
		this.hashTable[i] = newEntry;
		
		entriesSize++;
	}
	
	
	/**
	 *  the HashMap based on the given capacity 
	 * @param capacity
	 */
	private void rehash(int newCapacity) {
		HashMap newHashMap = new HashMap(newCapacity);
			
		for (int i = 0; i < tableSize; i++) {
			if (this.hashTable[i] != null) {
				newHashMap.set(hashTable[i].getKey(), hashTable[i].getValue());
			}
		}
		
		this.tableSize = newCapacity;
		this.hashTable = newHashMap.hashTable;
		
	}
	
	
	/**
	 * Returns the value for the entry with g as the key
	 * @return the value for the entry with g as the key, returns -1 if the key is not in the HashMap
	 */
	public int getValue(GraphNode g) {
		if (g == null) {
			throw new IllegalArgumentException("The input key is invalid");
		}
		
		int initialHashCode = hashCode(g);
		int keyToValue = -1; 
		for (int i = initialHashCode; this.hashTable[i]!=null && this.hashTable[i].getKey()!=null; i = (i + 1) % tableSize) {
			if (this.hashTable[i].getKey().getId().equals(g.getId())) {
				keyToValue = hashTable[i].getValue();
			}
		}
		
		return keyToValue;
	}
	
	
	/**
	 * Returns the hashCode for the key(GraphNode) based on its given UUID
	 * @return the hashCode for the key(GraphNode) based on its given UUID
	 */
	private int hashCode(GraphNode key) {	
		int result = 0;
		// Delete all "-" character, as the dashes will always be in the same place
		// No contribution to the hashCode
		String target = key.getId().replace("-","");
		
		// Since the UUID is quite long 
		// Using only the characters at the even-index 
		for (int i = 0; i < target.length(); i= i+2) {
			result = 37 * result + target.charAt(i);
		}
		
		result %= tableSize;		
		if (result < 0) {
			result += tableSize;
		}
		return result;
	}
	
	
	/**
	 * Returns true if the HashMap already has that key
	 * @param g
	 * @return true if the HashMap already has that key. Otherwise, returns false
	 */
	public boolean hasKey(GraphNode g) {
		if (g == null) {
			throw new IllegalArgumentException("The input key is invalid");
		}
		
		int initialHashCode = hashCode(g);
		int keyToValue = -1; 
		for (int i = initialHashCode; this.hashTable[i]!=null && this.hashTable[i].getKey()!=null; i = (i + 1) % tableSize) {
			if (this.hashTable[i].getKey().getId().equals(g.getId())) {
				keyToValue = hashTable[i].getValue();
			}
		}
		
		return keyToValue != -1;		
	}
	

	/**
	 * Returns the number of key-value pairs in the table
	 * @return the number of key-value pairs in the table
	 */
	public int size() {
		return entriesSize;
	}
	
	public int tableSize() {
		return tableSize;
	}
}
