package s3service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletContext;

import org.jets3t.service.CloudFrontService;
import org.jets3t.service.CloudFrontServiceException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3Service {
	
	private static String bucketName     = "media218";
	
	public static String uploadFileToS3(String fileName,File fis,String keypath){
		AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider());
		String tempurl=null;
        try {
            System.out.println("Uploading a new object to S3 from a filename:"+fileName+"\n");
            s3client.putObject(new PutObjectRequest(
            		                 bucketName, fileName, fis));
            tempurl=getCloudFrontURL(fileName,keypath);

         } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
            		"means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
            		"means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
            ace.printStackTrace();
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Now use today date.
        c.add(Calendar.DATE, 5); // Adding 5 days
        String output = sdf.format(c.getTime());
        System.out.println(output);
        
        Date d = c.getTime();
        
        
        URL url = s3client.generatePresignedUrl(bucketName, fileName, d);
	    
		
		
		return tempurl;
		
	}
	
	public static String getCloudFrontURL(String fileName,String keypath){
		
		
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		String signedUrlCanned=null;
		String distributionDomain = "dhxo8w3un9xnm.cloudfront.net";
		String privateKeyFilePath = keypath;//"C:\\Users\\arjun\\Desktop\\Keys\\new.der";
		//String privateKeyFilePath = new File("new.der").getRelativePath();
		//String s3ObjectKey = "s3/object/key.txt";			
		
		String s3ObjectKey = fileName;
		String policyResourcePath = "http://" + distributionDomain + "/" + s3ObjectKey;

		// Convert your DER file into a byte array.

		byte[] derPrivateKey;
		try {
			Path path = Paths.get(privateKeyFilePath);
			derPrivateKey = Files.readAllBytes(path); 
			/*		ServiceUtils.readInputStreamToBytes(new
			    FileInputStream(privateKeyFilePath));*/
		

		// Generate a "canned" signed URL to allow access to a 
		// specific distribution and object

			
			try {
				signedUrlCanned = CloudFrontService.signUrlCanned(
				    "http://" + distributionDomain + "/" + s3ObjectKey, // Resource URL or Path
				    "APKAJZNK4CZSJK4TKW2Q",     // Certificate identifier, 
				                   // an active trusted signer for the distribution
				    derPrivateKey, // DER Private key data
				    DateTime.now(DateTimeZone.UTC).plusDays(50).toDate()
				    //ServiceUtils.parseIso8601Date("2011-11-14T22:20:00.000Z") // DateLessThan
				    );
			} catch (CloudFrontServiceException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			System.out.println("CloudFront URL:"+signedUrlCanned);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return signedUrlCanned;
	}

}
