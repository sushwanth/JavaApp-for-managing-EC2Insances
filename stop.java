import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.InstanceState;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesResult;


public class stop {
 public void stopinstance()
 {
	 	String instance = null;
	 	System.out.println("You choose to stop an instance");
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
		StopInstancesRequest sir = new StopInstancesRequest();
        
        ArrayList<String> instanceid = new ArrayList<>();
        
        System.out.println("Enter the Instance-id to be stopped (Ex:i-3c8b3908)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			instance = br.readLine();
			System.out.println(" instance entered is: "+instance);
			instanceid.add(instance);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
        sir.setInstanceIds(instanceid);
        String result = null;
        StopInstancesResult sirs = ec2.stopInstances(sir);
        result=sirs.getStoppingInstances().get(0).getCurrentState().getName() ;
        System.out.println("The chosen instance is in "+result+" state");
		}catch (Exception a){
			System.out.println("You have entered a wrong instance-id");
		}
 }
}
