import java.io.IOException;


public class Main {
	public static void main(String[] args)
	{
		option select = new option();
		try {
			select.selectoption();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
