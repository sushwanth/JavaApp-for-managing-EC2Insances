import java.util.InputMismatchException;
import java.util.Scanner;


public class selectinstance {
	public static String instancenum(){
		String instanceid = null;
		int option = 0;
		System.out.println("1. Red Hat Enterprise Linux 6.4 (64-bit) 	- ami-b8a63b88");
		System.out.println("2. Ubuntu Server 13.10 (64-bit)			- ami-ace67f9c ");
		System.out.println("3. Microsoft Windows Server 2008 R2 Base 	- ami-54de4164");
		try{
			Scanner in = new Scanner(System.in);
			option = in.nextInt();
		}catch(InputMismatchException e) {
		}
		if (option == 1)
		{
			instanceid = "ami-b8a63b88";
			return instanceid;
		}
		else if (option == 2)
		{
			instanceid = "ami-ace67f9c";
			return instanceid;
		}
		else if (option == 3)
		{
			instanceid = "ami-54de4164";
			return instanceid;
		}
		else
		{
			System.out.println("Enter the valid option only");
			instancenum();
		}
		return instanceid;
		
	}
}
