package com.amazonaws.samples;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;

/**
 * Responsavel por listar as instancias criadas.
 * @author charlys
 *
 */
public class ListInstance {

	private static List<String> getAllInstance() {
		List<String> listInstances = new ArrayList<String>();

		final AmazonEC2 ec2 = AwsConfig.getAmazonEC2();

		DescribeInstancesResult describeInstancesRequest = ec2.describeInstances();
		List<Reservation> reservations = describeInstancesRequest.getReservations();
		for (Reservation reservation : reservations) {
			List<Instance> instancias = reservation.getInstances();
			for (Instance instance : instancias) {
				listInstances.add(instance.getInstanceId() + "\n");
			}
		}
		return listInstances;
	}

	public static void main(String[] args) {
		System.out.println(getAllInstance());
	}

}
