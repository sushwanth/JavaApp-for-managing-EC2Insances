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
import com.amazonaws.services.ec2.model.RebootInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesResult;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.opsworks.model.RebootInstanceRequest;


public class reboot {
	public void rb()
	{
		String instance = null;
	 	System.out.println("You choose to Reboot an instance");
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
		RebootInstancesRequest sir = new RebootInstancesRequest();
		
		ArrayList<String> instanceIds = new ArrayList<>();
        
        System.out.println("Enter the Instance-id to be Rebooted (Ex:i-3c8b3908)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			instance = br.readLine();
			System.out.println("Instance entered is: "+instance);
			instanceIds.add(instance);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
		sir.setInstanceIds(instanceIds);
		sir.withInstanceIds(instanceIds);
		sir.withInstanceIds(instance);
		ec2.rebootInstances(sir);
		System.out.println("The choosen "+instance+" is being rebooted");
		}catch (Exception a){
			System.out.println("You have entered a wrong instance-id");
		}
	}
}
