package test;

import java.util.ArrayList;

public class List {
	public static void main(String[] args) throws Exception { 
		while (true) { 
			List list = new ArrayList(); 
			for (int i=0;i<1000000;i++) {
				list.add(new Object()); 
				} // break between, allows JVM to clean up (GC !) Thread.sleep(100); } } 
		}
	}
}

def sumrange(f:Int => Int,a :Int,bt)