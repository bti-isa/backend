package com.isa.BloodTransferInstitute.controller;

import com.isa.BloodTransferInstitute.dto.appointment.AppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.FinishedAppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.NewAppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.ScheduleAppointmentDTO;
import com.isa.BloodTransferInstitute.exception.NotFoundException;
import com.isa.BloodTransferInstitute.mappers.GetAppointmentMapper;
import com.isa.BloodTransferInstitute.model.Appointment;
import com.isa.BloodTransferInstitute.service.AppointmentService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;

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
		dto.setDateTime(dto.getDateTime().plusHours(1).minusSeconds(dto.getDateTime().getSecond()));
		final var appointment = appointmentService.create(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(appointmentMapper.entityToEntityDTO(appointment));
	}

	@PatchMapping("/pre-schedule")
	@PreAuthorize("hasAuthority('PATIENT')")
	public ResponseEntity<?> preSchedule(@Valid @NotNull @RequestBody final ScheduleAppointmentDTO dto) {
		appointmentService.preSchedule(dto);
		return ResponseEntity.status(HttpStatus.OK).body("The message has just been sent to your email address. Please scan QR code and confirm your desired appointment.");
	}

	@GetMapping("/scheduled-message/{appointmentId}/{patientId}")
	public ResponseEntity<?> schedule(@NotNull @PathVariable("appointmentId") final Long appointmentId,
												@NotNull @PathVariable("patientId") final Long patientId) {
		Appointment scheduledAppointment = appointmentService.schedule(appointmentId, patientId);
		return ResponseEntity.status(HttpStatus.OK).body("Dear "+ scheduledAppointment.getPatient().getFirstname() + ", your appointment is successfully scheduled at " + scheduledAppointment.getDateTime().toString().replace('T',' ') + " .");
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
		parsedDateTime = parsedDateTime.plusHours(1).minusSeconds(parsedDateTime.getSecond());
		final var appointments = appointmentService.findAllAvailableByDateTime(parsedDateTime, pageSize, pageNumber, direction);
		if(appointments.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(appointmentMapper.listToListDTO(appointments));
	}
	@GetMapping("/cancel/{id}")
	@PreAuthorize("hasAuthority('PATIENT')")
	public ResponseEntity<AppointmentDTO> cancelAppointment(@NotNull @PathVariable("id") final Long id){
		final var appointment = appointmentService.cancelAppointment(id);
		return ResponseEntity.status(HttpStatus.OK).body(appointmentMapper.entityToEntityDTO(appointment));
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

	@GetMapping("/admins-bloodBank")
	@PreAuthorize("hasAuthority('INSTITUTE_ADMIN')")
	public ResponseEntity<List<AppointmentDTO>> findAllByAdminsUsername(@NotNull @RequestParam("username") final String username) {
		final var appointment = appointmentService.findAllByAdminsUsername(username);
		if(appointment.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(appointmentMapper.listToListDTO(appointment));
	}

	@GetMapping("/bloodbank/{id}")
	@PreAuthorize("hasAnyAuthority('INSTITUTE_ADMIN', 'SYSTEM_ADMIN', 'PATIENT')")
	public ResponseEntity<List<AppointmentDTO>> findAllByBloodbankId(@NotNull @PathVariable("id")final Long id){
		final var appointment = appointmentService.findAllByBloodbankId(id);
		if(appointment.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(appointmentMapper.listToListDTO(appointment));
	}

}
