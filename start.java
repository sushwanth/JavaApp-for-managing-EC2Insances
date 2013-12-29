import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesResult;


public class start {
	public void startinstance(){
		String instance = null;
		String result = null;
		System.out.println("You choose to start an instance");
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
		ec2.setEndpoint("ec2.us-west-2.amazonaws.com");// This is the location of your EC2 instance 
		StartInstancesRequest sir = new StartInstancesRequest(); 
		
        ArrayList<String> instanceid = new ArrayList<>();
        
        try{
        	System.out.println("Enter the Instance-id to be started (Ex:i-3c8b3908)");
        
        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			instance = br.readLine();
			System.out.println(" instance entered is: "+instance);
			instanceid.add(instance);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("wrong input ");
		}
		
        try{
        	sir.setInstanceIds(instanceid);
        	StartInstancesResult sirs = ec2.startInstances(sir);

        	result = sirs.getStartingInstances().get(0).getCurrentState().getName();
        	System.out.println("Instance has been started and is in "+result +" state");
        }catch (Exception a){
        	System.out.println("You have entered a wrong instance-id");
        }
	}
}
