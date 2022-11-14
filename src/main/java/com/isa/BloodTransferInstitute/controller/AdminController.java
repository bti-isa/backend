package com.isa.BloodTransferInstitute.controller;

import com.isa.BloodTransferInstitute.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Admin")
public class AdminController {

    private final AdminService adminService;


}
