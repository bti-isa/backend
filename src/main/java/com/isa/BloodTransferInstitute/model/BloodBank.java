package com.isa.BloodTransferInstitute.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BloodBanks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    Long id;

    @Column(nullable = false, unique = true)
    String name;

    @OneToOne(mappedBy = "bloodBank")
    Address address;

    @Column
    Double rating;

    @Column(nullable = false)
    String description;

    @OneToMany(mappedBy = "bloodBank")
    List<Appointment> appointmentList;

    @OneToMany(mappedBy = "bloodBank")
    List<User> bankAdmins;
}
