package com.isa.BloodTransferInstitute.repository.dto.appointment.mappers;

import com.isa.BloodTransferInstitute.repository.dto.ReportDTO;
import com.isa.BloodTransferInstitute.repository.dto.appointment.AppointmentDTO;
import com.isa.BloodTransferInstitute.repository.dto.bloodbank.BloodBankDTO;
import com.isa.BloodTransferInstitute.repository.dto.user.patient.PatientDTO;
import com.isa.BloodTransferInstitute.model.Appointment;
import com.isa.BloodTransferInstitute.model.BloodBank;
import com.isa.BloodTransferInstitute.model.Report;
import com.isa.BloodTransferInstitute.model.User;

import java.util.List;

public interface GetAppointmentMapper {

	AppointmentDTO entityToEntityDTO(Appointment appointment);

	List<AppointmentDTO> listToListDTO(List<Appointment> appointments);

	PatientDTO patientToPatientDTO(User patient);

	ReportDTO reportToReportDTO(Report report);

	BloodBankDTO bankToBankDTO(BloodBank bloodBank);
}
