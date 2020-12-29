package com.vaccnow.api;

import com.vaccnow.Service.BranchService;
import com.vaccnow.entity.Branch;
import com.vaccnow.entity.Customer;
import com.vaccnow.entity.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/branch")
public class BranchResource {

    @Autowired
    BranchService branchService;

    @GetMapping("/getall")
    public ResponseEntity<List<Branch>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(branchService.getAllBranches());
    }

    @GetMapping("/availablevaccinesinbranch")
    public ResponseEntity<List<Vaccine>> getAvailableVaccinesInBranch(@RequestParam String branchName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(branchService.getVaccinesByBranch(branchName));
    }

    @GetMapping("/isvaccineavailableinbranch")
    public ResponseEntity<Boolean> isVaccineAvailableInBranch(@RequestParam String branchName
            , @RequestParam String vaccineName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(branchService.isSpecificVaccineAvailable(branchName, vaccineName));
    }

    @GetMapping("/getavailableslotsinbranch")
    public ResponseEntity<List<String>> getAvailableSlotsInBranch(@RequestParam String branchName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(branchService.getAvailableSlots(branchName));
    }

    @GetMapping("/getallappliedvaccinations")
    public ResponseEntity<List<Customer>> getAllAppliedVaccinations(@RequestParam String branchName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(branchService.getAppliedVaccinationPerBranch(branchName));
    }

}
