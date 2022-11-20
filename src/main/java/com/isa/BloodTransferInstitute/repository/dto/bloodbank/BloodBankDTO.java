package com.isa.BloodTransferInstitute.repository.dto.bloodbank;

import com.isa.BloodTransferInstitute.repository.dto.address.AddressDTO;
import com.isa.BloodTransferInstitute.repository.dto.appointment.AppointmentDTO;

import java.util.List;

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
public class BloodBankDTO {

	Long id;

	String name;

	AddressDTO address;

	Double rating;

	String description;

	List<AppointmentDTO> appointmentList;

}
