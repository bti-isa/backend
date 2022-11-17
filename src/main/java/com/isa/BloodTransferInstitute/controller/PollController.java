package com.isa.BloodTransferInstitute.controller;

import com.isa.BloodTransferInstitute.model.Poll;
import com.isa.BloodTransferInstitute.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/polls")
@CrossOrigin(origins = "*")
public class PollController {
    @Autowired
    PollService pollService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody Poll poll){
        pollService.add(poll);
        return new ResponseEntity(HttpStatus.OK);
    }
}
