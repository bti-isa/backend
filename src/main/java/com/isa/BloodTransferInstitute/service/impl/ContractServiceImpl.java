package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.dto.transfer.ContractDTO;
import com.isa.BloodTransferInstitute.model.BloodUnit;
import com.isa.BloodTransferInstitute.model.Contract;
import com.isa.BloodTransferInstitute.repository.BloodUnitRepository;
import com.isa.BloodTransferInstitute.repository.ContractRepository;
import com.isa.BloodTransferInstitute.service.ContractService;

import java.io.Console;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

	private final ContractRepository contractRepository;
	private final BloodUnitRepository bloodUnitRepository;
	private final EmailSenderService emailSenderService;

	@Async
	public CompletableFuture<List<Contract>> deliverBlood() {
		List<Contract> contracts = contractRepository.findAllByDate(LocalDate.now());
		contracts = insufficientCheck(contracts);
		for (Contract c: contracts) {
			BloodUnit bloodUnit = bloodUnitRepository.findByBloodType(c.getBloodUnit().getBloodType());
			bloodUnit.setQuantity(bloodUnit.getQuantity() - c.getQuantity());
			bloodUnitRepository.save(bloodUnit);
		}
		return CompletableFuture.completedFuture(contracts);
	}

	private List<Contract> insufficientCheck(List<Contract> contracts) {
		List<Contract> passedCheckContracts = new ArrayList<>();
		for (Contract contract: contracts) {
			contract.setDate(contract.getDate().plusMonths(1));
			if(contract.canDeliver()) {
				passedCheckContracts.add(contract);
				continue;
			}
//			emailSenderService.sendSimpleEmail("psw@hospital.com", "Delivery information", "\n" +
//				"The delivery on the " + contract.getDate().toString() + " date will not be made because there are not enough blood units of type " + contract.getBloodUnit().getBloodType() + " in stock.");
		}
		contractRepository.saveAll(contracts);
		return passedCheckContracts;
	}
}
