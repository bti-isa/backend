package com.isa.BloodTransferInstitute.mappers;

import com.isa.BloodTransferInstitute.dto.ReportDTO;
import com.isa.BloodTransferInstitute.dto.appointment.AppointmentDTO;
import com.isa.BloodTransferInstitute.dto.bloodbank.BloodBankDTO;
import com.isa.BloodTransferInstitute.dto.user.patient.PatientDTO;
import com.isa.BloodTransferInstitute.model.Appointment;
import com.isa.BloodTransferInstitute.model.BloodBank;
import com.isa.BloodTransferInstitute.model.Report;
import com.isa.BloodTransferInstitute.model.User;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetAppointmentMapper {

	AppointmentDTO entityToEntityDTO(Appointment appointment);

	List<AppointmentDTO> listToListDTO(List<Appointment> appointments);

	PatientDTO patientToPatientDTO(User patient);

	ReportDTO reportToReportDTO(Report report);

	BloodBankDTO bankToBankDTO(BloodBank bloodBank);
}
