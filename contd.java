import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class contd {
	public void cont(){
		String cont = null;
		System.out.println("Do you wish to continue ? [Y/N]");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			cont = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (cont.equals("y") || cont.equals("Y"))
		  {
			  Main.main(null);
		  }else if (cont.equals("n")||cont.equals("N")){
			  System.out.println("You choose to exit the program Bye!!");
			  System.exit(0);
		  }else{
		  
			  System.out.println("Please enter a valid option");
			  cont();
		  }	
	}
}
