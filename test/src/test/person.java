package test;

public class Person {
	public Person(String id) {
		super();
		this.id=id;
		System.out.println("1");
				
	}
    private String id="0";
    @Override
    protected void finalize() throws Throwable{
    	System.out.println("2");
    	super.finalize();
    }
}

//public class Student extends Person{
//
//	public Student(String id) {
//		super(id);
//		System.out.println("3");
//		// TODO Auto-generated constructor stub
//	}
//
//	public Student() {
//		this("42");
//		System.out.println("4");
//	}
//    @Override
//    protected void finalize() throws Throwable{
//    	System.out.println("5");
//    	super.finalize();
//    }
//}

public class Q{
	public static void main(String[] args) throws Exception{
		new Student();
		System.gc();
		Thread.sleep(1000);
	}
}