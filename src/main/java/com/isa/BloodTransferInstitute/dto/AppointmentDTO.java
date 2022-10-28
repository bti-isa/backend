package com.isa.BloodTransferInstitute.dto;

import java.time.LocalDateTime;

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
public class AppointmentDTO {

	Long id;

	LocalDateTime dateTime;

	Double duration;

	UserDTO patient;

	AppointmentDetailsDTO appointmentDetails;
}
