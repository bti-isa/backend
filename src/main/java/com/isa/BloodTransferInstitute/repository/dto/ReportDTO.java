package com.isa.BloodTransferInstitute.repository.dto;

import com.isa.BloodTransferInstitute.repository.dto.appointment.AppointmentDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportDTO {

	Long id;

	String description;

	AppointmentDTO appointment;
}
