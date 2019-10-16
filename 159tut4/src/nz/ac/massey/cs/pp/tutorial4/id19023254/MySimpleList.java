package nz.ac.massey.cs.pp.tutorial4.id19023254;

import nz.ac.massey.cs.pp.tutorial4.ElementNotFoundException;
//import nz.ac.massey.cs.pp.tutorial4.SimpleList;

/**
 * This is the class template you must implement. This means to replace all TODOs 
 * by meaningful code until all tests in MySimpleTests succeed. 
 * RULES:
 * 1. are not allowed to change the definition of the single instance variable
 * 2. you are not allowed to add instance variables
 */
public class MySimpleList implements SimpleList{
	
	// do not change the next line!! 
	private String[] content = new String[5];
	// do not add more instance variables

	@Override
	public void add(String element) {
		for(int i = 0; i< content.length;i++){
			if(content[i]==null){
				content[i]=element;
				break;
			}
			if(i==content.length-1){
				String[] content_copy = new String[content.length+1];
				System.arraycopy(content,0,content_copy,0,content.length);
				content_copy[i+1]=element;
				content=content_copy;
				break;
			}
		}
	}

	@Override
	public void remove(String element) throws ElementNotFoundException {
		for(int i =0 ; i<content.length;i++){
			if(content[i]==element){
				while (i<content.length-1){
					content[i]=content[i+1];
					i++;
				}
				content[content.length-1]=null;
			}
		}
	}

	@Override
	public int getSize() {
		for(int i = 0;i<content.length;i++){
			if (content[i]==null){
				return i;
			}
		}
		return content.length;
	}

	@Override
	public boolean contains(String element) {
		for(int i = 0;i<content.length;i++){
			if(content[i]==element){
				return true;
			}
		}
		return false;
	}
}
