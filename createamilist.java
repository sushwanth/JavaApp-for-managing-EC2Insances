import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.Image;


public class createamilist {
public void amilist(){
	InputStream credentialsAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("AwsCredentials.properties");
	//Preconditions.checkNotNull(credentialsAsStream, "File 'AwsCredentials.properties' NOT found in the classpath");
	AWSCredentials credentials = null;
	try {
		credentials = new PropertiesCredentials(credentialsAsStream);
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace(); System.out.println("Caught");
	}
	AmazonEC2Client EC2 = new AmazonEC2Client(credentials);
	EC2.setEndpoint("ec2.us-west-2.amazonaws.com");
	
	DescribeImagesRequest request = new DescribeImagesRequest();
	
	request.withOwners("self");
	System.out.println("Wait while we fetch the list of your AMIs");
	Collection<Image> images = EC2.describeImages(request).getImages();
	for (Image tmp:images) {
        String id = tmp.getImageId();
        String name = tmp.getName();
        String hyp = tmp.getHypervisor();
		System.out.println("Ami-id: "+id+" || Hypervisor: "+hyp+" || Name: "+name);
    }
	
}
}
