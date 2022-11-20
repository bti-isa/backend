package com.isa.BloodTransferInstitute.mappers;

import com.isa.BloodTransferInstitute.dto.appointment.FinishedAppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.NewAppointmentDTO;
import com.isa.BloodTransferInstitute.enums.AppointmentStatus;
import com.isa.BloodTransferInstitute.model.Appointment;
import com.isa.BloodTransferInstitute.model.BloodBank;
import com.isa.BloodTransferInstitute.model.Report;
import com.isa.BloodTransferInstitute.model.User;

import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

	public static Appointment NewDTOToEntity(NewAppointmentDTO dto, BloodBank bloodBank) {

		final var report = Report.builder()
			.description("")
			.build();

		return Appointment.builder()
			.bloodBank(bloodBank)
			.patient(null)
			.duration(30.0)
			.dateTime(dto.getDateTime())
			.status(AppointmentStatus.AVAILIBLE)
			.finished(false)
			.report(report)
			.build();
	}

	public static Appointment ScheduleDTOToEntity(Appointment appointment, User patient) {
		return appointment.toBuilder()
			.status(AppointmentStatus.SCHEDULED)
			.patient(patient)
			.build();
	}

	public static Appointment FinishDTOToEntity(FinishedAppointmentDTO dto, Appointment appointment) {

		var report = appointment.getReport()
			.toBuilder()
			.description(dto.getReportDescription())
			.build();

		return appointment.toBuilder()
			.finished(true)
			.status(AppointmentStatus.COMPLETED)
			.report(report)
			.build();
	}

}
