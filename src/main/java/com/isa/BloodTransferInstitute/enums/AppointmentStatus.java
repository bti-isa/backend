package com.isa.BloodTransferInstitute.enums;

public enum AppointmentStatus {
    AVAILIBLE("AVAILIBLE"),
    SCHEDULED("SCHEDULED"),
    COMPLETED("COMPLETED");

    private final String status;
    AppointmentStatus(final String status) {this.status = status; }
    public String getAppointmentStatus() {return  this.status; }
}
