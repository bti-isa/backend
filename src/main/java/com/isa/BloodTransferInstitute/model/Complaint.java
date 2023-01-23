package com.isa.BloodTransferInstitute.model;

import com.isa.BloodTransferInstitute.enums.ComplaintStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Complaints")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    Long id;

    @Version
    Long version;

    @Column(nullable = false)
    String text;

    @Column
    String answer;

    @Column(nullable = false)
    ComplaintStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;
}
