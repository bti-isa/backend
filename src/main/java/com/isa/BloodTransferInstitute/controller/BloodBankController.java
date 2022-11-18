package com.isa.BloodTransferInstitute.controller;

import com.isa.BloodTransferInstitute.dto.SearchDTO;
import com.isa.BloodTransferInstitute.dto.bloodbank.BloodBankDTO;
import com.isa.BloodTransferInstitute.dto.bloodbank.NewBloodBankDTO;
import com.isa.BloodTransferInstitute.dto.bloodbank.UpdateBloodBankDTO;
import com.isa.BloodTransferInstitute.dto.user.patient.NewPatientDTO;
import com.isa.BloodTransferInstitute.mappers.BloodBankMapper;
import com.isa.BloodTransferInstitute.mappers.GetBloodBankMapper;
import com.isa.BloodTransferInstitute.model.BloodBank;
import com.isa.BloodTransferInstitute.service.BloodBankService;

import java.util.List;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/BloodBank")
@CrossOrigin(origins = "*")
public class BloodBankController {

	private final BloodBankService bloodBankService;

	private final GetBloodBankMapper getBloodBankMapper;

	@PostMapping("/add")
	public ResponseEntity<BloodBankDTO> addBloodBank(@Valid @NotNull @RequestBody final NewBloodBankDTO dto) {
		final var bloodBank = bloodBankService.add(dto);
		return ResponseEntity.status(HttpStatus.OK).body(getBloodBankMapper.EntityToEntityDTO(bloodBank));
	}

	@PostMapping("/search")
	public ResponseEntity<List<BloodBankDTO>> search(@RequestBody final SearchDTO searchDTO) {
		final var searchResult = getBloodBankMapper.ListToListDTO(bloodBankService.search(searchDTO));
		return ResponseEntity.status(HttpStatus.OK).body(searchResult);
	}

	@PutMapping("/update")
	public ResponseEntity<BloodBankDTO> update(@Valid @NonNull @RequestBody final UpdateBloodBankDTO dto) {
		final var updatedBank = bloodBankService.update(dto);
		return ResponseEntity.status(HttpStatus.OK).body(getBloodBankMapper.EntityToEntityDTO(updatedBank));
	}

	@GetMapping("/all")
	public ResponseEntity<List<BloodBankDTO>> getAll() {
		final var bloodBanks = bloodBankService.getAll();
		if (bloodBanks.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(getBloodBankMapper.ListToListDTO(bloodBanks));
	}

	@GetMapping("/{id}")
	public ResponseEntity<BloodBankDTO> getAById(@Valid @NotNull @PathVariable("id") final Long id) {
		final var bloodBank = bloodBankService.getById(id);
		if (bloodBank.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(getBloodBankMapper.EntityToEntityDTO(bloodBank.get()));
	}
}
