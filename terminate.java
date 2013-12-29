import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;


public class terminate {
	public void terminateinstance(){
		String instance = null;
		System.out.println("You chose to terminate an instance");
		InputStream credentialsAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("AwsCredentials.properties");
		//Preconditions.checkNotNull(credentialsAsStream, "File 'AwsCredentials.properties' NOT found in the classpath");
		AWSCredentials credentials = null;
		try {
			credentials = new PropertiesCredentials(credentialsAsStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); System.out.println("Caught");
		}
		AmazonEC2 ec2 = new AmazonEC2Client(credentials);    
		ec2.setEndpoint("ec2.us-west-2.amazonaws.com");
		TerminateInstancesRequest sir = new TerminateInstancesRequest();
		
		ArrayList<String> instanceIds = new ArrayList<>();

		System.out.println("Enter the instance id to be terminated (Ex:i-3c8b3908)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			instance = br.readLine();
			System.out.println(" instance entered is: "+instance);
			try{
				instanceIds.add(instance);
			}catch (Exception e){
				System.out.println("you have entered a wrong instance id");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
		sir.setInstanceIds(instanceIds);
		ec2.terminateInstances(sir); System.out.println("termianting reqstd.");
		System.out.println("The requested instance " + instance +" has been terminated");
		}catch (Exception a){
			System.out.println("You have entered a wrong instance-id");
		}
		//Collection<String> instanceIds = null;
		//sir.setInstanceIds(instanceIds);
	}
}
