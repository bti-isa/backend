package com.isa.BloodTransferInstitute.util;

import com.isa.BloodTransferInstitute.AppRunner;
import com.isa.BloodTransferInstitute.model.Contract;
import com.isa.BloodTransferInstitute.service.ContractService;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NewThread extends Thread {

	private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);
	private final ContractService contractService;

	public void run() {
		while (true) {
			try {
				final CompletableFuture<List<Contract>> contracts = contractService.deliverBlood();
				if (contracts != null) {
					final var factory = new ConnectionFactory();
					factory.setHost("localhost");

					final var connection = factory.newConnection();
					final var channel = connection.createChannel();

					channel.queueDeclare("monthly", false, false, false, null);
					var message = new StringBuilder();
					for (Contract c: contracts.get()) {
						message.append("\n" + "Blood type: ").append(c.getBloodUnit().getBloodType()).append(" ").append("Quantity: ").append(c.getQuantity());
					}
					final byte[] body = message.toString().getBytes(StandardCharsets.UTF_8);
					channel.basicPublish("", "monthly", null, body);
					logger.info("Message: " + message);
					Thread.sleep(10000); // 86 400 000 sec
				}
			} catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
					throw new RuntimeException(e);
			}
		}
	}
}
