package com.isa.BloodTransferInstitute.controller;

import com.isa.BloodTransferInstitute.dto.user.patient.*;
import com.isa.BloodTransferInstitute.mappers.GetUserMapper;
import com.isa.BloodTransferInstitute.service.PatientService;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Patient")
@CrossOrigin(origins = "*")
public class PatientController {

	private final PatientService patientService;
	private final GetUserMapper getUserMapper;

	@PostMapping("/")
	public ResponseEntity<PatientDTO> addUser(@Valid @NotNull @RequestBody final NewPatientDTO dto) {
		final var user = patientService.add(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(getUserMapper.entityToDTO(user));
	}

	@PatchMapping("/")
	@PreAuthorize("hasAuthority('PATIENT')")
	public ResponseEntity<PatientDTO> updateUser(@Valid @NotNull @RequestBody final UpdatePatientDTO dto) {
		final var user = patientService.update(dto);
		return ResponseEntity.status(HttpStatus.OK).body(getUserMapper.entityToDTO(user));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
	public ResponseEntity<Void> deleteUser(@Valid @NotNull @PathVariable("id") final Long id) {
		patientService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('SYSTEM_ADMIN', 'INSTITUTE_ADMIN')")
	public ResponseEntity<PatientDTO> getUser(@Valid @NotNull @PathVariable("id") final Long id) {
		final var user = patientService.get(id);
		if(user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(getUserMapper.entityToDTO(user.get()));
	}

	@GetMapping("/all")
	@PreAuthorize("hasAnyAuthority('SYSTEM_ADMIN', 'INSTITUTE_ADMIN')")
	public ResponseEntity<List<PatientDTO>> getAll() {
		final var users = patientService.getAll();
		if(users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(getUserMapper.entityListToDTOlist(users));
	}

	@PostMapping("/checkUnique")
	public ResponseEntity<List<Boolean>> checkUnique(@Valid @NotNull @RequestBody CheckUniquePatientDTO dto){
		List<Boolean> retVal = patientService.checkUnique(dto);
		return ResponseEntity.status(HttpStatus.OK).body(retVal);
	}

	@PostMapping("/search")
	@PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
	public ResponseEntity<List<PatientDTO>> search(@RequestBody final SearchPatientDTO dto) {
		final var searchResult = getUserMapper.entityListToDTOlist(patientService.search(dto));
		return  ResponseEntity.status(HttpStatus.OK).body(searchResult);
	}
}
