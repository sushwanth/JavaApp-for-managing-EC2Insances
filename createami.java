import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateImageRequest;
import com.amazonaws.services.ec2.model.CreateImageResult;
import com.amazonaws.services.ec2.model.RegisterImageRequest;
import com.amazonaws.services.ec2.model.RegisterImageResult;


public class createami {
	public void ami()
	{
		Boolean noReboot = true; // set this to false if you want to shutdown the instance and tehn create an AMI.
		String instanceId = null;
		String aminame = null;
		
		System.out.println("You choose to Create an AMI");
		
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
		
		System.out.println("Kindly enter the Instance-id for which you would ike to crete an AMI");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			instanceId = br.readLine();
			System.out.println(" instance entered is: "+instanceId);
			//instanceid.add(instanceId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Enter the name of the AMI");
		BufferedReader name = new BufferedReader(new InputStreamReader(System.in));
		try {
			aminame = name.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CreateImageRequest AMI = new CreateImageRequest()
					.withName(aminame)
					.withDescription("test creation using java api")
					//.withRootDeviceName("/dev/sda1")
					.withNoReboot(noReboot)
					.withInstanceId(instanceId);
		try{
		ec2.createImage(AMI);
		CreateImageResult Ami = new CreateImageResult();
		String Imgid = Ami.getImageId();
		int Hscode = Ami.hashCode();
		Imgid = Ami.getImageId();
		System.out.println("Ami is being created ");
		//System.out.println("Last line "+Ami.getImageId()+"hashcode "+Hscode);
		}catch(Exception a){
			System.out.println("Wait !! you have given a Wrong input");
		}
		
	}
}
