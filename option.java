import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;


public class option {
	public void selectoption() throws IOException
	{
	int option = 0;
	String cont = null;
	System.out.println("Enter one of the following options:");
	System.out.println("1. Create an instance");
	System.out.println("2. Start an Instance");
	System.out.println("3. Stop an Instance");
	System.out.println("4. Reboot an Instance");
	System.out.println("5. Terminate an instance");
	System.out.println("6. List all the instances");
	System.out.println("7. Create an AMI of an Instance");
	System.out.println("8. List all the AMIs");
	System.out.println("9. Exit the application");
	try{
		Scanner in = new Scanner(System.in);
		option = in.nextInt();
	}catch(InputMismatchException e) {
		
	}
	//option = in.nextInt();
	if (option == 1)
	{
		create Create = new create();
		Create.createinstance();
	}
	else if (option == 2)
	{
		start Stop = new start();
		Stop.startinstance();
	}
	else if (option == 3)
	{
		stop Stop = new stop();
		Stop.stopinstance();
	}
	else if (option == 4)
	{
		reboot Rb = new reboot();
		Rb.rb();
	}
	else if (option == 5)
	{
		terminate Terminate = new terminate();
		Terminate.terminateinstance();
	}
	else if (option == 6)
	{
		createlist List = new createlist();
		List.list();
	}
	else if (option == 7)
	{
		createami Ami = new createami();
		Ami.ami();
	}else if (option == 8)
	{
		createamilist Amilist = new createamilist();
		Amilist.amilist();
	}
	else if (option == 9)
	{
		System.out.println("You choose to exit the API, Bye!!!");
		System.exit(0);
	}
	else 
	{
		System.out.println("Kindly enter the correct valid option");
		selectoption();
	}
	contd conti = new contd();
	conti.cont();
	}
}
