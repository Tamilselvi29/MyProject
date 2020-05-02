package interview;


class Employee{
	
	int Empno;
	String Empname;
	int Salary;
	
	public void display(int no, String name){
	System.out.println("Eno ="+ no);
	System.out.println("Ename = " + name);
	}
	
	public void display(int no){
		System.out.println("Eno ="+ no);
	}
	
	
}
public class methoOverloading {

	public static void main(String[] args) {
		Employee e=new Employee();
		e.display(10);
		e.display(20, "vettri");
	}
	
}
