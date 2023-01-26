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

    @Column(nullable = false)
    String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    Address address;

    @Column
    Double rating;

    @Column(nullable = false)
    String description;

    @Column
    Integer equipment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "workinghours_id", referencedColumnName = "id")
    WorkingHours workingHours;

}
