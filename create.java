import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.Tag;


public class create {
	public void createinstance(){
		System.out.println("You chose to create an instance");
		InputStream credentialsAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("AwsCredentials.properties");
		//Preconditions.checkNotNull(credentialsAsStream, "File 'AwsCredentials.properties' NOT found in the classpath");
		AWSCredentials credentials = null;
		try {
			credentials = new PropertiesCredentials(credentialsAsStream);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace(); 
			System.out.println("invalid input");
			createinstance();
		}

		AmazonEC2 ec2 = new AmazonEC2Client(credentials);    
		ec2.setEndpoint("ec2.us-west-2.amazonaws.com");		 

		// CREATE EC2 INSTANCES
		System.out.println("Select the instance you wish to create");
		selectinstance Select = new selectinstance();
		String select = Select.instancenum();
		System.out.println("Creating instance");
		System.out.println("instane selected is "+select);
		RunInstancesRequest runInstancesRequest = new RunInstancesRequest()
			.withImageId(select)
			.withInstanceType("t1.micro")
		    .withMinCount(1)
		    .withMaxCount(1)
		  //  .withSecurityGroups("GettingStartedGroup")
		    .withKeyName("Sushwanth"); // Replace "Sushwanth" with your key name.

		RunInstancesResult runInstances = ec2.runInstances(runInstancesRequest);
		List<Instance> instances = runInstances.getReservation().getInstances();
		int idx = 1;
		for (Instance instance : instances) {
		  CreateTagsRequest createTagsRequest = new CreateTagsRequest();
		  createTagsRequest.withResources(instance.getInstanceId()) //
		      .withTags(new Tag("Name", "My Instance" + idx));
		  ec2.createTags(createTagsRequest);

		  idx++;
		  System.out.println("Congrats your ami-78de4148 instance is being created");
		}
	}
}
