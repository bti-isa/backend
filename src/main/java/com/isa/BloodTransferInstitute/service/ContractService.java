package com.isa.BloodTransferInstitute.service;

import com.isa.BloodTransferInstitute.model.Contract;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ContractService {

	CompletableFuture<List<Contract>> deliverBlood();
}
