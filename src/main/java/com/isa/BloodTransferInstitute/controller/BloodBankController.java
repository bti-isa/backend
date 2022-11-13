package com.isa.BloodTransferInstitute.controller;

import com.isa.BloodTransferInstitute.dto.SearchDTO;
import com.isa.BloodTransferInstitute.dto.bloodbank.BloodBankDTO;
import com.isa.BloodTransferInstitute.mappers.BloodBankMapper;
import com.isa.BloodTransferInstitute.service.BloodBankService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/BloodBank")
public class BloodBankController {

	private final BloodBankService bloodBankService;

	private final BloodBankMapper bloodBankMapper;

	@PostMapping("/search")
	public ResponseEntity<List<BloodBankDTO>> search(@RequestBody final SearchDTO searchDTO) {
		final var searchResult = bloodBankMapper.ListToListDTO(bloodBankService.search(searchDTO));
		return ResponseEntity.status(HttpStatus.OK).body(searchResult);
	}
}
