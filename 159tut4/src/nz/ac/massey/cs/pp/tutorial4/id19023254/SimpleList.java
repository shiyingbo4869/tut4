package nz.ac.massey.cs.pp.tutorial4.id19023254;

public interface SimpleList {
	/**
	 * Add an element to the list.
	 * @param element the element to be added
	 */
	public void add(String element);
	
	/**
	 * Remove an element from the list
	 * @param element the element to be removed
	 * @throws ElementNotFoundException if element is not is the list
	 */
	public void remove(String element) throws ElementNotFoundException;
	
	/**
	 * Get the number of elements in the list. 
	 * @return the size of the list
	 */
	public int getSize() ;
	
	/**
	 * Indicates whether the list contains an element.
	 * @param element
	 * @return
	 */
	public boolean contains(String element) ;
}
