package com.amazonaws.samples;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;

/**
 * Responsavel pela configuracao dos acessos e regionalizaçao
 * @author charlys
 *
 */
public class AwsConfig {

	public static AmazonEC2 getAmazonEC2() {
		AWSCredentialsProvider provider;
		if (AwsConstantes.accessKey != null && AwsConstantes.secretKey != null) {
			AWSCredentials credentials = new BasicAWSCredentials(AwsConstantes.accessKey, AwsConstantes.secretKey);
			provider = new StaticCredentialsProvider(credentials);
		} else {
			provider = new DefaultAWSCredentialsProviderChain();
		}

		return AmazonEC2ClientBuilder.standard().withCredentials(provider).withRegion(Regions.US_EAST_2).build();
	}

}
