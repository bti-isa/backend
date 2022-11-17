package com.isa.BloodTransferInstitute.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "Polls")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false,unique = true)
    Long id;

    @Column(nullable = false)
    Boolean weightOver50kg;

    @Column(nullable = false)
    Boolean commonCold;

    @Column(nullable = false)
    Boolean skinDiseases;

    @Column(nullable = false)
    Boolean problemWithPressure;

    @Column(nullable = false)
    Boolean antibiotics;

    @Column(nullable = false)
    Boolean menstruation;

    @Column(nullable = false)
    Boolean dentalIntervention;

    @Column(nullable = false)
    Boolean other;

}
