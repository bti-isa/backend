package com.isa.BloodTransferInstitute;

import com.isa.BloodTransferInstitute.model.Contract;
import com.isa.BloodTransferInstitute.service.ContractService;
import com.isa.BloodTransferInstitute.util.NewThread;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);
	private final ContractService contractService;

	@Override
	public void run(final String... args) throws Exception {
		final NewThread thread = new NewThread(contractService);
		thread.start();
	}
}
