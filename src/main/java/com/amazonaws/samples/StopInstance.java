package com.amazonaws.samples;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DryRunResult;
import com.amazonaws.services.ec2.model.DryRunSupportedRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;

public class StopInstance {

	private static void stopInstance(String instance_id) {
		final AmazonEC2 ec2 = AwsConfig.getAmazonEC2();

		DryRunSupportedRequest<StopInstancesRequest> dry_request = () -> {
			StopInstancesRequest request = new StopInstancesRequest()
					.withInstanceIds(instance_id);

			return request.getDryRunRequest();
		};

		DryRunResult dry_response = ec2.dryRun(dry_request);

		if (!dry_response.isSuccessful()) {
			System.out.printf("Falha ao parar a instancia %s", instance_id);
			throw dry_response.getDryRunResponse();
		}

		StopInstancesRequest request = new StopInstancesRequest()
				.withInstanceIds(instance_id);

		ec2.stopInstances(request);

		System.out.printf("A instancia %s foi parada com Sucesso", instance_id);
	}

	public static void main(String[] args) {
		stopInstance(AwsConstantes.NOVA_INSTANCIA_ID);
	}

}
