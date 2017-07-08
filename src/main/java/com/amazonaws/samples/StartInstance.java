package com.amazonaws.samples;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DryRunResult;
import com.amazonaws.services.ec2.model.DryRunSupportedRequest;
import com.amazonaws.services.ec2.model.StartInstancesRequest;

/**
 * Responsavel por inicializar uma instancia na Amazon
 * @author charlys
 *
 */
public class StartInstance {

	private static void startInstance(String instance_id) {
		final AmazonEC2 ec2 = AwsConfig.getAmazonEC2();

		DryRunSupportedRequest<StartInstancesRequest> dry_request = () -> {
			StartInstancesRequest request = new StartInstancesRequest()
					.withInstanceIds(instance_id);

			return request.getDryRunRequest();
		};

		DryRunResult dry_response = ec2.dryRun(dry_request);

		if (!dry_response.isSuccessful()) {
			System.out.printf("Falha ao iniciar a instancia %s", instance_id);

			throw dry_response.getDryRunResponse();
		}

		StartInstancesRequest request = new StartInstancesRequest()
				.withInstanceIds(instance_id);

		ec2.startInstances(request);

		System.out.printf("Instancia inicializada com Sucesso %s", instance_id);
	}
	
	public static void main(String[] args) {
		startInstance(AwsConstantes.NOVA_INSTANCIA_ID);
	}

}
