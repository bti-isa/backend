package com.isa.BloodTransferInstitute.controller;

import com.isa.BloodTransferInstitute.dto.appointment.AppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.FinishedAppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.NewAppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.ScheduleAppointmentDTO;
import com.isa.BloodTransferInstitute.mappers.GetAppointmentMapper;
import com.isa.BloodTransferInstitute.service.AppointmentService;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<AppointmentDTO> create(@Valid @NotNull @RequestBody final NewAppointmentDTO dto) {
		final var appointment = appointmentService.create(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(appointmentMapper.entityToEntityDTO(appointment));
	}

	@PatchMapping("/schedule")
	public ResponseEntity<AppointmentDTO> schedule(@Valid @NotNull @RequestBody final ScheduleAppointmentDTO dto) {
		final var appointment = appointmentService.schedule(dto);
		return ResponseEntity.status(HttpStatus.OK).body(appointmentMapper.entityToEntityDTO(appointment));
	}

	@PatchMapping("/finish")
	public ResponseEntity<AppointmentDTO> finish(@Valid @NotNull @RequestBody final FinishedAppointmentDTO dto) {
		final var appointment = appointmentService.finish(dto);
		return ResponseEntity.status(HttpStatus.OK).body(appointmentMapper.entityToEntityDTO(appointment));
	}

	@GetMapping("/all")
	public ResponseEntity<List<AppointmentDTO>> getAll() {
		final var appointments = appointmentService.getAll();
		if(appointments.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(appointmentMapper.listToListDTO(appointments));
	}

	@GetMapping("/{id}")
	public ResponseEntity<AppointmentDTO> getAppointment(@NotNull @PathVariable("id") final Long id) {
		final var appointment = appointmentService.findById(id);
		if(appointment.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(appointmentMapper.entityToEntityDTO(appointment.get()));
	}

	@GetMapping("/datetime")
	public ResponseEntity<List<AppointmentDTO>> findByDateTime(@NotNull @RequestParam final LocalDateTime dateTime) {
		final var appointments = appointmentService.findByDateTime(dateTime);
		if(appointments.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(appointmentMapper.listToListDTO(appointments));
	}

	@GetMapping("/available")
	public ResponseEntity<List<AppointmentDTO>> findAllAvailable() {
		final var appointments = appointmentService.findAllAvailable();
		if(appointments.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(appointmentMapper.listToListDTO(appointments));
	}
}
