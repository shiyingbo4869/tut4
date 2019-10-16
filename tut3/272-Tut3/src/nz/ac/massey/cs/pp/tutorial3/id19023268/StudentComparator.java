package nz.ac.massey.cs.pp.tutorial3.id19023268;

import java.util.Comparator;

class StudentComparatorById implements Comparator<Student>
{
	@Override
	public int compare(Student arg0, Student arg1) {
		// TODO Auto-generated method stub
//		return Integer.parseInt(arg0.id) - Integer.parseInt(arg1.id);
		return arg0.id.compareTo(arg1.id);
	}
}

class StudentComparatorByFirstName implements Comparator<Student>
{
	@Override
	public int compare(Student arg0, Student arg1) {
		// TODO Auto-generated method stub
		return arg0.fname.compareTo(arg1.fname);
	}
}

class StudentComparatorByName implements Comparator<Student>
{
	@Override
	public int compare(Student arg0, Student arg1) {
		// TODO Auto-generated method stub
		return arg0.name.compareTo(arg1.name);
	}
}

class StudentComparatorByProgram implements Comparator<Student>
{
	@Override
	public int compare(Student arg0, Student arg1) {
		// TODO Auto-generated method stub
		return arg0.program.compareTo(arg1.program);
	}

}

class StudentComparatorByMajor implements Comparator<Student>
{
	@Override
	public int compare(Student arg0, Student arg1) {
		// TODO Auto-generated method stub
		return arg0.major.compareTo(arg1.major);
	}

}
