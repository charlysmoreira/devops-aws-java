package com.amazonaws.samples;

import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.Tag;

/**
 * Responsavel por criar uma nova instancia na Amazon.
 * @author charlys
 *
 */
public class CreateInstance {
	//TODO Caso der erro com as credenciais:
	// Criar nova chave e acesso no console da Amazon
	// Copiar as chaves para windows >> preference >> AWS Toolkit e setar a chave e acesso
	// Altera a classe AwsConfig para nova chave

	public static void main(String[] args) {

		RunInstancesRequest run_request = new RunInstancesRequest()
				.withImageId(AwsConstantes.ami_id)
				.withInstanceType(InstanceType.T2Micro)
				.withMaxCount(1)
				.withMinCount(1);

		RunInstancesResult run_response = AwsConfig.getAmazonEC2()
				.runInstances(run_request);

		String instance_id = run_response.getReservation()
				.getReservationId();

		Tag tag = new Tag().withKey("Name")
				.withValue(AwsConstantes.name);

		CreateTagsRequest tag_request = new CreateTagsRequest()
				.withTags(tag);

		System.out.printf("Uma nova instancia EC2 %s baseada em AMI %s foi criada com Sucesso", 
				instance_id, AwsConstantes.ami_id);
	}
}
