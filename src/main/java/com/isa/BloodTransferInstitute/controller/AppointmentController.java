package com.isa.BloodTransferInstitute.controller;

import com.isa.BloodTransferInstitute.dto.appointment.AppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.FinishedAppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.NewAppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.ScheduleAppointmentDTO;
import com.isa.BloodTransferInstitute.mappers.GetAppointmentMapper;
import com.isa.BloodTransferInstitute.service.AppointmentService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Appointment")
@CrossOrigin(origins = "*")
public class AppointmentController {

	private final AppointmentService appointmentService;
	private final GetAppointmentMapper appointmentMapper;

	@PostMapping("/")
	@PreAuthorize("hasAuthority('INSTITUTE_ADMIN')")
	public ResponseEntity<AppointmentDTO> create(@Valid @NotNull @RequestBody final NewAppointmentDTO dto) {
		final var appointment = appointmentService.create(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(appointmentMapper.entityToEntityDTO(appointment));
	}

	@PatchMapping("/schedule")
	@PreAuthorize("hasAuthority('PATIENT')")
	public ResponseEntity<AppointmentDTO> schedule(@Valid @NotNull @RequestBody final ScheduleAppointmentDTO dto) {
		final var appointment = appointmentService.schedule(dto);
		return ResponseEntity.status(HttpStatus.OK).body(appointmentMapper.entityToEntityDTO(appointment));
	}

	@PatchMapping("/finish")
	@PreAuthorize("hasAuthority('INSTITUTE_ADMIN')")
	public ResponseEntity<AppointmentDTO> finish(@Valid @NotNull @RequestBody final FinishedAppointmentDTO dto) {
		final var appointment = appointmentService.finish(dto);
		return ResponseEntity.status(HttpStatus.OK).body(appointmentMapper.entityToEntityDTO(appointment));
	}

	@GetMapping("/all")
	@PreAuthorize("hasAuthority('INSTITUTE_ADMIN')")
	public ResponseEntity<List<AppointmentDTO>> getAll() {
		final var appointments = appointmentService.getAll();
		if(appointments.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(appointmentMapper.listToListDTO(appointments));
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('INSTITUTE_ADMIN')")
	public ResponseEntity<AppointmentDTO> getAppointment(@NotNull @PathVariable("id") final Long id) {
		final var appointment = appointmentService.findById(id);
		if(appointment.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(appointmentMapper.entityToEntityDTO(appointment.get()));
	}

	@GetMapping("/{number}/{size}/{direction}")
	@PreAuthorize("hasAuthority('PATIENT')")
	public ResponseEntity<List<AppointmentDTO>> findAllAvailableByDateTime(@NotNull @PathVariable("number") final int pageNumber,
																			@NotNull @PathVariable("size") final int pageSize,
																			@NotNull @PathVariable("direction") final Sort.Direction direction,
																			@NotNull @RequestParam(name = "dateTime") final String dateTime) {
		LocalDateTime parsedDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME);
		final var appointments = appointmentService.findAllAvailableByDateTime(parsedDateTime, pageSize, pageNumber, direction);
		if(appointments.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(appointmentMapper.listToListDTO(appointments));
	}

	@GetMapping("/patient/{id}")
	@PreAuthorize("hasAuthority('PATIENT')")
	public ResponseEntity<List<AppointmentDTO>> findAllByPatientId(@NotNull @PathVariable("id") final Long id) {
		final var appointment = appointmentService.findAllByPatientId(id);
		if(appointment.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(appointmentMapper.listToListDTO(appointment));
	}

	@GetMapping("/bloodBank/{id}")
	@PreAuthorize("hasAuthority('INSTITUTE_ADMIN')")
	public ResponseEntity<List<AppointmentDTO>> findAllByBloodBankId(@NotNull @PathVariable("id") final Long id) {
		final var appointment = appointmentService.findAllByBloodBankId(id);
		if(appointment.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(appointmentMapper.listToListDTO(appointment));
	}

}
