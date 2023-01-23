package com.isa.BloodTransferInstitute.enums;

public enum ComplaintStatus {
    PENDING("PENDING"),
    ANSWERED("ANSWERED");
    private final String status;
    ComplaintStatus(final String status) {this.status = status; }
    public String getComplaintStatus() {return  this.status; }
}
