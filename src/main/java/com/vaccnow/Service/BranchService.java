package com.vaccnow.Service;

import com.vaccnow.Exception.VaccNowException;
import com.vaccnow.Repository.BranchRepository;
import com.vaccnow.entity.Branch;
import com.vaccnow.entity.Customer;
import com.vaccnow.entity.Slot;
import com.vaccnow.entity.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchService {

    @Autowired
    BranchRepository branchRepository;

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public List<Vaccine> getVaccinesByBranch(String branchName) {
        return branchRepository.findByName(branchName).orElseThrow(() -> new VaccNowException
                ("Branch not found", HttpStatus.NOT_FOUND)).getVaccines();
    }

    public boolean isSpecificVaccineAvailable(String branchName, String vaccineName) {
        List<Vaccine> vaccines = branchRepository.findByName(branchName)
                .orElseThrow(() -> new VaccNowException("Branch not found", HttpStatus.NOT_FOUND))
                .getVaccines();

        for (Vaccine vaccine : vaccines) {
            if (vaccine.getName().equals(vaccineName))
                return true;
        }
        return false;
    }

    public List<String> getAvailableSlots(String branchName) {
        List<Slot> slots = branchRepository.findByName(branchName)
                .orElseThrow(() -> new VaccNowException("Branch not found", HttpStatus.NOT_FOUND))
                .getSlots();
        List<String> availableTimeSlots = new ArrayList<>();
        slots.stream().forEach(slot -> {
            if (!slot.isBooked()) availableTimeSlots.add(slot
                    .getSlot().toString());
        });
        return availableTimeSlots;
    }

    public List<Customer> getAppliedVaccinationPerBranch(String branchName) {
        List<Customer> customers = branchRepository.findByName(branchName)
                .orElseThrow(() -> new VaccNowException("Branch not found", HttpStatus.NOT_FOUND))
                .getCustomers();
        List<Customer> customersAppliedWithVaccination = new ArrayList<>();
        customers.stream().forEach(customer -> {
            if (customer.getApplied()) customersAppliedWithVaccination.add(customer);
        });
        return customersAppliedWithVaccination;
    }


}
