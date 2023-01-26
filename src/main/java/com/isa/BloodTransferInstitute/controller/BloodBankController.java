package com.isa.BloodTransferInstitute.controller;

import com.isa.BloodTransferInstitute.dto.SearchDTO;
import com.isa.BloodTransferInstitute.dto.bloodbank.*;
import com.isa.BloodTransferInstitute.dto.user.admin.RegisteredDonorsDTO;
import com.isa.BloodTransferInstitute.mappers.BloodBankMapper;
import com.isa.BloodTransferInstitute.mappers.GetBloodBankMapper;
import com.isa.BloodTransferInstitute.model.BloodBank;
import com.isa.BloodTransferInstitute.model.BloodUnit;
import com.isa.BloodTransferInstitute.service.BloodBankService;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
	public ResponseEntity<BloodBankDTO> addBloodBank(@Valid @NotNull @RequestBody final NewBloodBankDTO dto) {
		final var bloodBank = bloodBankService.add(dto);
		return ResponseEntity.status(HttpStatus.OK).body(getBloodBankMapper.EntityToEntityDTO(bloodBank));
	}

	@PostMapping("/search")
	//@PreAuthorize("hasAnyAuthority('INSTITUTE_ADMIN', 'SYSTEM_ADMIN', 'PATIENT')")
	public ResponseEntity<List<BloodBankDTO>> search(@RequestBody final SearchDTO searchDTO) {
		final var searchResult = getBloodBankMapper.ListToListDTO(bloodBankService.search(searchDTO));
		return ResponseEntity.status(HttpStatus.OK).body(searchResult);
	}

	@PutMapping("/update")
	@PreAuthorize("hasAuthority('SYSTEM_ADMIN','INSTITUTE_ADMIN')")
	public ResponseEntity<BloodBankDTO> update(@Valid @NonNull @RequestBody final UpdateBloodBankDTO dto) {
		final var updatedBank = bloodBankService.update(dto);
		return ResponseEntity.status(HttpStatus.OK).body(getBloodBankMapper.EntityToEntityDTO(updatedBank));
	}

	@GetMapping("/all")
	//@PreAuthorize("hasAnyAuthority('INSTITUTE_ADMIN', 'SYSTEM_ADMIN', 'PATIENT')")
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

	@GetMapping
	@PreAuthorize("hasAnyAuthority('INSTITUTE_ADMIN', 'SYSTEM_ADMIN', 'PATIENT')")
	public ResponseEntity<List<BloodBankDTO>> getAllWithPage(Pageable page) {
		final var bloodBanks = bloodBankService.getAllWithPage(page);
		if (bloodBanks.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(getBloodBankMapper.ListToListDTO(bloodBanks.toList()));
	}

	@GetMapping("/simple")
	@PreAuthorize("hasAnyAuthority('INSTITUTE_ADMIN', 'SYSTEM_ADMIN', 'PATIENT')")
	public ResponseEntity<List<SimpleBloodBankDTO>> getSimpleInformation() {
		List<SimpleBloodBankDTO> returnList = new ArrayList<SimpleBloodBankDTO>();
		for(BloodBank bloodBank : bloodBankService.getAll()) {
			var simple = BloodBankMapper.EntityToSimpleDTO(bloodBank);
			returnList.add(simple);
		}
		return ResponseEntity.status(HttpStatus.OK).body(returnList);
	}

	@GetMapping("/registered-donors/{email}")
	@PreAuthorize("hasAnyAuthority('INSTITUTE_ADMIN')")
	public ResponseEntity<List<RegisteredDonorsDTO>>getRegisteredDonors(@Valid @NotNull @PathVariable("email") final String email){
		Long bloodBankId = bloodBankService.getByAdminId(email);
		if(bloodBankId == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(bloodBankService.getRegisteredDonors(bloodBankId));
	}

	@GetMapping("/get-by-admin/{email}")
	@PreAuthorize("hasAnyAuthority('INSTITUTE_ADMIN')")
	public ResponseEntity<BloodBankDTO> getByAdminId(@Valid @NotNull @PathVariable("email") final String email){
		Long bloodBankId = bloodBankService.getByAdminId(email);
		if(bloodBankId == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(getBloodBankMapper.EntityToEntityDTO(bloodBankService.getById(bloodBankId).get()));
	}

	@GetMapping("/get-blood-units/{id}")
	@PreAuthorize("hasAnyAuthority('INSTITUTE_ADMIN')")
	public ResponseEntity<?> getBloodUnits(@Valid @NotNull @PathVariable("id") final Long id) {
		List<BloodUnit> units = bloodBankService.getBloodUnits(id);
		if(units.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		List<BloodUnitDTO> retList = new ArrayList<BloodUnitDTO>();
		for (var unit : units){
			var dto = getBloodBankMapper.BloodUnitToDTO(unit);
			retList.add(dto);
		}

		return ResponseEntity.status(HttpStatus.OK).body(retList);
	}
}
