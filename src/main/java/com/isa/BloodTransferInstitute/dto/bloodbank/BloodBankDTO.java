package com.isa.BloodTransferInstitute.dto.bloodbank;

import com.isa.BloodTransferInstitute.dto.AddressDTO;
import com.isa.BloodTransferInstitute.dto.appointment.AppointmentDTO;
import com.isa.BloodTransferInstitute.dto.user.UserDTO;

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

	List<UserDTO> bankAdmins;

}
