import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.InstanceState;
import com.amazonaws.services.ec2.model.Reservation;


public class createlist {
	public void list (){
		//
			InputStream credentialsAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("AwsCredentials.properties");
			//Preconditions.checkNotNull(credentialsAsStream, "File 'AwsCredentials.properties' NOT found in the classpath");
			AWSCredentials credentials = null;
			try {
				credentials = new PropertiesCredentials(credentialsAsStream);
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); System.out.println("Caught");
			}
		//	
		AmazonEC2Client EC2 = new AmazonEC2Client(credentials);
		EC2.setEndpoint("ec2.us-west-2.amazonaws.com");
		DescribeInstancesResult Res = EC2.describeInstances();
		List<Reservation> L = Res.getReservations();
		for(Reservation R : L){
			
		List<com.amazonaws.services.ec2.model.Instance> Li = R.getInstances();
				for(com.amazonaws.services.ec2.model.Instance I : Li){
					InstanceState state = I.getState();
					String cutstate = state.getName();
					System.out.println("Instance : " +I.getInstanceId() +" || Image Id : "+I.getImageId()+" || State of operation : "+cutstate +" || Platform : "+I.getPlatform());
				}
		}
		}
}
