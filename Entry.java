
/**
 * The Entry class has a key -- GraphNode
 *                     a value -- the corresponding index of the graph node in the heap array
 * @author Jiayin Li
 * @email  jiayinli007@brandeis.edu
 * COSI 21A
 */
public class Entry {
	public GraphNode key;
	public int value;
	
	/**
	 * Constructor for the Entry class
	 * @param key
	 * @param value
	 */
	public Entry(GraphNode key, int value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Returns the key field of the Entry object
	 * @return the key field of the Entry object
	 */
	public GraphNode getKey() {
		return this.key;
	}
	
	
	/**
	 * Returns the value field of the Entry object
	 * @return the value field of the Entry object
	 */
	public int getValue() {
		return this.value;
	}
	
	
	/**
	 * Sets the key field for the Entry object
	 * @param the given key 
	 */
	public void setKey(GraphNode key) {
		this.key = key;
	}
	
	
	/**
	 * Sets the value field for the Entry object
	 * @param the given value 
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	
	/**
	 * Returns the string representation of the Entry object
	 */
	public String toString() {
		return "Key is : " + key + ", Value is : " + value;
	}
}
